package com.biblioteca.biblioteca_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.biblioteca_app.entities.Editorial;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, String> {

}
