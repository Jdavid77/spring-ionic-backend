package com.joaodavid.springionic.service.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.joaodavid.springionic.DTO.ClienteDTO;
import com.joaodavid.springionic.model.Cliente;
import com.joaodavid.springionic.repository.ClienteRepository;
import com.joaodavid.springionic.resources.exceptions.FieldMessage;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClienteDTO> {

	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClientUpdate ann) {

	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		Map<String,String> map = (Map<String,String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer id = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();

		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux != null && !aux.getId().equals(id)) { // atempting to update the client email, but if the email already exists it gives an error
			list.add(new FieldMessage("email","E-mail já existente"));
		}
		
		
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldname())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
