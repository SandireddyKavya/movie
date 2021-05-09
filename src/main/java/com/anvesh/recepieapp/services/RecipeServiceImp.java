package com.anvesh.recepieapp.services;

import com.anvesh.recepieapp.domain.Recipe;
import com.anvesh.recepieapp.repositories.RecipeRepository;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImp implements RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeServiceImp(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipies() {
//        log.debug("hello there");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(Long l) {
        Optional<Recipe> recipe = recipeRepository.findById(l);
        return recipe.orElseThrow(() -> new RuntimeException("Recipie Not found"));
    }
}
