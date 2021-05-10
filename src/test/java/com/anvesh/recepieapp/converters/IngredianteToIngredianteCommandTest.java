package com.anvesh.recepieapp.converters;

import com.anvesh.recepieapp.dataTransfers.IngrediantCommand;
import com.anvesh.recepieapp.domain.Ingrediant;
import com.anvesh.recepieapp.domain.UnitOfMeasurment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class IngredianteToIngredianteCommandTest {

    final Long id = 1L;
    final String description = "anvesh";
    final BigDecimal amount = new BigDecimal(4);
    IngredianteToIngredianteCommand converter;

    @BeforeEach
    void setUp() {
        converter = new IngredianteToIngredianteCommand(new UomToUomCommand());
    }

    @Test
    void nullOject() {
        IngrediantCommand cat = converter.convert(null);
        assertNull(cat);
    }

    @Test
    void convert() {
        Ingrediant ingrediant = new Ingrediant();
        ingrediant.setId(id);
        ingrediant.setAmount(amount);
        ingrediant.setDescription(description);
        UnitOfMeasurment measurment = new UnitOfMeasurment();
        measurment.setId(id);
        ingrediant.setMeasurment(measurment);
        IngrediantCommand command = converter.convert(ingrediant);
        assertEquals(id, command.getId());
        assertEquals(amount, command.getAmount());
        assertEquals(id, command.getMeasurment().getId());
        assertEquals(description, command.getDescription());
    }
}