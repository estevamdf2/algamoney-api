package com.example.algamoney.api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.example.algamoney.api.token.CustomTokenEnhancer;


@Profile("oauth-security")
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	/**
	 * Configurações das credenciais
	 * do cliente 
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient("angular")
			.secret("@ngul@r0")
			//necessário definir senão gera erro
			.scopes("read" , "write") //É possível definir varios escopos
			.authorizedGrantTypes("password","refresh_token")
			//Tempo de acesso do token em segundos
			.accessTokenValiditySeconds(1800)
			.refreshTokenValiditySeconds(3600 *24) // Tempo de vida do refresh token 1 dia.
		.and()
			.withClient("mobile")
			.secret("m0b1l30")
			.scopes("read") //este cliente só tem acesso leitura
			.authorizedGrantTypes("password","refresh_token")
			.accessTokenValiditySeconds(1800)
			.refreshTokenValiditySeconds(3600 *24);
	}
	
	/**
	 * Configurações para o endpoints
	 * do Token
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		//Objeto com com mais detalhamento de tokens
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
		
		endpoints
		.tokenStore(tokenStore())
		.tokenEnhancer(tokenEnhancerChain)
		
		/*
		 *  Sempre que solicitado será enviado um refresh token para o usuário. Ele não se deslogara.
		 *  Se não setado. O refresh token expira em 24 horas.
		 */
		.reuseRefreshTokens(false)
		.authenticationManager(authenticationManager);
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter acessTokenConverter = new JwtAccessTokenConverter();
		//chave de validação do token
		acessTokenConverter.setSigningKey("algaworks");
		return acessTokenConverter;
	}

	/**
	 * Responsável por armazenar
	 * o token
	 * @return
	 */
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}
}
