package com.anvesh.recepieapp.converters;

import com.anvesh.recepieapp.dataTransfers.IngrediantCommand;
import com.anvesh.recepieapp.dataTransfers.UnitOfMeasurementCommand;
import com.anvesh.recepieapp.domain.Ingrediant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class IngrediantCommandToIngrediantTest {
    final Long id = 1L;
    final String description = "anvesh";
    final BigDecimal amount = new BigDecimal(4);
    IngrediantCommandToIngrediant converter;
    UomCommandToUom uomConverter;

    @BeforeEach
    void setUp() {
        converter = new IngrediantCommandToIngrediant(new UomCommandToUom());
    }

    @Test
    void nullOject() {
        Ingrediant cat = converter.convert(null);
        assertNull(cat);
    }

    @Test
    void convert() {
        IngrediantCommand ingrediantCommand = new IngrediantCommand();
        ingrediantCommand.setId(id);
        ingrediantCommand.setDescription(description);
        ingrediantCommand.setAmount(amount);
        UnitOfMeasurementCommand command = new UnitOfMeasurementCommand();
        command.setId(id);
        ingrediantCommand.setMeasurment(command);
        Ingrediant ingrediant = converter.convert(ingrediantCommand);
        assertEquals(amount, ingrediant.getAmount());
        assertEquals(id, ingrediant.getId());
        assertEquals(description, ingrediant.getDescription());
        assertEquals(command.getId(), ingrediant.getMeasurment().getId());
    }

}