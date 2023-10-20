package com.biblioteca.biblioteca_app.services;

import com.biblioteca.biblioteca_app.entities.Autor;
import com.biblioteca.biblioteca_app.exceptions.MiException;
import com.biblioteca.biblioteca_app.repository.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    public void crearAutor(String nombre) throws MiException {
        validar(nombre);
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autorRepositorio.save(autor);
    }

    @Transactional(readOnly = true)
    public List<Autor> listarAutores() {
        return autorRepositorio.findAll();
    }

    public void modificarAutor(String nombre, String id) throws MiException {
        validar(nombre);
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        respuesta.ifPresent(autor -> {
            autor.setNombre(nombre);
            autorRepositorio.save(autor);
        });
    }

    @Transactional(readOnly = true)
    public Autor getOne(String id) {
        return autorRepositorio.getReferenceById(id);
    }

    public void eliminar(String id) throws MiException {
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        respuesta.ifPresent(autorRepositorio::delete);
    }

    private void validar(String nombre) throws MiException {
        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("El nombre no puede ser nulo o estar vacío");
        }
    }
}
