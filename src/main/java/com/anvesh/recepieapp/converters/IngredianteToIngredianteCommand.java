package com.anvesh.recepieapp.converters;

import com.anvesh.recepieapp.dataTransfers.IngrediantCommand;
import com.anvesh.recepieapp.domain.Ingrediant;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredianteToIngredianteCommand implements Converter<Ingrediant, IngrediantCommand> {

    private final UomToUomCommand toUomCommand;


    public IngredianteToIngredianteCommand(UomToUomCommand toUomCommand) {
        this.toUomCommand = toUomCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public IngrediantCommand convert(Ingrediant ingrediant) {
        if (ingrediant == null) {
            return null;
        }
        IngrediantCommand command = new IngrediantCommand();
        command.setId(ingrediant.getId());
        command.setMeasurment(toUomCommand.convert(ingrediant.getMeasurment()));
        command.setAmount(ingrediant.getAmount());
        command.setDescription(ingrediant.getDescription());
        return command;
    }
}
