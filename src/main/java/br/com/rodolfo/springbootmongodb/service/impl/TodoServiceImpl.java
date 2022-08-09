package br.com.rodolfo.springbootmongodb.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.rodolfo.springbootmongodb.model.TodoDTO;
import br.com.rodolfo.springbootmongodb.repository.TodoRepository;
import br.com.rodolfo.springbootmongodb.service.TodoService;
import br.com.rodolfo.springbootmongodb.service.exceptions.DataIntegrityException;
import br.com.rodolfo.springbootmongodb.service.exceptions.ObjectFoundException;
import br.com.rodolfo.springbootmongodb.service.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

	private final TodoRepository todoRepository;

	@Override
	public TodoDTO create(TodoDTO todo) {
		todoRepository.findByTodo(todo.getTodo()).ifPresent(todoWithSameName -> {
			throw new ObjectFoundException(
					"Object found id: " + todoWithSameName.getId() + ", Type: " + TodoDTO.class.getTypeName());

		});

		todo.setCreatedAt(new Date(System.currentTimeMillis()));

		return todoRepository.save(todo);
	}

	@Override
	public List<TodoDTO> getAll() {
		return todoRepository.findAll();
	}

	@Override
	public TodoDTO getSingle(String id) {
		return todoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Object not found Id:" + id + ", Type: " + TodoDTO.class.getTypeName()));
	}

	@Override
	public Optional<TodoDTO> updateById(String id, TodoDTO todo) {
		todoRepository.findByTodo(todo.getTodo()).ifPresent(todoWithSameName -> {
			if (Boolean.FALSE.equals(todoWithSameName.getId().equalsIgnoreCase(id))) {
				throw new DataIntegrityException(
						"Object already exists: " + id + ", Type: " + TodoDTO.class.getTypeName());
			}
		});

		return Optional.ofNullable(todoRepository.findById(id).map(todoToSave -> {
			todoToSave.setTodo(todo.getTodo());
			todoToSave.setDescription(todo.getDescription());
			todoToSave.setCompleted(todo.getCompleted());
			todoToSave.setCreatedAt(todo.getCreatedAt());
			todoToSave.setUpdatedAt(new Date(System.currentTimeMillis()));

			return todoRepository.save(todoToSave);
		}).orElseThrow(() -> new ObjectNotFoundException(
				"Object not found with id: " + id + ", Type: " + TodoDTO.class.getTypeName())));

	}

	@Override
	public void deleteById(String id) {
		todoRepository.findById(id).map(existingTodo -> {
			todoRepository.delete(existingTodo);
			return "";
		}).orElseThrow(() -> new ObjectNotFoundException(
				"Object not found with id: " + id + ", Type: " + TodoDTO.class.getTypeName()));
	}
}