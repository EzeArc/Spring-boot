package com.biblioteca.biblioteca_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biblioteca.biblioteca_app.entities.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l WHERE l.title = : title")
    public Libro buscarPorTitle(@Param("title") String title);

    @Query("SELECT l FROM Libro l WHERE l.autor.name = : name")
    public List<Libro> buscarPorName(@Param("name") String name);
}
