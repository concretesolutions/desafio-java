package br.com.concrete.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class UserDto {
	
	@Size(min = 5, max = 200, message 
		      = "O nome deve conter entre 5 e 200 caracteres")
	private String name;
	
	@Email(message = "O e-mail é invalido")
	private String email;
	


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
