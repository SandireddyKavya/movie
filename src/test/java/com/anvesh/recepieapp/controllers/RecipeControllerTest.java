package com.anvesh.recepieapp.controllers;

import com.anvesh.recepieapp.domain.Recipe;
import com.anvesh.recepieapp.services.RecipeService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
public class RecipeControllerTest {

    @Mock
    RecipeService service;
    @InjectMocks
    RecipeController controller;
    MockMvc mvc;

    @BeforeEach
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getRecipie() throws Exception {
        Recipe r = new Recipe();
        r.setId(1L);
        when(service.findById(anyLong())).thenReturn(r);
        mvc.perform(get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/show"));
    }

    /*  @Test
      public void saveOrUpdate() throws Exception {
          RecipeCommand command = new RecipeCommand();
          command.setId(1L);
          when(service.saveReciepeCommand(command)).thenReturn(command);
  //        mvc.perform()
      }
  */
    @Test
    public void deleteTest() throws Exception {
//        When
        mvc.perform(get("/recipe/1/delete"))
//                Then
                .andExpect(status().is3xxRedirection())
//                Then
                .andExpect(view().name("redirect:/index"));
        verify(service, times(1)).deletById(anyLong());
    }
}