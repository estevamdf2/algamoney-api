package com.example.algamoney.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

/**
 * Classe responsável por receber os eventos de
 * header Location e eliminar
 * códigos duplicados.
 * 
 * @author msousa
 *
 */
public class RecursoCriadoEvent  extends ApplicationEvent{

	private static final long serialVersionUID = -8456496638934868695L;
	
	private HttpServletResponse response;
	private Long codigo;
	

	/*
	 * Aqui é criado o evento
	 * Devemos passar como parametro
	 * Object, HttpServletResponse e o codigo
	 */
	public RecursoCriadoEvent(Object source, HttpServletResponse response, Long codigo) {
		super(source);
		this.response = response;
		this.codigo = codigo;
	}


	public HttpServletResponse getResponse() {
		return response;
	}


	public Long getCodigo() {
		return codigo;
	}

	

}
