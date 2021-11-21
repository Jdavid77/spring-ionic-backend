package com.joaodavid.springionic;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

@SpringBootApplication
public class SpringIonicApplication implements CommandLineRunner{

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
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		Categoria cat3 = new Categoria(null,"Jardinagem");
		Categoria cat4 = new Categoria(null,"Casa de Banho");
		Categoria cat5 = new Categoria(null,"Decoração");
		Categoria cat6 = new Categoria(null,"Perfumaria");
		Categoria cat7 = new Categoria(null,"Eletrónicos");
		
		Produto p1 = new Produto(null,"Computador",2000.0);
		Produto p2 = new Produto(null,"Impressora",800.0);
		Produto p3 = new Produto(null,"Mouse",80.0);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriarepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		produtorepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null,"MinasGerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlândia",est1);
		Cidade c2 = new Cidade(null,"São Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadorepository.save(est1);
		estadorepository.save(est2);
		cidaderepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"Maria Silva","maria@gmail.com","36378912377",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("23763323","93838392"));
		
		Endereco e1 = new Endereco(null,"Rua Flores","300","Apto 203","Jardim","38220834",cli1,c1);
		Endereco e2 = new Endereco(null,"Avenida Matos","105","Sala 800","Centro","38777012",cli1,c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienterepository.save(cli1);
		enderecorepository.save(e1);
		enderecorepository.save(e2);
		
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
