package com.anvesh.recepieapp.services;

import com.anvesh.recepieapp.domain.Recipe;
import com.anvesh.recepieapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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
//        verify(recipeServiceImp,times(1).)
    }

    @Test
    public void getRecipie() {
        Recipe testRcipe = new Recipe();
        testRcipe.setId(1L);
        Optional<Recipe> optionalRecipe = Optional.of(testRcipe);
        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
        Recipe recipe = recipeServiceImp.findById(1L);
        assertNotNull("Recipie is null", recipe);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }
}