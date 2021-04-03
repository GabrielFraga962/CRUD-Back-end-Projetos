package org.generation.blogPessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.generation.blogPessoal.repository.PostagemRepository;
import org.generation.blogPessoal.model.Postagem;

@RestController  				//Informa para o Spring que essa classe trata-se de um controle
@RequestMapping("/postagens")  //Informa em qual URI essa classe será acessada
@CrossOrigin("*")             //Informa que a classe irá aceitar requisições de qualquer origem
public class PostagemController {
	
	@Autowired  // Permite que todos serviços dessa interface sejam acessadas a partir do controller
	private PostagemRepository repository;
	
	@GetMapping // Se o método da requisição externa for o GET, ele vai disparar esse método abaixo
	public ResponseEntity<List<Postagem>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")//o java é um programa autosensitive
	public ResponseEntity<Postagem> findById(@PathVariable long id) {//o long aceita uma quantidade maior de numeros do que o int.
		return repository.findById(id)
				.map(Resp -> ResponseEntity.ok(Resp))
				.orElse(ResponseEntity.notFound().build());
		
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Postagem> Post (@Valid @RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<Postagem> Put (@Valid @RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
   
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id) {
		repository.deleteById(id);
	}
}