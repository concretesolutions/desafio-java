package br.com.concrete.controller;

import java.util.Set;

import javax.security.auth.login.LoginException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.concrete.dtos.UserRequestDto;
import br.com.concrete.dtos.UserResponseDto;
import br.com.concrete.exception.ValidateException;
import br.com.concrete.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponseDto> newRegister(@RequestBody final UserRequestDto userRequestDto) {
		this.validate(userRequestDto);
		final UserResponseDto userResponseDto = this.userService.newUser(userRequestDto);
		final ResponseEntity<UserResponseDto> responseEntity = new ResponseEntity<UserResponseDto>(userResponseDto,
				userResponseDto.getResponseHeaders(), HttpStatus.ACCEPTED);
		return responseEntity;
	}

	@RequestMapping(path = "/user/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponseDto> getUser(@PathVariable final String id) throws LoginException {
		final UserResponseDto userResponseDto = this.userService.getUser(id);
		return ResponseEntity.ok(userResponseDto);
	}

	private void validate(final UserRequestDto userRequestDto) {
		final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		final Validator validator = factory.getValidator();
		final Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(userRequestDto);
		if (!violations.isEmpty()) {
			violations.iterator().next().getMessage();
			final String errorsString = violations.stream().map(x -> x.getMessage()).reduce((a, b) -> a + ", " + b).get();
			throw new ValidateException(errorsString.toString());
		}

	}

}
