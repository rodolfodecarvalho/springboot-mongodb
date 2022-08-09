package br.com.rodolfo.springbootmongodb.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodolfo.springbootmongodb.model.TodoDTO;
import br.com.rodolfo.springbootmongodb.service.TodoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "api")
@RequiredArgsConstructor
public class TodoController {

	private final TodoService todoService;

	@GetMapping("/todos")
	public ResponseEntity<?> getAllTodos() {
		List<TodoDTO> todos = todoService.getAll();

		return ResponseEntity.status(todos.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(todos);
	}

	@PostMapping("/todos")
	public ResponseEntity<?> createTodo(@Valid @RequestBody TodoDTO todo) {
		todoService.create(todo);

		return ResponseEntity.status(HttpStatus.CREATED).body(todo);
	}

	@GetMapping(path = "/todos/{id}")
	public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id) {
		TodoDTO todo = todoService.getSingle(id);

		return ResponseEntity.status(HttpStatus.OK).body(todo);
	}

	@PutMapping(path = "/todos/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id") String id, @Valid @RequestBody TodoDTO todo) {
		return todoService.updateById(id, todo).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
	}

	@DeleteMapping(path = "/todos/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
		todoService.deleteById(id);

		return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted with id " + id);
	}
}