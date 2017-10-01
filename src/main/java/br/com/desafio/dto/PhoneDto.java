package br.com.desafio.dto;

import java.io.Serializable;

public class PhoneDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5610881493650687320L;

	private Long id;
	
	private Integer number;
	
	private Integer ddd;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

}
