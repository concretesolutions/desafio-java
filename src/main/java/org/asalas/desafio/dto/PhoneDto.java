package org.asalas.desafio.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneDto implements Serializable {
	private Long id;
	private Integer number;
	private Integer countrycode;
	private Integer citycode;
	
}
