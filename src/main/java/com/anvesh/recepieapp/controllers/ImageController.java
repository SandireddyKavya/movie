package com.anvesh.recepieapp.controllers;


import com.anvesh.recepieapp.services.ImageService;
import com.anvesh.recepieapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

    private final RecipeService recipeRepository;
    private final ImageService imageService;

    public ImageController(RecipeService recipeRepository, ImageService imageService) {
        this.recipeRepository = recipeRepository;
        this.imageService = imageService;
    }

    @GetMapping("recipe/{recipe_id}/image")
    public String displayImageUploadForm(@PathVariable Long recipe_id, Model model) {
        model.addAttribute("recipeId", recipe_id);
        return "recipe/imageupload";
    }

    @PostMapping("recipe/{id}/image")
    public String handleImage(@PathVariable Long id, @RequestParam("imagefile") MultipartFile file) {
        imageService.saveImageFile(id, file);
        return "redirect:/recipe/show/" + id;
    }

}
