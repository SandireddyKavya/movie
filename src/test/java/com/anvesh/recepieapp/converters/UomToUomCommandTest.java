package com.anvesh.recepieapp.converters;

import com.anvesh.recepieapp.dataTransfers.UnitOfMeasurementCommand;
import com.anvesh.recepieapp.domain.UnitOfMeasurment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UomToUomCommandTest {
    final Long id = 1L;
    final String uom = "Anvesh";
    UomToUomCommand converter;

    @BeforeEach
    void setUp() {
        converter = new UomToUomCommand();
    }

    @Test
    void checkNull() {
        UnitOfMeasurementCommand command = converter.convert(null);
        assertNull(command);
    }

    @Test
    void convert() {
        UnitOfMeasurment measurment = new UnitOfMeasurment();
        measurment.setId(id);
        measurment.setUom(uom);
        UnitOfMeasurementCommand command = converter.convert(measurment);
        assertEquals(id, command.getId());
        assertEquals(uom, command.getUom());

    }
}