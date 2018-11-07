package org.asalas.desafio.domain;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
    private String name;
	
	private String email;
	
	private String password;
	
	@OneToMany
	private List<Phone> phones;
	
	private Calendar created;
	
	private Calendar modified;
	
	private Calendar lastLogin;
	
	private String token;
 	
	private ProfileEnum profile;
	
	
	
	
}
