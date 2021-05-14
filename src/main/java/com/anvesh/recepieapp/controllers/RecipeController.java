package com.anvesh.recepieapp.controllers;


import com.anvesh.recepieapp.dataTransfers.RecipeCommand;
import com.anvesh.recepieapp.domain.Recipe;
import com.anvesh.recepieapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {
    private final RecipeService service;

    public RecipeController(RecipeService service) {
        this.service = service;
    }

    @RequestMapping({"/recipe/show/{id}"})
    public String getRecipie(@PathVariable String id, Model model) {
        Recipe recipe = service.findById(Long.parseLong(id));
        model.addAttribute("recipe", recipe);
        return "/recipe/show";
    }


    @RequestMapping({"/recipe/new"})
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand recipe = service.saveReciepeCommand(command);
        return "redirect:/recipe/show/" + recipe.getId();

    }
}

