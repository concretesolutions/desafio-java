package com.khalilpan.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.khalilpan.HashingPasswordWithPBKDF2;


@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private String email;
	private String password;
	
	@OneToMany(mappedBy="user1") //user1 é o nome do campo no Phone classe //user1 is the name of field name in phone class
	private List<phone> phones; //usar para conectar com a tabela do phone
	
	@OneToOne(mappedBy="user2")
	@JsonIgnore
	private UserLog userLog; //usar para conectar com a tabela do UserLog
	
	public User() {
	}

	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = HashingPasswordWithPBKDF2.getInstance().toHash(password);//vai salvar a senha hashed //will save the password in hashed mode
		this.phones = new ArrayList<phone>();
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<phone> getPhones() {
		return phones;
	}

	public void setPhones(ArrayList<phone> phones) {
		this.phones = phones;
	}
	
	
	
	public UserLog getUserLog() {
		return userLog;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + ", phones=" + phones + "]";
	}

	//inserir novo numero
	public void addphone(String number,String ddd) {
		this.phones.add(new phone(number,ddd));
	}


	
}
