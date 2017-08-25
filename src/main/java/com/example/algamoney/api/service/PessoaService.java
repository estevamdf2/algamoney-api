package com.example.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaRepository.findOne(codigo);
		
		//A exceção será tratada na classe AlgaMoneyExceptionHandler
		if(pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}

		/*
		 *Classe utils que copia as propriedade de pessoa
		 *passada por parametrô para o objeto pessoaSalva
		 *Exceto codigo que vem null (pessoa) 
		 */
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		return pessoaRepository.save(pessoaSalva);
	}
}

