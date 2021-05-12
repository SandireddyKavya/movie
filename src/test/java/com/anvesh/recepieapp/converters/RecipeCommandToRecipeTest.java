package com.anvesh.recepieapp.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecipeCommandToRecipeTest {
    private IngrediantCommandToIngrediant toIngrediant;
    private CategoreiCommandToCategorie toCategorie;
    private NotesCommandToNotes toNotes;

    @BeforeEach
    void setUp() {
        toIngrediant = new IngrediantCommandToIngrediant(new UomCommandToUom());


    }

    @Test
    void convert() {
    }
}