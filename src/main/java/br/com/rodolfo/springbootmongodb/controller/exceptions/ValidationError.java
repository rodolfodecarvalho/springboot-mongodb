package br.com.rodolfo.springbootmongodb.controller.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ValidationError extends StandardError implements Serializable {

	private static final long serialVersionUID = -1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public void addError(String name, String message) {
		errors.add(new FieldMessage(name, message));
	}
}