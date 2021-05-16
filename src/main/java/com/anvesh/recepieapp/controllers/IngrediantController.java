package com.anvesh.recepieapp.controllers;


import com.anvesh.recepieapp.dataTransfers.IngrediantCommand;
import com.anvesh.recepieapp.dataTransfers.RecipeCommand;
import com.anvesh.recepieapp.dataTransfers.UnitOfMeasurementCommand;
import com.anvesh.recepieapp.services.IngrediantService;
import com.anvesh.recepieapp.services.RecipeService;
import com.anvesh.recepieapp.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class IngrediantController {
    private final RecipeService recipeService;
    private final IngrediantService ingrediantService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngrediantController(RecipeService recipeService, IngrediantService ingrediantService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingrediantService = ingrediantService;
        this.unitOfMeasureService = unitOfMeasureService;
    }


    //    TO display all ingrediants for a list
    @GetMapping
    @RequestMapping("recipe/{id}/ingredients")
    public String listOfIngrediants(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.commandFindyById(Long.valueOf(id)));
        return "recipe/ingrediants/list";
    }

    //    To show a particular Ingrediant
    @GetMapping
    @RequestMapping({"recipe/{rid}/ingredients/{ingid}/show"})
    public String showIngrediant(@PathVariable String rid, @PathVariable String ingid, Model model) {
        model.addAttribute("ingredient", ingrediantService.findByIngrediantIdAndRecipeId(Long.valueOf(ingid), Long.valueOf(rid)));
        return "recipe/ingrediants/show";
    }

    //    To autopuplate values into form after user clicks on update
    @GetMapping
    @RequestMapping({"recipe/{recipieId}/ingredients/{infId}/update"})
    public String updateIngrediant(@PathVariable String infId, @PathVariable String recipieId, Model model) {
        model.addAttribute("ingredient", ingrediantService.findByIngrediantIdAndRecipeId(Long.valueOf(infId), Long.valueOf(recipieId)));
        model.addAttribute("listUOM", unitOfMeasureService.findAllUOM());
        return "recipe/ingrediants/ingrediantform";
    }


    @PostMapping("recipe/{recipe_id}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngrediantCommand command, @PathVariable Long recipe_id) {
        System.out.println("In Save or update method " + command.getRecipeId());
        IngrediantCommand savedCommand = ingrediantService.saveIngredientCommand(command);
        log.debug("SAving Ingrediant to database");
        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredients/" + savedCommand.getId() + "/show";
    }

    @GetMapping("recipe/{recipe_id}/ingredient/new")
    public String newIngredient(Model model, @PathVariable Long recipe_id) {
        RecipeCommand command = recipeService.commandFindyById(recipe_id);
        IngrediantCommand command1 = new IngrediantCommand();
        command1.setRecipeId(recipe_id);
        command1.setMeasurment(new UnitOfMeasurementCommand());
//        Adding list of measures to choose in ui
        model.addAttribute("listUOM", unitOfMeasureService.findAllUOM());
//        Adding empty ingrediant object which is automatically populated in ui after user enters values
        model.addAttribute("ingredient", command1);
        return "recipe/ingrediants/ingrediantform";
    }


}
