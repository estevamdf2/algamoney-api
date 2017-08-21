package com.example.algamoney.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.algamoney.api.repository.CategoriaRepository;
import com.mysql.fabric.Response;
import com.example.algamoney.api.model.Categoria;

/**
 * Expoe os recursos do REST
 * para esta classe
 * @RestController : o retorno dele já vira em Json.
 * @RequestMapping: faz o mapeamento da requisição
 * @author msousa
 *
 */
@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	/*
	 * O spring buscara uma implementação da interface
	 * CategoriaRepository e injetará na classe.
	 */
	@Autowired 
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public List<Categoria> listar(){
		return categoriaRepository.findAll();
	}
	
	/**
	 * Com a anotação @ResponseStatus
	 * voce retorna o código 201 ao criar
	 * o recurso.
	 * 
	 * HttpServletResponse usada para retornar
	 * o código da categoria criada na resposta
	 * do método.
	 * @param categoria
	 */
	@PostMapping
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(categoriaSalva.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		//inserir o recurso no body
		
		return ResponseEntity.created(uri).body(categoriaSalva);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
		
		Categoria categoriaBusca = categoriaRepository.findOne(codigo);
		
		/*
		 *Mostrar erro 404 caso não
		 *tenha a categoria cadastrada. 
		 */
		if(categoriaBusca != null) {
			return new ResponseEntity<Categoria>(categoriaBusca,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
