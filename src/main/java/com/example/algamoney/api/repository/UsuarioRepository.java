package com.example.algamoney.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	/**
	 * Caso não encontre não
	 * exige tratamento para ver 
	 * se o objeto e diferente de nulo.
	 * @param email
	 * @return
	 */
	public Optional<Usuario> findByEmail(String email);
}
