package br.com.desafio.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.dto.UserDto;
import br.com.desafio.entities.User;
import br.com.desafio.response.Response;
import br.com.desafio.services.UserService;
import br.com.desafio.utils.UserUtils;

@RestController
@RequestMapping("/api/register")
@CrossOrigin(origins="*")
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<Response<UserDto>> register(@Valid @RequestBody UserDto userDto, BindingResult result) {
		
		Response<UserDto> response = new Response<UserDto>();
		
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				response.getErrors().add(error.getDefaultMessage());
			}
			return ResponseEntity.badRequest().body(response);
		}
		
		try {
			
			User user = userService.register(UserUtils.convertUserDtoToUser(userDto));
			response.setData(UserUtils.convertUserToUserDto(user));
			return ResponseEntity.ok(response);
			
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
}
