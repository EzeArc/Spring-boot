package com.biblioteca.biblioteca_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biblioteca.biblioteca_app.exceptions.MyException;
import com.biblioteca.biblioteca_app.services.AutorService;
import com.biblioteca.biblioteca_app.services.EditorialService;
import com.biblioteca.biblioteca_app.services.LibroService;

@Controller
@RequestMapping("/libro")
public class LibroController {
    @Autowired
    private LibroService libroService;
    @Autowired
    private AutorService autorService;
    @Autowired
    private EditorialService editorialService;

    @GetMapping
    public String registrar() {
        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam Long ISBN, @RequestParam String title, @RequestParam Integer copies,
            @RequestParam String idAutor, @RequestParam String idEditorial, ModelMap modelo) {
        try {
            libroService.crearLibro(ISBN, title, copies, idAutor, idEditorial);
            modelo.put("Exito", "El libro fue cargado exitosamente");
        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            return "libro_form.html";
        }
        return "index.html";
    }
}
