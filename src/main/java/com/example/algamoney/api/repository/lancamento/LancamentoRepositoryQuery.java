package com.example.algamoney.api.repository.lancamento;

import com.example.algamoney.api.repository.filter.LancamentoFilter;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.algamoney.api.dto.LancamentoEstatisticaCategoria;
import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.projection.ResumoLancamento;

//Responsável pela consulta da Query do SpringDateJPA
public interface LancamentoRepositoryQuery {

	public List<LancamentoEstatisticaCategoria> porCategoria(LocalDate mesReferencia);
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
	
}
