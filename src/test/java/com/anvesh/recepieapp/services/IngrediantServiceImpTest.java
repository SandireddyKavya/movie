package com.anvesh.recepieapp.services;

import com.anvesh.recepieapp.converters.IngrediantCommandToIngrediant;
import com.anvesh.recepieapp.converters.IngredianteToIngredianteCommand;
import com.anvesh.recepieapp.converters.UomCommandToUom;
import com.anvesh.recepieapp.converters.UomToUomCommand;
import com.anvesh.recepieapp.dataTransfers.IngrediantCommand;
import com.anvesh.recepieapp.domain.Ingrediant;
import com.anvesh.recepieapp.domain.Recipe;
import com.anvesh.recepieapp.repositories.IngrediantRepo;
import com.anvesh.recepieapp.repositories.RecipeRepository;
import com.anvesh.recepieapp.repositories.UnitOfMeasureRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngrediantServiceImpTest {
    @Mock
    UnitOfMeasureRepo repo;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    IngrediantRepo ingrediantRepository;
    //    To inject uom into converter
    @Mock
    UomToUomCommand toUomCommand;
    //    TO inject converter into service implimentation or else it throws null pointer exception
    @InjectMocks
    IngredianteToIngredianteCommand toIngredianteCommand;

    @Mock
    UomCommandToUom toUom;

    @InjectMocks
    IngrediantCommandToIngrediant toIngrediant;

   /* public IngrediantServiceImpTest() {
        this.toIngredianteCommand = new IngredianteToIngredianteCommand(new UomToUomCommand());
    }*/

    @InjectMocks
    IngrediantServiceImp service;

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.initMocks(this);
        service = new IngrediantServiceImp(ingrediantRepository, toIngredianteCommand, toIngrediant, recipeRepository, repo);
    }

    @Test
    void findByIdAndRecipeId() {
        Recipe sample = new Recipe();
        sample.setId(1L);
        Ingrediant ingrediant1 = new Ingrediant();
        ingrediant1.setId(1L);
        sample.getIngrediants().add(ingrediant1);
        ingrediant1.setRecipe(sample);
        Ingrediant ingrediant2 = new Ingrediant();
        ingrediant2.setId(2L);
        ingrediant2.setRecipe(sample);
        sample.getIngrediants().add(ingrediant2);
        when(ingrediantRepository.findByIdAndRecipeId(anyLong(), anyLong())).thenReturn(Optional.of(ingrediant2));
        IngrediantCommand command = service.findByIngrediantIdAndRecipeId(1L, 1L);
        assertNotNull(command);
        assertEquals(2L, command.getId());
        assertEquals(1L, command.getRecipeId());
        verify(ingrediantRepository, times(1)).findByIdAndRecipeId(anyLong(), anyLong());
    }

    @Test
    void saveOrUpdateIngrediant() {
        IngrediantCommand command = new IngrediantCommand();
        command.setId(1L);
        command.setRecipeId(2L);
        Ingrediant ing = new Ingrediant();
        ing.setId(1L);
        ing.setRecipe(new Recipe());
        ing.getRecipe().setId(2L);
        Optional<Ingrediant> ingrediantOptional = Optional.of(ing);
        Optional<Recipe> recipe = Optional.of(new Recipe());
        Recipe savedRecipe = new Recipe();
        savedRecipe.getIngrediants().add(new Ingrediant());
        savedRecipe.getIngrediants().iterator().next().setId(1L);
//        when(ingrediantRepository.findByIdAndRecipeId(anyLong(),anyLong())).thenReturn(ingrediantOptional);
        when(recipeRepository.findById(anyLong())).thenReturn(recipe);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        IngrediantCommand savedCommand = service.saveIngredientCommand(command);
        assertEquals(1L, savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));

    }
}