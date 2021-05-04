package com.anvesh.recepieapp.controllers;

import com.anvesh.recepieapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index.html"})
    public String recipeList(Model model) {
        model.addAttribute("recipes", recipeService.getRecipies());
        return "index";
    }
}
