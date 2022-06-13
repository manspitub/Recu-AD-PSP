package com.salesianos.triana.edu.Tarea.validadores.validacion;

import com.salesianos.triana.edu.Tarea.validadores.anotaciones.PasswordMatch;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {

    private String passwordField;

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        passwordField = constraintAnnotation.passwordField();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String password = (String) PropertyAccessorFactory.forBeanPropertyAccess(o).getPropertyValue(passwordField);

        if (password.length() >= 8 && password.length() <= 20){

            boolean mayuscula = false;
            boolean minuscula = false;
            boolean numero = false;
            char c;

            for (int i=0; i<password.length(); i++){

                c = password.charAt(i);

                if (Character.isDigit(c))
                    numero = true;
                if (Character.isUpperCase(c))
                    mayuscula = true;
                if (Character.isLowerCase(c))
                    minuscula = true;

            }

            if (numero && mayuscula && minuscula)
                return true;
            else
                return false;


        } else
            return false;
    }
}
