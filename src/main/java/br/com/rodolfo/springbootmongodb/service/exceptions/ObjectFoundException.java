package br.com.rodolfo.springbootmongodb.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ObjectFoundException extends RuntimeException {

	private static final long serialVersionUID = 4645146803851299782L;

	public ObjectFoundException(String msg) {
		super(msg);
	}

	public ObjectFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}