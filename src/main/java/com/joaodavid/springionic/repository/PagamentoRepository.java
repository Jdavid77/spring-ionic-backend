package com.joaodavid.springionic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.joaodavid.springionic.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento,Integer> {

}
