package br.com.desafio.utils;

import br.com.desafio.dto.PhoneDto;
import br.com.desafio.entities.Phone;

public class PhoneUtils {
	
	public static Phone convertPhoneDtoToPhone(PhoneDto phoneDto) {
		Phone phone = new Phone();
		phone.setId(phoneDto.getId());
		phone.setNumber(phoneDto.getNumber());
		phone.setDdd(phoneDto.getDdd());
		return phone;
	}
	
	public static PhoneDto convertPhoneToPhoneDto(Phone phone) {
		PhoneDto phoneDto = new PhoneDto();
		phoneDto.setId(phone.getId());
		phoneDto.setNumber(phone.getNumber());
		phoneDto.setDdd(phone.getDdd());
		return phoneDto;
	}
	
}
