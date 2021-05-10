package com.anvesh.recepieapp.converters;

import com.anvesh.recepieapp.dataTransfers.UnitOfMeasurementCommand;
import com.anvesh.recepieapp.domain.UnitOfMeasurment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UomCommandToUomTest {

    final Long id = 1L;
    final String uom = "Anvesh";
    UomCommandToUom converter;

    @BeforeEach
    void setUp() {
        converter = new UomCommandToUom();
    }

    @Test
    void checkNull() {
        UnitOfMeasurment unitOfMeasurment = converter.convert(null);
        assertNull(unitOfMeasurment);
    }

    @Test
    void convert() {
        var command = new UnitOfMeasurementCommand();
        command.setId(id);
        command.setUom(uom);
        UnitOfMeasurment measurment = converter.convert(command);
        assertEquals(id, measurment.getId());
        assertEquals(uom, measurment.getUom());
    }
}