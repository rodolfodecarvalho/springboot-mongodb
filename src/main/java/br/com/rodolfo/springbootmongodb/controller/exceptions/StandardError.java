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
public class StandardError implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
}