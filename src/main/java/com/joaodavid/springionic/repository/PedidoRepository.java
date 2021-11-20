package com.joaodavid.springionic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.joaodavid.springionic.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {

}
