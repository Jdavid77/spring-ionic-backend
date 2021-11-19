package com.joaodavid.springionic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.joaodavid.springionic.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {

}
