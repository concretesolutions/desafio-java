package org.asalas.desafio.config;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthPuntoEntrada  implements AuthenticationEntryPoint {

		@Override
		public void commence(HttpServletRequest request, 
				HttpServletResponse response,
				AuthenticationException authException)
				throws IOException, ServletException {
			// TODO Auto-generated method stub
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No autorizado");
		}
		
}
