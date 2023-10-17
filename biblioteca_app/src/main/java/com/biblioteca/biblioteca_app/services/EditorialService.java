package com.biblioteca.biblioteca_app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.biblioteca_app.entities.Editorial;
import com.biblioteca.biblioteca_app.exceptions.MyException;
import com.biblioteca.biblioteca_app.repository.EditorialRepository;

@Service
public class EditorialService {

    @Autowired
    EditorialRepository editorialRepository;

    @Transactional
    public void crearEditorial(String name) throws MyException {
        validate(name);
        Editorial editorial = new Editorial();
        editorial.setName(name);

        editorialRepository.save(editorial);
    }

    @Transactional(readOnly = true)
    public List<Editorial> listarEditoriales() {
        List<Editorial> editoriales = new ArrayList<>();
        editoriales = editorialRepository.findAll();
        return editoriales;
    }

    @Transactional
    public void modifyEditorial(String name, String id) throws MyException {
        validate(name);
        Optional<Editorial> answer = editorialRepository.findById(id);

        if (answer.isPresent()) {
            Editorial editorial = answer.get();
            editorial.setName(name);

            editorialRepository.save(editorial);
        }
    }

    @Transactional(readOnly = true)
    public Editorial getOne(String id) {
        return editorialRepository.getOne(id);
    }

    private void validate(String name) throws MyException {

        if (name.isEmpty() || name == null) {
            throw new MyException("el nombre de la editorial no puede ser nulo o estar vacio");
        }
    }
}
