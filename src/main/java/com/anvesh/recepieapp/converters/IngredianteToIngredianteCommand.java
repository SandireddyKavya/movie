package com.anvesh.recepieapp.converters;

import com.anvesh.recepieapp.dataTransfers.IngrediantCommand;
import com.anvesh.recepieapp.domain.Ingrediant;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredianteToIngredianteCommand implements Converter<Ingrediant, IngrediantCommand> {
    @Override
    public IngrediantCommand convert(Ingrediant ingrediant) {
        IngrediantCommand command = new IngrediantCommand();
        return command;
    }
}
