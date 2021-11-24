package com.joaodavid.springionic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.joaodavid.springionic.model.Categoria;
import com.joaodavid.springionic.model.Produto;
import com.joaodavid.springionic.repository.CategoriaRepository;
import com.joaodavid.springionic.repository.ProdutoRepository;
import com.joaodavid.springionic.service.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	public ProdutoRepository repository;
	
	@Autowired
	public CategoriaRepository categoriaRepository;
	
	public Produto findById(Integer id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage,String orderBy,String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repository.search(nome,categorias, pageRequest);
	}

}
