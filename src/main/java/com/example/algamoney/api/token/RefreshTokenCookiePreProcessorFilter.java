package com.example.algamoney.api.token;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE) //para o Spring dar alta prioridade para o filtro criado.
public class RefreshTokenCookiePreProcessorFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		/*
		 *Condicional para pegar o cookie
		 *da url /oauth/token quando o parameter for grant_type 
		 */
		if("/oauth/token".equalsIgnoreCase(req.getRequestURI())
				&& "refresh_token".equals(req.getParameter("grant_type"))
				&& req.getCookies() != null) {
			for (Cookie cookie : req.getCookies()) {
				if(cookie.getName().equals("refreshToken")) {
					String refreshToken = cookie.getValue(); //necessário inseri-lo na requisição
					req = new MyServletRequestWrapper(req, refreshToken);
				}
			}
			
		}
		
		//continuando a cadeia do filtro
		chain.doFilter(req, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/*
	 * Como não é possivel inseri-lo na requisição já existente
	 * foi criada esta classe para fazer isso.
	 */
	static class MyServletRequestWrapper extends HttpServletRequestWrapper{

		private String refreshToken;

		public MyServletRequestWrapper(HttpServletRequest request, String refreshToken) {
			super(request);
			this.refreshToken = refreshToken;
		}
		
		/*
		 * Criando outro parameterMap. Para passar novos parametros 
		 * para a requisição.
		 * (non-Javadoc)
		 * @see javax.servlet.ServletRequestWrapper#getParameterMap()
		 */
		@Override
		public Map<String, String[]> getParameterMap(){
			ParameterMap<String, String[]> map = new ParameterMap<>(getRequest().getParameterMap());
			map.put("refresh_token", new String[] {refreshToken});
			map.setLocked(true);
			return map;
		}
	}
}
