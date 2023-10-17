package com.biblioteca.biblioteca_app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.biblioteca_app.entities.Autor;
import com.biblioteca.biblioteca_app.exceptions.MyException;
import com.biblioteca.biblioteca_app.repository.AutorRepository;

@Service
public class AutorService {

    @Autowired
    AutorRepository autorRepository;

    @Transactional
    public void crearAutor(String name) throws MyException {
        validate(name);
        Autor autor = new Autor();
        autor.setName(name);

        autorRepository.save(autor);
    }

    @Transactional(readOnly = true)
    public List<Autor> listarAutores() {
        List<Autor> autores = new ArrayList<>();
        autores = autorRepository.findAll();
        return autores;
    }

    @Transactional
    public void modifyAutor(String name, String id) throws MyException {
        validate(name);
        Optional<Autor> answer = autorRepository.findById(id);

        if (answer.isPresent()) {
            Autor autor = answer.get();
            autor.setName(name);
            autorRepository.save(autor);
        }
    }

    @Transactional(readOnly = true)
    public Autor getOne(String id){
        return autorRepository.getOne(id);
    }

    private void validate(String name) throws MyException {
        
        if (name.isEmpty() || name == null) {
            throw new MyException("el nombre no puede ser nulo o estar vacio");
        }
    }
}
