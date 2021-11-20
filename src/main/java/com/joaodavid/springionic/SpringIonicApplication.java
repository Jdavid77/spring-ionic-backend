package com.joaodavid.springionic;

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
import com.joaodavid.springionic.model.Produto;
import com.joaodavid.springionic.model.enums.TipoCliente;
import com.joaodavid.springionic.repository.CategoriaRepository;
import com.joaodavid.springionic.repository.CidadeRepository;
import com.joaodavid.springionic.repository.ClienteRepository;
import com.joaodavid.springionic.repository.EnderecoRepository;
import com.joaodavid.springionic.repository.EstadoRepository;
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
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		Produto p1 = new Produto(null,"Computador",2000.0);
		Produto p2 = new Produto(null,"Impressora",800.0);
		Produto p3 = new Produto(null,"Mouse",80.0);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriarepository.saveAll(Arrays.asList(cat1,cat2));
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
		
		
	}
	
	

}
