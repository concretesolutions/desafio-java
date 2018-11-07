package org.asalas.desafio.controllers;

import org.asalas.desafio.servicios.UsuarioService;
import org.asalas.desafio.dto.UsuarioDto;

import javax.validation.Valid;

import org.asalas.desafio.converters.UsuarioUtils;
import org.asalas.desafio.domain.Response;
import org.asalas.desafio.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/login")
@CrossOrigin(origins="*")
public class LoginRController {
	
	@Autowired
	private UsuarioService serv;

	@PostMapping
	public ResponseEntity<Response<UsuarioDto>> login(@Valid @RequestBody UsuarioDto dto, 
			BindingResult result) {
		
		Response<UsuarioDto> response = new Response<UsuarioDto>();
		
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				response.getErrors().add(error.getDefaultMessage());
			}
			return ResponseEntity.badRequest().body(response);
		}
		
		try {
			
			Usuario user = serv.login(dto.getEmail(), dto.getPassword());
			response.setData(UsuarioUtils.convertUsuarioToUsuarioDto(user));
			return ResponseEntity.ok(response);
			
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
}
