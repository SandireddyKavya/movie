package com.anvesh.recepieapp.controllers;


import com.anvesh.recepieapp.domain.Recipe;
import com.anvesh.recepieapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

}

