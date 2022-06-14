package com.vladgoncharov.eshop.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckCityValidator.class)
public @interface CheckCity {

    String message() default "Введены неверные символы";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}