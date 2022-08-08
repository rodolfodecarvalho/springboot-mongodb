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

		Optional<TodoDTO> todoOptional = todoRepository.findByTodo(todo.getTodo());

		if (todoOptional.isPresent()) {
			throw new ObjectFoundException(
					"Object found id: " + todoOptional.get().getId() + ", Type: " + TodoDTO.class.getTypeName());
		}

		todo.setCreatedAt(new Date(System.currentTimeMillis()));

		return todoRepository.save(todo);
	}

	@Override
	public List<TodoDTO> getAll() {
		return todoRepository.findAll();
	}

	@Override
	public TodoDTO getSingle(String id) {
		Optional<TodoDTO> todoOptional = todoRepository.findById(id);
		return todoOptional.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found Id:" + id + ", Type: " + TodoDTO.class.getTypeName()));
	}

	@Override
	public void updateById(String id, TodoDTO todo) {
		Optional<TodoDTO> todoOptional = todoRepository.findById(id);
		Optional<TodoDTO> todoWithSameName = todoRepository.findByTodo(todo.getTodo());

		if (todoOptional.isPresent()) {
			if (todoWithSameName.isPresent()
					&& Boolean.FALSE.equals(todoWithSameName.get().getId().equalsIgnoreCase(id))) {
				throw new DataIntegrityException(
						"Object already exists: " + id + ", Type: " + TodoDTO.class.getTypeName());
			}

			TodoDTO todoToSave = todoOptional.get();
			todoToSave.setTodo(todo.getTodo());
			todoToSave.setDescription(todo.getDescription());
			todoToSave.setCompleted(todo.getCompleted());
			todoToSave.setCreatedAt(todo.getCreatedAt());
			todoToSave.setUpdatedAt(new Date(System.currentTimeMillis()));

			todoRepository.save(todoToSave);
		} else {
			throw new ObjectNotFoundException(
					"Object not found with id: " + id + ", Type: " + TodoDTO.class.getTypeName());
		}

	}

	@Override
	public void deleteById(String id) {
		Optional<TodoDTO> todoOptional = todoRepository.findById(id);

		if (todoOptional.isPresent()) {
			todoRepository.deleteById(id);
		} else {
			throw new ObjectNotFoundException(
					"Object not found with id: " + id + ", Type: " + TodoDTO.class.getTypeName());
		}
	}
}