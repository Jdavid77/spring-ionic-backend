package com.joaodavid.springionic.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.joaodavid.springionic.model.Cliente;
import com.joaodavid.springionic.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
	
	@Transactional(readOnly=true)
	Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);

}
