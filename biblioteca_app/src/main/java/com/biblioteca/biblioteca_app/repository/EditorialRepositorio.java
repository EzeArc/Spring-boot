package com.biblioteca.biblioteca_app.repository;

import com.biblioteca.biblioteca_app.entities.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial,String> {

}