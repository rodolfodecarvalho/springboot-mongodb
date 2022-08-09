package br.com.rodolfo.springbootmongodb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class Api {

	@GetMapping("/")
	public String getAllTodos() {
		return "Spring boot with mongodb";
	}
}