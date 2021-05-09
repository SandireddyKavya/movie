package com.anvesh.recepieapp.services;


import com.anvesh.recepieapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipies();

    Recipe findById(Long l);
}
