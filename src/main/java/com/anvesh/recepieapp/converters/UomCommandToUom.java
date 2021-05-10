package com.anvesh.recepieapp.converters;

import com.anvesh.recepieapp.dataTransfers.UnitOfMeasurementCommand;
import com.anvesh.recepieapp.domain.UnitOfMeasurment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UomCommandToUom implements Converter<UnitOfMeasurementCommand, UnitOfMeasurment> {
    @Override
    public UnitOfMeasurment convert(UnitOfMeasurementCommand unitOfMeasurementCommand) {
        UnitOfMeasurment measurment = new UnitOfMeasurment();
        measurment.setUom(unitOfMeasurementCommand.getUom());
        measurment.setId(unitOfMeasurementCommand.getId());
        return measurment;
    }
}
