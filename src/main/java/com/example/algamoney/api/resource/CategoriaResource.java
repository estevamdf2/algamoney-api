package com.example.algamoney.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.algamoney.api.repository.CategoriaRepository;
import com.example.algamoney.api.service.categoriaService;
import com.example.algamoney.api.event.RecursoCriadoEvent;
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
	
	@Autowired
	private categoriaService categoriaService;

	@Autowired
	private ApplicationEventPublisher publisher;

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
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
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
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo ){
		categoriaRepository.delete(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long codigo, @Valid @RequestBody Categoria categoria){
		Categoria categoriaSalva = categoriaService.atualizar(codigo,categoria);
		return ResponseEntity.ok(categoriaSalva);
		
	}
}
