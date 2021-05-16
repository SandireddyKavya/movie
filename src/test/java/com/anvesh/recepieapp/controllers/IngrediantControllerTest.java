package com.anvesh.recepieapp.controllers;

import com.anvesh.recepieapp.dataTransfers.IngrediantCommand;
import com.anvesh.recepieapp.dataTransfers.RecipeCommand;
import com.anvesh.recepieapp.services.IngrediantService;
import com.anvesh.recepieapp.services.RecipeService;
import com.anvesh.recepieapp.services.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class IngrediantControllerTest {

    @InjectMocks
    IngrediantController controller;
    @Mock
    UnitOfMeasureService unitOfMeasureService;

    @Mock
    RecipeService recipeService;
    @Mock
    IngrediantService ingrediantService;
    MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listOfIngrediants() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);
        when(recipeService.commandFindyById(anyLong())).thenReturn(command);
//        when
        mvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingrediants/list"))
                .andExpect(model().attributeExists("recipe"));
        verify(recipeService, times(1)).commandFindyById(anyLong());
    }

    @Test
    void showIngrediant() throws Exception {
        IngrediantCommand command = new IngrediantCommand();
        command.setId(1L);
        command.setRecipeId(3L);
        when(ingrediantService.findByIngrediantIdAndRecipeId(anyLong(), anyLong())).thenReturn(command);
        mvc.perform(get("/recipe/1/ingredients/1/show")).andExpect(status().isOk())
                .andExpect(view().name("recipe/ingrediants/show"))
                .andExpect(model().attributeExists("ingredient"));
        verify(ingrediantService, times(1)).findByIngrediantIdAndRecipeId(anyLong(), anyLong());
    }

    @Test
    void newIngredient() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);
        when(unitOfMeasureService.findAllUOM()).thenReturn(new HashSet<>());
        when(recipeService.commandFindyById(anyLong())).thenReturn(command);
        mvc.perform(get("/recipe/1/ingredient/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingrediants/ingrediantform"))
                .andExpect(model().attributeExists("listUOM"))
                .andExpect(model().attributeExists("ingredient"));
        verify(recipeService, times(1)).commandFindyById(anyLong());
        verify(unitOfMeasureService, times(1)).findAllUOM();
    }
}