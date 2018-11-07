package org.asalas.desafio.servicios.impl;

import org.asalas.desafio.domain.Phone;
import org.asalas.desafio.repo.PhoneRepo;
import org.asalas.desafio.servicios.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneServiceImp implements PhoneService {
	@Autowired
	private PhoneRepo repo;

	@Override
	public Phone save(Phone phone) {
		return repo.save(phone);
	}
	
	
}
