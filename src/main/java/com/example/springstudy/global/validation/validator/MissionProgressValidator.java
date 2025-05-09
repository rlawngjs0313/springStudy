package com.example.springstudy.global.validation.validator;

import com.example.springstudy.domain.mission.enums.MissionCurrent;
import com.example.springstudy.domain.mission.exception.code.MissionErrorCode;
import com.example.springstudy.domain.mission.repository.MissionRepository;
import com.example.springstudy.domain.mission.repository.UserMissionRepository;
import com.example.springstudy.domain.user.service.query.UserQueryService;
import com.example.springstudy.global.validation.annotation.HasInProgress;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MissionProgressValidator implements ConstraintValidator<HasInProgress, Object> {

    private final MissionRepository missionRepository;
    private final UserQueryService userQueryService;

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {

        BeanWrapper wrapper = new BeanWrapperImpl(object);
        Long missionId = (Long) wrapper.getPropertyValue("missionId");
        Long userId = userQueryService.getAuthUserId().getId();

        boolean isValid = false;

        if (userId != null && missionId != null){
            MissionCurrent result = missionRepository.findByUserIdAndMissionId(userId, missionId);
            isValid = result != null && result.equals(MissionCurrent.BEFORE_PROGRESS);
        }

        if (!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MissionErrorCode.NOT_BEFORE_PROGRESS.getMessage()).addConstraintViolation();
        }
        return isValid;
    }
}
