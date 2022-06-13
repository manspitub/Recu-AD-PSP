package com.salesianos.triana.edu.Tarea.validadores.anotaciones;


import com.salesianos.triana.edu.Tarea.validadores.validacion.PasswordMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = PasswordMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PasswordMatch {

    String message() default "La contraseña no cumple con las características requeridas";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};

    String passwordField();

}