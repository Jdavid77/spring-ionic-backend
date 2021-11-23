package com.joaodavid.springionic.repository;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaodavid.springionic.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

	@Transactional(readOnly=true)
	Cliente findByEmail(String email);
}
