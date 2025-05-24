package com.example.springstudy.global.validation.validator;

import com.example.springstudy.domain.mission.enums.MissionCurrent;
import com.example.springstudy.domain.mission.exception.code.MissionErrorCode;
import com.example.springstudy.domain.mission.repository.MissionRepository;
import com.example.springstudy.domain.user.service.query.UserQueryService;
import com.example.springstudy.global.validation.annotation.HasAfterProgress;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MissionInProgressValidator implements ConstraintValidator<HasAfterProgress, Long> {

    private final MissionRepository missionRepository;
    private final UserQueryService userQueryService;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        Long userId = userQueryService.getAuthUserId().getId();

        boolean isValid = false;

        if (userId != null && value != null){
            MissionCurrent result = missionRepository.findByUserIdAndMissionId(userId, value);
            isValid = result != null && result.equals(MissionCurrent.IN_PROGRESS);
        }

        if (!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    MissionErrorCode.ALREADY_COMPLETED.getMessage()
            ).addConstraintViolation();
        }
        return isValid;
    }
}
