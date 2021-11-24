package com.joaodavid.springionic.service;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaodavid.springionic.model.ItemPedido;
import com.joaodavid.springionic.model.PagamentoComBoleto;
import com.joaodavid.springionic.model.Pedido;
import com.joaodavid.springionic.model.enums.EstadoPagamento;
import com.joaodavid.springionic.repository.ItemPedidoRepository;
import com.joaodavid.springionic.repository.PagamentoRepository;
import com.joaodavid.springionic.repository.PedidoRepository;
import com.joaodavid.springionic.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	public PedidoRepository repository;
	
	@Autowired
	public BoletoService service;
	
	@Autowired
	public PagamentoRepository pagamentoRepository;
	
	@Autowired
	public ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itempedidorepository;
	
	@Autowired
	private ClienteService clienteservice;
	
	public Pedido findById(Integer id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido Insert(Pedido obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.setCliente(clienteservice.findById(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			service.preencherPagamentoComBoleto(pagto,obj.getInstant());
			
		}
		obj = repository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.findById(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itempedidorepository.saveAll(obj.getItens());
		System.out.println(obj);
		return obj;
	}
}
