package br.com.rodolfo.springbootmongodb.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "todos")
public class TodoDTO implements Serializable {

	private static final long serialVersionUID = -3483606185918668386L;

	@Id
	private String id;

	@NotBlank(message = "Field requerid!")
	private String todo;

	@NotBlank(message = "Field requerid!")
	private String description;

	@NotNull(message = "Field requerid!")
	private Boolean completed;

	private Date createdAt;

	private Date updatedAt;
}