package br.com.desafio.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.entities.Phone;
import br.com.desafio.repositories.PhoneRepository;
import br.com.desafio.services.PhoneService;

@Service
public class PhoneServiceImpl implements PhoneService {
	
	@Autowired
	private PhoneRepository phoneRepository;

	@Override
	public Phone save(Phone phone) {
		return phoneRepository.save(phone);
	}
	
}
