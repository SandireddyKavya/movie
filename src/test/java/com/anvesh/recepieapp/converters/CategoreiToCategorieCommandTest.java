package com.anvesh.recepieapp.converters;

import com.anvesh.recepieapp.dataTransfers.CategorieCommand;
import com.anvesh.recepieapp.domain.Categorie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CategoreiToCategorieCommandTest {
    final Long id = 1L;
    final String description = "Anvesh";
    CategoreiToCategorieCommand converter;

    @BeforeEach
    void setUp() {
        converter = new CategoreiToCategorieCommand();
    }

    @Test
    void nullOject() {
        CategorieCommand cat = converter.convert(null);
        assertNull(cat);
    }

    @Test
    void convert() {
        Categorie categorie = new Categorie();
        categorie.setId(id);
        categorie.setDescription(description);
        CategorieCommand command = converter.convert(categorie);
        assertEquals(id, command.getId());
        assertEquals(description, command.getDescription());

    }
}