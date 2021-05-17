package com.anvesh.recepieapp.controllers;


import com.anvesh.recepieapp.dataTransfers.RecipeCommand;
import com.anvesh.recepieapp.services.ImageService;
import com.anvesh.recepieapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@Slf4j
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

    //    To get Image from db
    @GetMapping("recipe/{id}/recipeimage")
    public void getImageFromDb(@PathVariable Long id, HttpServletResponse response) throws IOException {
        RecipeCommand command = recipeRepository.commandFindyById(id);
        log.debug("got image from db " + command.getImage().length);
        byte[] bytes = new byte[command.getImage().length];
        int j = 0;
        for (Byte i : bytes) {
            bytes[j++] = i;
        }
        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(bytes);
        IOUtils.copy(is, response.getOutputStream());
        is.close();
    }


}
