package com.example.algamoney.api.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.repository.filter.LancamentoFilter;
import com.jayway.jsonpath.Predicate;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery{

	//Entity manager para fazer a consulta
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		
		
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		//Criar as restrições do criteria
		Predicate[] predicates = CriarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<Lancamento> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}

	private Predicate[] CriarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
			Root<Lancamento> root) {

		List<Predicate> predicates = new ArrayList<>();
		
		if(lancamentoFilter.getDescricao() != null) {
			predicates.add(builder.like(
									builder.lower(root.get("descricao")),"%" + lancamentoFilter.getDescricao().toLowerCase() + "%"));
		}
		
		if(lancamentoFilter.getDataVencimentoDe() != null) {
			
		}
		
		if(lancamentoFilter.getDataVencimentoAte() != null) {
			
		}
		
		/*
		 * Como o array e variavel cria-se
		 * uma lista de array
		 */
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
