package br.com.concrete.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneDto {
	
	@NotBlank(message = "ddd is required")
	@Pattern(regexp="^(0|[1-9][0-9]*)$",message= "O DDD deve conter somente números")
	private String ddd;

	@NotBlank(message = "phone is required")
	@Pattern(regexp="^(0|[1-9][0-9]*)$", message= "O número de telefone deve conter somente números")
	private String number;
	

	public String getNumber() {
		return number;
	}

	public void setNumber(String phoneNumber) {
		this.number = phoneNumber;
	}
	
	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

}
