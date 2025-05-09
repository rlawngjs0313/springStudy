package com.example.springstudy.global.validation.validator;


import com.example.springstudy.domain.shop.exception.code.ShopErrorCode;
import com.example.springstudy.domain.shop.repository.ShopRepository;
import com.example.springstudy.global.validation.annotation.ExistShop;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ShopExistValidator implements ConstraintValidator<ExistShop, Object> {

    private final ShopRepository shopRepository;

    @Override
    public boolean isValid(Object values, ConstraintValidatorContext context) {
        boolean isValid = shopRepository.existsById((Long) values);

        if (!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ShopErrorCode.NOT_FOUND.getMessage()).addConstraintViolation();
        }

        return isValid;
    }
}
