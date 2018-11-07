package org.asalas.desafio.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Phone {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Integer number;
	private Integer citycode;
	private Integer countrycode;
}
