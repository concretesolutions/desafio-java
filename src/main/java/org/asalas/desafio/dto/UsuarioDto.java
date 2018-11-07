package org.asalas.desafio.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import org.asalas.desafio.domain.ProfileEnum;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioDto implements Serializable {
	private Long id;
	
	private Calendar created;
	
	private Calendar modified;
	
	private Calendar lastLogin;
	
	private String token;

	private String name;
	
	private String email;
	
	private String password;
	
	private List<PhoneDto> phones;
	
	private ProfileEnum profile;
}
