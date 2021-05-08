package com.anvesh.recepieapp.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategorieTest {
    Categorie categorie;

    @Before
    public void setDes() {
        categorie = new Categorie();
        categorie.setDescription("Hello");
    }

    @Test
    public void getRecipes() {
        assertEquals("Hello", categorie.getDescription());
    }

    @Test
    public void getDescription() {
    }
}