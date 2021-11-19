package com.joaodavid.springionic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.joaodavid.springionic.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade,Integer> {

}
