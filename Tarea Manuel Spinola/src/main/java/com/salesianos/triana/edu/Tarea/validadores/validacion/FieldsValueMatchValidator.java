package com.salesianos.triana.edu.Tarea.validadores.validacion;

import com.salesianos.triana.edu.Tarea.validadores.anotaciones.FieldsValueMatch;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        String fieldValue = (String) PropertyAccessorFactory.forBeanPropertyAccess(value).getPropertyValue(field);
        String fieldMatchValue = (String) PropertyAccessorFactory.forBeanPropertyAccess(value).getPropertyValue(fieldMatch);

        return StringUtils.hasText(fieldValue) && fieldValue.contentEquals(fieldMatchValue);

    }
}
