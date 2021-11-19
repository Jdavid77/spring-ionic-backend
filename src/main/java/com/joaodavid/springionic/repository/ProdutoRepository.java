package com.joaodavid.springionic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.joaodavid.springionic.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

}
