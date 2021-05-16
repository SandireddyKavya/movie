package com.anvesh.recepieapp.services;

import com.anvesh.recepieapp.dataTransfers.IngrediantCommand;

public interface IngrediantService {
    IngrediantCommand findByIngrediantIdAndRecipeId(Long id, Long recipe_id);

    IngrediantCommand saveIngredientCommand(IngrediantCommand command);

    void deleteIngredientIdAndRecipeId(Long inredientId, Long recipeId);
}
