package br.com.rodolfo.springbootmongodb.controller.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldMessage implements Serializable {

	private static final long serialVersionUID = -1L;

	private String fieldName;

	private String message;
}