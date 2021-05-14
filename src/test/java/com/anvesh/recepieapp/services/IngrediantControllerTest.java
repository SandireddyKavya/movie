package com.anvesh.recepieapp.services;

import com.anvesh.recepieapp.dataTransfers.RecipeCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class IngrediantControllerTest {

    @InjectMocks
    IngrediantController controller;

    @Mock
    RecipeService service;
    MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listOfIngrediants() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);
        when(service.commandFindyById(anyLong())).thenReturn(command);
//        when
        mvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingrediants/list"))
                .andExpect(model().attributeExists("recipe"));
        verify(service, times(1)).commandFindyById(anyLong());
    }
}