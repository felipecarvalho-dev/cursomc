package com.felipesouto.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> erros = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timeSLong) {
		super(status, msg, timeSLong);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getErros() {
		return erros;
	}

	public void addError(String fieldName, String message) {
		erros.add(new FieldMessage(fieldName,message));
	}


}
