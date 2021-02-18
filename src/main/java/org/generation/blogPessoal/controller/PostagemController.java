package org.generation.blogPessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.generation.blogPessoal.repository.PostagemRepository;
import org.generation.blogPessoal.model.Postagem;

@RestController
@RequestMapping(path="/postagem")
@CrossOrigin("*")
public class PostagemController {
	
	private static final ResponseEntity<List<Postagem>> ResposeEntity = null;
	@Autowired
	private PostagemRepository repository;
	
	@GetMapping("/")
	public ResponseEntity<List<Postagem>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{Id}")
	public ResponseEntity<Postagem> findById(@PathVariable long Id) {
		return repository.findById(Id).map(Resp -> ResponseEntity.ok(Resp)).orElse(ResponseEntity.notFound().build());
		
	}
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	
	}

