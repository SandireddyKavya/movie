package com.anvesh.recepieapp.services;

import com.anvesh.recepieapp.converters.IngrediantCommandToIngrediant;
import com.anvesh.recepieapp.converters.IngredianteToIngredianteCommand;
import com.anvesh.recepieapp.dataTransfers.IngrediantCommand;
import com.anvesh.recepieapp.domain.Ingrediant;
import com.anvesh.recepieapp.domain.Recipe;
import com.anvesh.recepieapp.repositories.IngrediantRepo;
import com.anvesh.recepieapp.repositories.RecipeRepository;
import com.anvesh.recepieapp.repositories.UnitOfMeasureRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
public class IngrediantServiceImp implements IngrediantService {
    private final IngrediantRepo ingrediantRepository;
    private final IngredianteToIngredianteCommand toIngredianteCommand;
    private final IngrediantCommandToIngrediant toIngrediant;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepo unitOfMeasureRepo;

    public IngrediantServiceImp(IngrediantRepo repository, IngredianteToIngredianteCommand toIngredianteCommand, IngrediantCommandToIngrediant toIngrediant, RecipeRepository recipeRepository, UnitOfMeasureRepo unitOfMeasureRepo) {
        this.ingrediantRepository = repository;
        this.toIngredianteCommand = toIngredianteCommand;
        this.toIngrediant = toIngrediant;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepo = unitOfMeasureRepo;
    }

    @Override
    public IngrediantCommand findByIngrediantIdAndRecipeId(Long id, Long recipe_id) {
        Optional<Ingrediant> optional = ingrediantRepository.findByIdAndRecipeId(id, recipe_id);
//        System.out.println("Option is not empty");
        Ingrediant ingrediant = optional.orElseThrow(RuntimeException::new);

        IngrediantCommand command = toIngredianteCommand.convert(ingrediant);
        if (command == null) {
            System.out.println("command is null");
        }
        return command;
    }

// To save or update ingredient to database
@Override
public IngrediantCommand saveIngredientCommand(IngrediantCommand command) {
    Optional<Recipe> optionalRecipe = recipeRepository.findById(command.getRecipeId());
    if (optionalRecipe.isEmpty()) {
        log.debug("REcipe is no present in ingrediant " + command.getRecipeId());
        return new IngrediantCommand();
    }
    Recipe recipe = optionalRecipe.get();
    Optional<Ingrediant> ingrediantOptional = recipe.getIngrediants().stream()
            .filter(ingrediant -> ingrediant.getId().equals(command.getId()))
            .findFirst();
//                ingrediantRepository.findByIdAndRecipeId(command.getId(), recipe.getId());
    if (ingrediantOptional.isPresent()) {
        Ingrediant ingredientFound = ingrediantOptional.get();
        ingredientFound.setDescription(command.getDescription());
        ingredientFound.setAmount(command.getAmount());
        System.out.println("Unit of measure Id " + command.getMeasurment().getId());
        ingredientFound.setMeasurment(unitOfMeasureRepo
                .findById(command.getMeasurment().getId())
                .orElseThrow(() -> new RuntimeException("UOM NOT FOUND")));
    } else {
//            Add new Ingredient to recipe
        recipe.addIngredient(toIngrediant.convert(command));
//            recipe.getIngrediants().add(toIngrediant.convert(command));
    }

    Recipe savedRecdipe = recipeRepository.save(recipe);
//        if it is a new ingredient, command object doesn't contains a id,since it is not saved in db
    Optional<Ingrediant> savedIngredient = savedRecdipe.getIngrediants().stream().filter(ingrediant -> ingrediant.getId().equals(command.getId())).findFirst();
    if (savedIngredient.isEmpty()) {
        savedIngredient = savedRecdipe.getIngrediants().stream().filter(ingrediant -> ingrediant.getDescription().equals(command.getDescription()))
                .filter(ingrediant -> ingrediant.getAmount().equals(command.getAmount()))
                .filter(ingrediant -> ingrediant.getMeasurment().getId().equals(command.getMeasurment().getId())).findFirst();
    }

    return toIngredianteCommand.convert(savedIngredient.get());

}
}
