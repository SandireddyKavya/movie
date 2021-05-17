package com.anvesh.recepieapp.converters;

import com.anvesh.recepieapp.dataTransfers.RecipeCommand;
import com.anvesh.recepieapp.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private final IngredianteToIngredianteCommand toIngredianteCommand;
    private final CategoreiToCategorieCommand toCategorieCommand;
    private final NotesToNotesCommand toNotesCommand;

    public RecipeToRecipeCommand(IngredianteToIngredianteCommand toIngredianteCommand, CategoreiToCategorieCommand toCategorieCommand, NotesToNotesCommand toNotesCommand) {
        this.toIngredianteCommand = toIngredianteCommand;
        this.toCategorieCommand = toCategorieCommand;
        this.toNotesCommand = toNotesCommand;
    }

    @Override
    public RecipeCommand convert(Recipe recipe) {
        RecipeCommand command = new RecipeCommand();
        command.setUrl(recipe.getUrl());
        command.setId(recipe.getId());
        command.setDescription(recipe.getDescription());
        command.setPrepTime(recipe.getPrepTime());
        command.setServings(recipe.getSavings());
        command.setSource(recipe.getSource());
        command.setCookTime(recipe.getCookTime());
        command.setDirections(recipe.getDirections());
        command.setDifficulty(recipe.getDifficulty());
        command.setImage(recipe.getImage());
        command.setIngrediants(recipe.getIngrediants().stream().map(toIngredianteCommand::convert).collect(Collectors.toSet()));
        command.setCategories(recipe.getCategories().stream().map(toCategorieCommand::convert).collect(Collectors.toSet()));
        command.setNotes(toNotesCommand.convert(recipe.getNotes()));
        return command;
    }
}
