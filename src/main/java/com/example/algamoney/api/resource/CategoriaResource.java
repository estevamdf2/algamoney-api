package com.example.algamoney.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.repository.CategoriaRepository;
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
}
