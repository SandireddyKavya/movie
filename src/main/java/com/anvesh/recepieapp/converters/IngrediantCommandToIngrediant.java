package com.anvesh.recepieapp.converters;

import com.anvesh.recepieapp.dataTransfers.IngrediantCommand;
import com.anvesh.recepieapp.domain.Ingrediant;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngrediantCommandToIngrediant implements Converter<IngrediantCommand, Ingrediant> {

    final UomCommandToUom converter;

    public IngrediantCommandToIngrediant(UomCommandToUom converter) {
        this.converter = converter;
    }

    @Override
    public Ingrediant convert(IngrediantCommand ingrediantCommand) {
        if (ingrediantCommand == null) {
            return null;
        }
        Ingrediant ingrediant = new Ingrediant();
        ingrediant.setId(ingrediantCommand.getId());
        ingrediant.setDescription(ingrediantCommand.getDescription());
        ingrediant.setAmount(ingrediantCommand.getAmount());
        ingrediant.setMeasurment(converter.convert(ingrediantCommand.getMeasurment()));
        return ingrediant;
    }
}
