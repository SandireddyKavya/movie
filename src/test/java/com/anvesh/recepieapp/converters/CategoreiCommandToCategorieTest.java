package com.anvesh.recepieapp.converters;

import com.anvesh.recepieapp.dataTransfers.CategorieCommand;
import com.anvesh.recepieapp.domain.Categorie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CategoreiCommandToCategorieTest {

    final Long id = 1L;
    final String description = "Anvesh";
    CategoreiCommandToCategorie converter;

    @BeforeEach
    void setUp() {
        converter = new CategoreiCommandToCategorie();
    }

    @Test
    void nullOject() {
        Categorie cat = converter.convert(null);
        assertNull(cat);
    }

    @Test
    void convert() {
        var categorieCommand = new CategorieCommand();
        categorieCommand.setId(id);
        categorieCommand.setDescription(description);
        var categorie = converter.convert(categorieCommand);
        assertEquals(id, categorie.getId());
        assertEquals(description, categorie.getDescription());

    }
}