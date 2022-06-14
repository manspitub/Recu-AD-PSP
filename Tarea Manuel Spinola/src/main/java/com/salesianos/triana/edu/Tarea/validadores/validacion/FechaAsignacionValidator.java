package com.salesianos.triana.edu.Tarea.validadores.validacion;

import com.salesianos.triana.edu.Tarea.validadores.anotaciones.FechaAsignacionValueMatch;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class FechaAsignacionValidator implements ConstraintValidator<FechaAsignacionValueMatch, Object> {


    private String fechaInicioField;
    private String fechaFinField;


    @Override
    public void initialize(FechaAsignacionValueMatch constraintAnnotation) {
        fechaInicioField = constraintAnnotation.fechaInicioField();
        fechaFinField = constraintAnnotation.fechaFinField();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        LocalDateTime fechaInicio = (LocalDateTime) PropertyAccessorFactory.forBeanPropertyAccess(o).getPropertyValue(fechaInicioField);
        LocalDateTime fechaFin = (LocalDateTime) PropertyAccessorFactory.forBeanPropertyAccess(o).getPropertyValue(fechaFinField);


        if (LocalDateTime.now().isAfter(fechaInicio) && LocalDateTime.now().isAfter(fechaFin)){
            return false;
        } else {
            return true;
        }

    }
}
