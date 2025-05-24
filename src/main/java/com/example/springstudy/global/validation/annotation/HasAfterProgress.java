package com.example.springstudy.global.validation.annotation;


import com.example.springstudy.global.validation.validator.MissionInProgressValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MissionInProgressValidator.class)
@Target({ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface HasAfterProgress {

    String message() default "해당 미션은 이미 완료했습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
