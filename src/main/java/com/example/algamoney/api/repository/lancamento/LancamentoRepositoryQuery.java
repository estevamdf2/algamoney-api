package com.example.algamoney.api.repository.lancamento;

import com.example.algamoney.api.repository.filter.LancamentoFilter;

import java.util.List;

import com.example.algamoney.api.model.Lancamento;

//Responsável pela consulta da Query do SpringDateJPA
public interface LancamentoRepositoryQuery {

	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}
