package com.example.algamoney.api.token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.example.algamoney.api.property.AlgamoneyApiProperty;

/**
 * Processador que atua depois que o refresh token 
 * e criado. 
 * Objetivo. Tirar o refreshToken do body da resposta
 * @author msousa
 *
 */
@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken>{

	@Autowired
	private AlgamoneyApiProperty algamoneyApiProperty;
	
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return returnType.getMethod().getName().equals("postAccessToken");
	}
	
	/**
	 * Executado quando o retorno do metodo supports for true
	 * Aqui ficam as regras para remover o refreshToken do body
	 */
	@Override
	public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body, MethodParameter returnType, MediaType arg2,
			Class<? extends HttpMessageConverter<?>> arg3, ServerHttpRequest request, ServerHttpResponse response) {

		HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
		HttpServletResponse resp = ((ServletServerHttpResponse) response).getServletResponse();
		
		//Cast do body
		DefaultOAuth2AccessToken tokenBody = (DefaultOAuth2AccessToken) body;
		
		String refreshToken = body.getRefreshToken().getValue();
		adicionarRefreshTokenNoCookie(refreshToken, req, resp);
		removerRefreshTokenDoBody(tokenBody);
		
		return body;
	}

	private void removerRefreshTokenDoBody(DefaultOAuth2AccessToken tokenBody) {
		tokenBody.setRefreshToken(null);
	}

	private void adicionarRefreshTokenNoCookie(String refreshToken, HttpServletRequest req, HttpServletResponse resp) {
		Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
		refreshTokenCookie.setHttpOnly(true); //usado somente em http
		refreshTokenCookie.setSecure(algamoneyApiProperty.getSeguranca().isEnableHttps()); 
		refreshTokenCookie.setPath(req.getContextPath()+ "/oauth/token"); //Caminho que ele será enviado pelo browser
		refreshTokenCookie.setMaxAge(2592000);// tempo de expiração em Dias.
		resp.addCookie(refreshTokenCookie); // Adicionando cookie na resposta.
	}

	

}
