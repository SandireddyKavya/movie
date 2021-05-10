package com.anvesh.recepieapp.converters;

import com.anvesh.recepieapp.dataTransfers.UnitOfMeasurementCommand;
import com.anvesh.recepieapp.domain.UnitOfMeasurment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UomToUomCommand implements Converter<UnitOfMeasurment, UnitOfMeasurementCommand> {
    @Override
    public UnitOfMeasurementCommand convert(UnitOfMeasurment unitOfMeasurment) {
        UnitOfMeasurementCommand command = new UnitOfMeasurementCommand();
        command.setId(unitOfMeasurment.getId());
        command.setUom(unitOfMeasurment.getUom());
        return command;
    }
}
