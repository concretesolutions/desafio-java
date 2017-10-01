package br.com.desafio.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.dto.UserDto;
import br.com.desafio.response.Response;
import br.com.desafio.services.UserService;
import br.com.desafio.utils.UserUtils;

@RestController
@RequestMapping(value="/api/profile")
@CrossOrigin(origins="*")
public class ProfileController {
	
	private static final String AUTH_HEADER = "Authorization";
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Response<UserDto>> getUser(@PathVariable("id") Long id, HttpServletRequest request) {
		Response<UserDto> response = new Response<UserDto>();
		try {
			String token = request.getHeader(AUTH_HEADER).substring(7);
			response.setData(UserUtils.convertUserToUserDto(userService.getUser(id, token)));
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

}
