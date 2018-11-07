package org.asalas.desafio.controllers;

import javax.validation.Valid;

import org.asalas.desafio.converters.UsuarioUtils;
import org.asalas.desafio.domain.Response;
import org.asalas.desafio.domain.Usuario;
import org.asalas.desafio.dto.UsuarioDto;
import org.asalas.desafio.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registro")
public class ResgistroRController {
	@Autowired
	private UsuarioService serv;
	
	@PostMapping
	public ResponseEntity<Response<UsuarioDto>> registrar(@Valid @RequestBody UsuarioDto dto, BindingResult result){
		Response<UsuarioDto> response =  new Response<>();
		
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				response.getErrors().add(error.getDefaultMessage());
			}
			return ResponseEntity.badRequest().body(response);
		}
		
		try {
			
			Usuario user = serv.register(UsuarioUtils.convertUsuarioDtoToUsuario(dto));
			response.setData(UsuarioUtils.convertUsuarioToUsuarioDto(user));
			return ResponseEntity.ok(response);
			
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
}
