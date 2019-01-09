package com.khalilpan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.khalilpan.beans.LoginUser;
import com.khalilpan.beans.User;
import com.khalilpan.beans.UserLog;
import com.khalilpan.beans.phone;
import com.khalilpan.dataSource.DataSourceUser;
import com.khalilpan.handlingErrors.UserNotFoundException;



@RestController
public class UserController {
	

	@Autowired
	private DataSourceUser dataSourceUser;

	// Cadastro
	// http://localhost:8080/register
	@PostMapping("/register")
	public MappingJacksonValue addUser(@RequestBody User tempUser) {
		User newUser = dataSourceUser.addUser(tempUser);

		if (newUser == null) {
			return dataSourceUser.getMessage("EmailExists", "E-mail já existente");
		}

		//obter a tora do usuário criado // to get created user's Log
		UserLog newUserLog = dataSourceUser.getLog(newUser);

		//retornar criação mensagem  // to return a created message
		return dataSourceUser.getCreatedMessage(newUserLog);
	}

//	// to get all users - GET
//	// http://localhost:8080/users
//	@GetMapping("/users")
//	public List<User> getAllUsers() {
//		return dataSourceUser.getAllUsers();
//	}

//	// to get all phones - GET
//	// http://localhost:8080/phones
//	@GetMapping("/phones")
//	public List<phone> getAllPhones() {
//		return dataSourceUser.getAllPhones();
//	}

//	// to get all logs - GET
//	// http://localhost:8080/logs
//	@GetMapping("/logs")
//	public List<UserLog> getAlllogs() {
//		return dataSourceUser.getAllLogs();
//	}

	//Login endpoint // to login
	// http://localhost:8080/login
	@GetMapping("/login")
	public MappingJacksonValue login(@RequestBody LoginUser loginUser) {

		MappingJacksonValue mappingJacksonValue = dataSourceUser.login(loginUser);

		if (mappingJacksonValue == null) {
			throw new UserNotFoundException("Email: (" + loginUser.getEmail() + ") não encontrado");
		}

		return mappingJacksonValue;

	}

	// to reach user profile end point
	// http://localhost:8080/userprofile/{userid}
	@GetMapping("/userprofile/{userid}")
	public MappingJacksonValue userProfile(@RequestHeader String token, @PathVariable Long userid) {

		Boolean tokenStatusBoolean = dataSourceUser.checkToken(token);
		if (tokenStatusBoolean == false) {
			// token doesn't exists in the List of tokens
			return dataSourceUser.getMessage("TokenNotFound", "Não autorizado");
		} else if (tokenStatusBoolean == true) {
			// token exists in the list of tokens
			return dataSourceUser.findUserById(userid, token);
		}

		return null;

	}

}
