package com.joaodavid.springionic.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.joaodavid.springionic.model.Categoria;
import com.joaodavid.springionic.model.Cidade;
import com.joaodavid.springionic.model.Cliente;
import com.joaodavid.springionic.model.Endereco;
import com.joaodavid.springionic.model.Estado;
import com.joaodavid.springionic.model.ItemPedido;
import com.joaodavid.springionic.model.Pagamento;
import com.joaodavid.springionic.model.PagamentoComBoleto;
import com.joaodavid.springionic.model.PagamentoComCartao;
import com.joaodavid.springionic.model.Pedido;
import com.joaodavid.springionic.model.Produto;
import com.joaodavid.springionic.model.enums.EstadoPagamento;
import com.joaodavid.springionic.model.enums.Perfil;
import com.joaodavid.springionic.model.enums.TipoCliente;
import com.joaodavid.springionic.repository.CategoriaRepository;
import com.joaodavid.springionic.repository.CidadeRepository;
import com.joaodavid.springionic.repository.ClienteRepository;
import com.joaodavid.springionic.repository.EnderecoRepository;
import com.joaodavid.springionic.repository.EstadoRepository;
import com.joaodavid.springionic.repository.ItemPedidoRepository;
import com.joaodavid.springionic.repository.PagamentoRepository;
import com.joaodavid.springionic.repository.PedidoRepository;
import com.joaodavid.springionic.repository.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriarepository;
	
	@Autowired
	private ProdutoRepository produtorepository;
	
	@Autowired
	private CidadeRepository cidaderepository;
	
	@Autowired
	private EstadoRepository estadorepository;
	
	@Autowired
	private EnderecoRepository enderecorepository;
	
	@Autowired
	private ClienteRepository clienterepository;
	
	@Autowired
	private PedidoRepository pedidorepository;
	
	@Autowired
	private PagamentoRepository pagamentorepository;
	
	@Autowired
	private ItemPedidoRepository itempedidorepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public void InstantiateTestDatabase() throws ParseException{
		

		Categoria cat1 = new Categoria(null,"Inform??tica");
		Categoria cat2 = new Categoria(null,"Escrit??rio");
		Categoria cat3 = new Categoria(null,"Cama mesa e banho");
		Categoria cat4 = new Categoria(null,"Eletr??nicos");
		Categoria cat5 = new Categoria(null,"Jardinagem");
		Categoria cat6 = new Categoria(null,"Decora????o");
		Categoria cat7 = new Categoria(null,"Perfumaria");
		
		Produto p1 = new Produto(null,"Computador",2000.0);
		Produto p2 = new Produto(null,"Impressora",800.0);
		Produto p3 = new Produto(null,"Mouse",80.0);
		Produto p4 = new Produto(null,"Mesa de escrit??rio",300.00);
		Produto p5 = new Produto(null,"Toalha",50.00);
		Produto p6 = new Produto(null,"Colcha",200.00);
		Produto p7 = new Produto(null,"Tv true color",1200.00);
		Produto p8 = new Produto(null,"Ro??adeira",800.00);
		Produto p9 = new Produto(null,"Abajour",100.00);
		Produto p10 = new Produto(null,"Pendente",180.00);
		Produto p11 = new Produto(null,"Shampoo",90.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		cat3.getProdutos().addAll(Arrays.asList(p5,p6));
		cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9,p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1,cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2,cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1,cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		categoriarepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		produtorepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
		
		Estado est1 = new Estado(null,"MinasGerais");
		Estado est2 = new Estado(null,"S??o Paulo");
		
		Cidade c1 = new Cidade(null,"Uberl??ndia",est1);
		Cidade c2 = new Cidade(null,"S??o Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadorepository.save(est1);
		estadorepository.save(est2);
		cidaderepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"Maria Silva","johnynobrega17@gmail.com","36378912377",TipoCliente.PESSOAFISICA,pe.encode("123"));
		cli1.getTelefones().addAll(Arrays.asList("23763323","93838392"));
		
		Cliente cli2 = new Cliente(null,"Ana Costa","steamuser699@gmail.com","89533644001",TipoCliente.PESSOAFISICA,pe.encode("123"));
		cli2.addPerfil(Perfil.ADMIN);
		cli1.getTelefones().addAll(Arrays.asList("23764423","91238392"));
		
		
		Endereco e1 = new Endereco(null,"Rua Flores","300","Apto 203","Jardim","38220834",cli1,c1);
		Endereco e2 = new Endereco(null,"Avenida Matos","105","Sala 800","Centro","38777012",cli1,c2);
		Endereco e3 = new Endereco(null,"Avenida Floriano","2106",null,"Centro","23456789",cli2,c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		
		clienterepository.saveAll(Arrays.asList(cli1,cli2));
		enderecorepository.save(e1);
		enderecorepository.save(e2);
		enderecorepository.save(e3);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"),cli1,e1);
		Pedido ped2 = new Pedido(null,sdf.parse("10/10/2017 19:35"),cli1,e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2,sdf.parse("20/10/2017 00:00"),null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidorepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentorepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1,p1,0.00,1,2000.00);
		ItemPedido ip2 = new ItemPedido(ped1,p3,0.00,2,80.00);
		ItemPedido ip3 = new ItemPedido(ped2,p2,100.00,1,800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itempedidorepository.save(ip1);
		itempedidorepository.save(ip2);
		itempedidorepository.save(ip3);
		
	}
}
