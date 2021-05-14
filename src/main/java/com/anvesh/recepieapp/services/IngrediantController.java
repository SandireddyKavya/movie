package com.anvesh.recepieapp.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IngrediantController {
    private final RecipeService service;

    public IngrediantController(RecipeService service) {
        this.service = service;
    }

    @GetMapping
    @RequestMapping("recipe/{id}/ingredients")
    public String listOfIngrediants(@PathVariable String id, Model model) {
        model.addAttribute("recipe", service.commandFindyById(Long.valueOf(id)));
        return "recipe/ingrediants/list";
    }
}
