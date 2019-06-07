package com.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.json.response.RegistroResponse;
import com.desafio.json.resquest.RegistroRequest;
import com.desafio.service.RegistroService;

@RestController
public class UsersController {
	
	@Autowired
	private RegistroService registroService;
	
	@RequestMapping( value = "desafioJava/registerUser", method = RequestMethod.POST )
	public @ResponseBody RegistroResponse registerUser( @RequestBody RegistroRequest registroRequest ) {
		RegistroResponse registroResponse = new RegistroResponse();
		
		registroResponse = registroService.registrarUsuario(registroRequest);
		
		return registroResponse;
	}

}
