package com.joaodavid.springionic.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {


	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> list = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timestamp) {
		super(status, msg, timestamp);
		
		
		
	}

	public List<FieldMessage> getErrors() {
		return list;
	}

	public void addError(String fieldname,String message) {
		list.add(new FieldMessage(fieldname,message));
	}
	
	

}
