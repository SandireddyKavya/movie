package com.anvesh.recepieapp.controllers;


import com.anvesh.recepieapp.services.IngrediantService;
import com.anvesh.recepieapp.services.RecipeService;
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
    private final IngrediantService ingrediantService;

    public IngrediantController(RecipeService service, IngrediantService ingrediantService) {
        this.service = service;
        this.ingrediantService = ingrediantService;
    }

    @GetMapping
    @RequestMapping("recipe/{id}/ingredients")
    public String listOfIngrediants(@PathVariable String id, Model model) {
        model.addAttribute("recipe", service.commandFindyById(Long.valueOf(id)));
        return "recipe/ingrediants/list";
    }

    @GetMapping
    @RequestMapping({"recipe/{rid}/ingredients/{ingid}/show"})
    public String showIngrediant(@PathVariable String rid, @PathVariable String ingid, Model model) {
        model.addAttribute("ingredient", ingrediantService.findByIngrediantIdAndRecipeId(Long.valueOf(ingid), Long.valueOf(rid)));
        return "recipe/ingrediants/show";
    }
}
