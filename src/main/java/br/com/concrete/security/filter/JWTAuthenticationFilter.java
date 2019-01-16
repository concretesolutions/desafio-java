package br.com.concrete.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.filter.GenericFilterBean;

import com.google.gson.Gson;

import br.com.concrete.exception.ErrorDetails;
import br.com.concrete.exception.LoginException;
import br.com.concrete.exception.SessionException;
import br.com.concrete.service.LoginService;

@Component
public class JWTAuthenticationFilter extends GenericFilterBean {

	private static final String AUTH_HEADER = "Authorization";

	@Value("${jwt.filter.less}")
	private String filterLess;

	@Autowired
	private LoginService loginService;


	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
			throws IOException, ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, request.getServletContext());
		final HttpServletRequest requestHttp = (HttpServletRequest) request;
		final HttpServletResponse responseHttp = (HttpServletResponse) response;
		if (isShouldFilter(requestHttp)) {
			final String token = requestHttp.getHeader(AUTH_HEADER);
			try {
				this.loginService.checkToken(token);
			} catch (LoginException e) {
				this.errorLogin(responseHttp);
				throw e;
			}
			catch (SessionException e) {
				this.sessionExpired(responseHttp);
				throw e;
			}	
			
		}
		filterChain.doFilter(request, response);
	}
	
	private boolean isShouldFilter(final HttpServletRequest requestHttp) {
		final String[] filterArray = this.filterLess.split(",");
		for (String filter : filterArray) {
			if (requestHttp.getRequestURL().indexOf(filter) != -1) {
				return false;
			}
		}
		return true;
	}
	
	private final static int FORBIDDEN = 403;
	private final static String ENCODE = "UTF-8";
	private final static String CONTENT_TYPE = "application/json";
	
	private void sessionExpired(final HttpServletResponse responseHttp) throws IOException {
		final ErrorDetails errorDetails = new ErrorDetails("Sessão expirada");
		this.error(responseHttp, errorDetails);
	}
	
	private void errorLogin(final HttpServletResponse responseHttp) throws IOException {
		final ErrorDetails errorDetails = new ErrorDetails("Não autorizado");
		this.error(responseHttp, errorDetails);
	}
	
	private void error(final HttpServletResponse responseHttp, final ErrorDetails errorDetails) throws IOException {
		final String json = new Gson().toJson(errorDetails);
		responseHttp.setContentType(CONTENT_TYPE);
		responseHttp.setCharacterEncoding(ENCODE);
		responseHttp.setStatus(FORBIDDEN);
		responseHttp.getOutputStream().write(json.getBytes());
		responseHttp.getOutputStream().flush();
		responseHttp.getOutputStream().close();
	}

}