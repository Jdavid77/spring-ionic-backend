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

	
	public static void main(String[] args) {
		SpringApplication.run(SpringIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}
	
	

}
