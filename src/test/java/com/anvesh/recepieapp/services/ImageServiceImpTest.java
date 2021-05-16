package com.anvesh.recepieapp.services;

import com.anvesh.recepieapp.domain.Recipe;
import com.anvesh.recepieapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageServiceImpTest {

    @InjectMocks
    ImageServiceImp imageServiceImp;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void saveImageFile() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        MultipartFile file = new MockMultipartFile("imagefile", "testne.txt", "text/plain", "hello this anvesh".getBytes());
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
        imageServiceImp.saveImageFile(1L, file);
        verify(recipeRepository, times(1)).save(same(recipe));
        assertEquals(file.getBytes().length, recipe.getImage().length);

    }
}