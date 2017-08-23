package com.example.algamoney.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@GetMapping
	public List<Pessoa> listar(){
		return pessoaRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		
		//Cria a URI para retornar ao usuário o código da pessoa cadastrada.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				 .buildAndExpand(pessoaSalva.getCodigo()).toUri();
		//Inserir o recurso salvo o body
		return ResponseEntity.created(uri).body(pessoa);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo){
		
		Pessoa pessoaBusca = pessoaRepository.findOne(codigo);
		
		//Mostrar erro 404 caso não tenha a pessoa com o código informado.
		return pessoaBusca != null ? new ResponseEntity<Pessoa>(pessoaBusca,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}