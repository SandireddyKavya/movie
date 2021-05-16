package com.anvesh.recepieapp.controllers;

import com.anvesh.recepieapp.services.ImageService;
import com.anvesh.recepieapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class ImageControllerTest {

    @InjectMocks
    ImageController controller;
    @Mock
    RecipeService recipeService;
    @Mock
    ImageService imageService;

    MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void displayImageUploadForm() throws Exception {
        mvc.perform(get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/imageupload"))
                .andExpect(model().attributeExists("recipeId"));
    }

    @Test
    void handleImage() throws Exception {
        MockMultipartFile file = new MockMultipartFile("imagefile", "testing.txt", "text/plain", "hello".getBytes());
        mvc.perform(multipart("/recipe/1/image").file(file))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/show/1"));
        verify(imageService, times(1)).saveImageFile(anyLong(), any());
    }
}