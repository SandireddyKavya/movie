package com.anvesh.recepieapp.services;

import com.anvesh.recepieapp.converters.IngredianteToIngredianteCommand;
import com.anvesh.recepieapp.converters.UomToUomCommand;
import com.anvesh.recepieapp.dataTransfers.IngrediantCommand;
import com.anvesh.recepieapp.domain.Ingrediant;
import com.anvesh.recepieapp.domain.Recipe;
import com.anvesh.recepieapp.repositories.IngrediantRepo;
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
    IngrediantRepo repository;
    //    To inject uom into converter
    @Mock
    UomToUomCommand toUomCommand;
    //    TO inject converter into service implimentation or else it throws null pointer exception
    @InjectMocks
    IngredianteToIngredianteCommand toIngredianteCommand;

   /* public IngrediantServiceImpTest() {
        this.toIngredianteCommand = new IngredianteToIngredianteCommand(new UomToUomCommand());
    }*/

    @InjectMocks
    IngrediantServiceImp service;

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.initMocks(this);
        service = new IngrediantServiceImp(repository, toIngredianteCommand);
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
        when(repository.findByIdAndRecipeId(anyLong(), anyLong())).thenReturn(Optional.of(ingrediant2));
        IngrediantCommand command = service.findByIngrediantIdAndRecipeId(1L, 1L);
        assertNotNull(command);
        assertEquals(2L, command.getId());
        assertEquals(1L, command.getRecipeId());
        verify(repository, times(1)).findByIdAndRecipeId(anyLong(), anyLong());
    }
}