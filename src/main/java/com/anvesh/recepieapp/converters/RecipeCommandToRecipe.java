package com.anvesh.recepieapp.converters;

import com.anvesh.recepieapp.dataTransfers.RecipeCommand;
import com.anvesh.recepieapp.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final IngrediantCommandToIngrediant toIngrediant;
    private CategoreiCommandToCategorie toCategorie;
    private NotesCommandToNotes toNotes;

    public RecipeCommandToRecipe(IngrediantCommandToIngrediant toIngrediant, CategoreiCommandToCategorie toCategorie, NotesCommandToNotes toNotes) {
        this.toNotes = toNotes;
        this.toIngrediant = toIngrediant;
        this.toCategorie = toCategorie;
    }

    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        Recipe recipe = new Recipe();
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setId(recipeCommand.getId());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setSavings(recipeCommand.getServings());
        recipe.setSource(recipeCommand.getSource());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setImage(recipeCommand.getImage());
        recipe.setIngrediants(recipeCommand.getIngrediants()
                .stream().map(toIngrediant::convert)
                .collect(Collectors.toSet()));
        recipe.setCategories(recipeCommand.getCategories()
                .stream().map(x -> toCategorie.convert(x))
                .collect(Collectors.toSet()));
        recipe.setNotes(toNotes.convert(recipeCommand.getNotes()));
        return recipe;
    }
}
