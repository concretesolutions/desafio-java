package com.desafio.service;

import com.desafio.json.response.RegistroResponse;
import com.desafio.json.resquest.RegistroRequest;

public interface RegistroService {

	public RegistroResponse registrarUsuario( RegistroRequest registroRequest );
	
}
