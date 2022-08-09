package br.com.rodolfo.springbootmongodb.service;

import java.util.List;
import java.util.Optional;

import br.com.rodolfo.springbootmongodb.model.TodoDTO;

public interface TodoService {

	public TodoDTO create(TodoDTO todo);

	public List<TodoDTO> getAll();

	public TodoDTO getSingle(String id);

	public Optional<TodoDTO> updateById(String id, TodoDTO todo);

	public void deleteById(String id);
}