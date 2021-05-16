package com.anvesh.recepieapp.services;

import com.anvesh.recepieapp.domain.Recipe;
import com.anvesh.recepieapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class ImageServiceImp implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImp(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file) {
        log.debug("Image is being Uploaded");
        try {
            Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
            Byte[] bytesObject = new Byte[file.getBytes().length];
            int k = 0;
            for (byte i : file.getBytes()) {
                bytesObject[k++] = i;
            }
            if (recipeOptional.isPresent()) {
                Recipe recipe = recipeOptional.get();
                recipe.setImage(bytesObject);
                recipeRepository.save(recipe);
            } else {
                throw new RuntimeException("Recipe Not found : " + recipeId);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        log.debug("Image Uploaded");
    }
}
