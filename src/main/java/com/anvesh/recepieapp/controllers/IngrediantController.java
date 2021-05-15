package com.anvesh.recepieapp.controllers;


import com.anvesh.recepieapp.dataTransfers.IngrediantCommand;
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
    private final RecipeService service;
    private final IngrediantService ingrediantService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngrediantController(RecipeService service, IngrediantService ingrediantService, UnitOfMeasureService unitOfMeasureService) {
        this.service = service;
        this.ingrediantService = ingrediantService;
        this.unitOfMeasureService = unitOfMeasureService;
    }


    //    TO display all ingrediants for a list
    @GetMapping
    @RequestMapping("recipe/{id}/ingredients")
    public String listOfIngrediants(@PathVariable String id, Model model) {
        model.addAttribute("recipe", service.commandFindyById(Long.valueOf(id)));
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
        command.setRecipeId(recipe_id);
        System.out.println("In Save or update method " + command.getRecipeId());
        IngrediantCommand savedCommand = ingrediantService.saveIngredientCommand(command);
        log.debug("SAving Ingrediant to database");
        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredients/" + savedCommand.getId() + "/show";
    }
}
