package br.com.concrete.dtos;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequestDto extends UserDto {

	@Pattern(message = "A senha deve conter ao menos uma letra mínuscula, uma maiúscula,  um número e ter no mínimo 5 caracteres", regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,}$")
	private String password;

	private Set<PhoneDto> phones = new HashSet<PhoneDto>();

	public Set<PhoneDto> getPhones() {
		return phones;
	}

	public void setPhones(Set<PhoneDto> phones) {
		this.phones = phones;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
