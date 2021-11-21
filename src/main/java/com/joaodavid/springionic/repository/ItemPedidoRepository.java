package com.joaodavid.springionic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.joaodavid.springionic.model.ItemPedido;
import com.joaodavid.springionic.model.ItemPedidoPK;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido,ItemPedidoPK> {

}
