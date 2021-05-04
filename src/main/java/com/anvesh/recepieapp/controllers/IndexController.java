package com.anvesh.recepieapp.controllers;


import com.anvesh.recepieapp.domain.Categorie;
import com.anvesh.recepieapp.domain.UnitOfMeasurment;
import com.anvesh.recepieapp.repositories.CategorierRepo;
import com.anvesh.recepieapp.repositories.UnitOfMeasureRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {
    private CategorierRepo categorierRepo;
    private UnitOfMeasureRepo unitOfMeasureRepo;

    public IndexController(CategorierRepo categorierRepo, UnitOfMeasureRepo unitOfMeasureRepo) {
        this.categorierRepo = categorierRepo;
        this.unitOfMeasureRepo = unitOfMeasureRepo;
    }

    @RequestMapping({"", "/", "/index.html"})
    public String getIndex() {
        Optional<Categorie> categorie = categorierRepo.findByDescription("Italian");
        Optional<UnitOfMeasurment> um = unitOfMeasureRepo.findByUom("glass");
        System.out.println("Categorie Id " + categorie.get().getId() + "\n"
                + "UNit Of measurment Id " + um.get().getId());
        return "index";
    }
}
