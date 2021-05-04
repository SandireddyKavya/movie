package com.anvesh.recepieapp.bootstrap;


import com.anvesh.recepieapp.domain.*;
import com.anvesh.recepieapp.repositories.CategorierRepo;
import com.anvesh.recepieapp.repositories.RecipeRepository;
import com.anvesh.recepieapp.repositories.UnitOfMeasureRepo;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//TO Initilaize data into database as soon as application starts

@Component
public class RecipeDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepo unitOfMeasurment;
    private final CategorierRepo categorierRepo;

    //recipe repository is injected into throuh context
    public RecipeDataLoader(RecipeRepository recipeRepository, UnitOfMeasureRepo unitOfMeasurment, CategorierRepo categorierRepo) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasurment = unitOfMeasurment;
        this.categorierRepo = categorierRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        //saves the list of repositories one by into the database
        recipeRepository.saveAll(InitializeRecipes());
    }

    //Make a list of repositoris to save in database
    public List<Recipe> InitializeRecipes() {
        List<Recipe> recipes = new ArrayList<>();


        //creating two recipies
        Recipe coffee = new Recipe();
        Recipe omlet = new Recipe();


        //Getting unit of measurments from table which are stored in database

        Optional<UnitOfMeasurment> optionalEgg = unitOfMeasurment.findByUom("piece");
        Optional<UnitOfMeasurment> optionTeaspoon = unitOfMeasurment.findByUom("Teaspoon");
        Optional<UnitOfMeasurment> optionalGlass = unitOfMeasurment.findByUom("glass");
        Optional<UnitOfMeasurment> optionalPinch = unitOfMeasurment.findByUom("pinch");
        Optional<UnitOfMeasurment> optionalBow = unitOfMeasurment.findByUom("bowl");

        //Getting actual univtOfmeasurements from optional values
        UnitOfMeasurment eggMeasure = getMeasure(optionalEgg);
        UnitOfMeasurment teaspoon = getMeasure(optionTeaspoon);
        UnitOfMeasurment glass = getMeasure(optionalGlass);
        UnitOfMeasurment pinch = getMeasure(optionalPinch);
        UnitOfMeasurment bowl = getMeasure(optionalBow);


        //creating Ingrediants for recipies
        Ingrediant egg = new Ingrediant("Egg", eggMeasure, new BigDecimal(2), omlet);
        Ingrediant pepper = new Ingrediant("Pepper", pinch, new BigDecimal(1), omlet);
        Ingrediant salt = new Ingrediant("Salt", pinch, new BigDecimal(1), omlet);
        Ingrediant onions = new Ingrediant("onions", bowl, new BigDecimal(1), omlet);
        Ingrediant sugar = new Ingrediant("Sugar", teaspoon, new BigDecimal(2), coffee);
        Ingrediant milk = new Ingrediant("Milk", glass, new BigDecimal(2), coffee);
        Ingrediant coffePowder = new Ingrediant("cofeePowder", teaspoon, new BigDecimal(1), coffee);
        Ingrediant water = new Ingrediant("Water", glass, new BigDecimal(1), coffee);


        //Getting categories from DatabAse


        //Adding Ingrediants to recipes
        coffee.getIngrediants().add(water);
        coffee.getIngrediants().add(milk);
        coffee.getIngrediants().add(coffePowder);
        coffee.getIngrediants().add(sugar);

        omlet.getIngrediants().add(egg);
        omlet.getIngrediants().add(pepper);
        omlet.getIngrediants().add(salt);
        omlet.getIngrediants().add(onions);

        Notes coffeNotes = new Notes();
        coffeNotes.setRecipeNotes("Take a glasse of water in a bowl\n" +
                "put one teaspoon of coffe powder into the sam bowl\n" +
                "heat the contents for 10 mins under midium flame\n" +
                "add suger after 5 minutes of heating\n" +
                "Add two glasses of milk into the heating contents\n" +
                "heat it for another 4 minutes\n" +
                "switch of stove and serve it in glasses");

        coffeNotes.setRecipe(coffee);

        Notes eggNotes = new Notes();
        eggNotes.setRecipeNotes("Take two eggs\n" +
                "put some oil on the pan and let it heat on the stove\n" +
                "breaks the eggs and pour the yolk on the hot pan\n" +
                "spread the contents on the pan evenely\n" +
                "put some pepper above the contents\n" +
                "let it heat for 5 minutes and the serve it");
        eggNotes.setRecipe(omlet);

        coffee.setCookTime(15);
        coffee.setDescription("Making of worlds best coffee");
        coffee.setDifficulty(Difficulty.EASY);
        coffee.setNotes(coffeNotes);
        coffee.setPrepTime(15);
        coffee.setDirections("Take a glasse of water in a bowl\n" +
                "put one teaspoon of coffe powder into the sam bowl\n" +
                "heat the contents for 10 mins under midium flame\n" +
                "add suger after 5 minutes of heating\n" +
                "Add two glasses of milk into the heating contents\n" +
                "heat it for another 4 minutes\n" +
                "switch of stove and serve it in glasses");


        Categorie amercian = getCategory("Amercian");
        Categorie indian = getCategory("Indian");
        amercian.getRecipes().add(coffee);
        indian.getRecipes().add(omlet);
        coffee.getCategories().add(indian);
        coffee.getCategories().add(amercian);


        omlet.setCookTime(15);
        omlet.setDescription("Making of worlds best Omlet");
        omlet.setDifficulty(Difficulty.HARD);
        omlet.setNotes(eggNotes);
        omlet.setPrepTime(15);
        omlet.setDirections("Take two eggs\n" +
                "put some oil on the pan and let it heat on the stove\n" +
                "breaks the eggs and pour the yolk on the hot pan\n" +
                "spread the contents on the pan evenely\n" +
                "put some pepper above the contents\n" +
                "let it heat for 5 minutes and the serve it");

        omlet.getCategories().add(indian);
        recipes.add(omlet);
        recipes.add(coffee);

        return recipes;
    }

    private UnitOfMeasurment getMeasure(Optional<UnitOfMeasurment> optional) {
        return optional.orElseThrow(() -> new RuntimeException("Optinal not found : " + optional));
    }

    private Categorie getCategory(String name) {
        return categorierRepo.findByDescription(name).orElseThrow(() -> new RuntimeException("Categorie not found : " + name));
    }

}
