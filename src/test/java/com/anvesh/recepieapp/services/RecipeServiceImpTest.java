package com.anvesh.recepieapp.services;

import com.anvesh.recepieapp.domain.Recipe;
import com.anvesh.recepieapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class RecipeServiceImpTest {

    @Mock
    RecipeRepository recipeRepository;

    RecipeServiceImp recipeServiceImp;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeServiceImp = new RecipeServiceImp(recipeRepository);
    }

    @Test
    public void getRecipies() {
        Set<Recipe> recipeSet = recipeServiceImp.getRecipies();
        assertEquals(recipeSet.size(), 0);
    }
}