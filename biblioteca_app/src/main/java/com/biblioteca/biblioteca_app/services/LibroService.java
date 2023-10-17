package com.biblioteca.biblioteca_app.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.biblioteca_app.entities.Autor;
import com.biblioteca.biblioteca_app.entities.Editorial;
import com.biblioteca.biblioteca_app.entities.Libro;
import com.biblioteca.biblioteca_app.exceptions.MyException;
import com.biblioteca.biblioteca_app.repository.AutorRepository;
import com.biblioteca.biblioteca_app.repository.EditorialRepository;
import com.biblioteca.biblioteca_app.repository.LibroRepository;


@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private EditorialRepository editorialRepository;

    @Transactional
    public void crearLibro(Long ISBN, String title, Integer copies, String idAutor, String idEditorial)
            throws MyException {

        validate(ISBN, title, copies, idAutor, idEditorial);

        Autor autor = autorRepository.findById(idAutor).get();
        Editorial editorial = editorialRepository.findById(idEditorial).get();

        Libro libro = new Libro();
        libro.setISBN(ISBN);
        libro.setTitle(title);
        libro.setCopies(copies);
        libro.setAlta(new Date());
        libro.setAutor(autor);
        libro.setEditorial(editorial);

        libroRepository.save(libro);
    }

    @Transactional(readOnly = true)
    public List<Libro> listarLibros() {
        List<Libro> libros = new ArrayList<>();
        libros = libroRepository.findAll();
        return libros;
    }

    // MODIFICA LIBROS
    @Transactional
    public void modifyLibro(Long ISBN, String title, Integer copies, String idAutor, String idEditorial)
            throws MyException {

        validate(ISBN, title, copies, idAutor, idEditorial);

        Optional<Libro> answer = libroRepository.findById(ISBN);
        Optional<Autor> answerAutor = autorRepository.findById(idAutor);
        Optional<Editorial> answerEditorial = editorialRepository.findById(idEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if (answerAutor.isPresent()) {
            autor = answerAutor.get();
        }

        if (answerEditorial.isPresent()) {
            editorial = answerEditorial.get();
        }

        if (answer.isPresent()) {
            Libro libro = answer.get();
            libro.setTitle(title);
            libro.setCopies(copies);
            libro.setAutor(autor);
            libro.setEditorial(editorial);

            libroRepository.save(libro);
        }
    }

    @Transactional(readOnly = true)
    public Libro getOne(Long isbn){
       return libroRepository.getOne(isbn);
    }

    // VALIDA EL INGRESO DE DATOS
    private void validate(Long ISBN, String title, Integer copies, String idAutor, String idEditorial)
            throws MyException {
        if (ISBN == null) {
            throw new MyException("El isbn no puede ser nulo!");
        }
        if (title == null || title.isEmpty()) {
            throw new MyException("El titulo no puede ser nulo!");
        }
        if (copies == null) {
            throw new MyException("El numero de ejemplares no puede ser nulo!");
        }
        if (idAutor == null || idAutor.isEmpty()) {
            throw new MyException("El autor no puede ser nulo!");
        }
        if (idEditorial == null || idEditorial.isEmpty()) {
            throw new MyException("La editorial no puede ser nulo!");
        }
    }
}
