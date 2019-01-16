package br.com.concrete.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phone_number")
public class PhoneEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_phone")
	private Long id;
	
	@Column(name = "phone_ddd")
	private String ddd;

	@Column(name = "phone_number")
	private String number;
	
	public String getPhoneNumber() {
		return number;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.number = phoneNumber;
	}
	
	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


}
