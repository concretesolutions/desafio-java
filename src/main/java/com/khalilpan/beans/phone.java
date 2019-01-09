package com.khalilpan.beans;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class phone {

	@Id
	@GeneratedValue
	@JsonIgnore
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User user1; //usar para conectar com a tabela do user
	
	private String number;
	private String ddd;
	
	public phone() {
		super();
	}

	public phone(String number, String ddd) {
		this.number = number;
		this.ddd = ddd;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getDdd() {
		return ddd;
	}


	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user1 = user;
	}

	@Override
	public String toString() {
		return "phone [number=" + number + ", ddd=" + ddd + "]";
	}
	
}
