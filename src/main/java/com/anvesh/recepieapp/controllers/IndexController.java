package com.anvesh.recepieapp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index.html"})
    public String getIndex() {
        System.out.println("Devtool is very fast");
        return "index";
    }
}
