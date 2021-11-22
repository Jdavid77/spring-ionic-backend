package com.joaodavid.springionic.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.joaodavid.springionic.model.Categoria;


public class CategoriaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento Obrigatório!")
	@Length(min=5,max=80,message="Tamanho entre 5 e 80 carateres")
	private String name;
	
	public CategoriaDTO() {
		
	}

	public CategoriaDTO(Categoria obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
