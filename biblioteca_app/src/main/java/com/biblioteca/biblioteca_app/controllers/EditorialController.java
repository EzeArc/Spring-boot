package com.biblioteca.biblioteca_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biblioteca.biblioteca_app.services.EditorialService;

@Controller
@RequestMapping("/editorial")
public class EditorialController {
    
    @Autowired
    private EditorialService editorialService;

     @GetMapping("/registrar")
    public String registrar(){
        return "editorial_form.html";
    }
}
