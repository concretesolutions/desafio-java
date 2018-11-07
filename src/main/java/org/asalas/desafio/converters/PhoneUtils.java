package org.asalas.desafio.converters;

import org.asalas.desafio.domain.Phone;
import org.asalas.desafio.dto.PhoneDto;

public class PhoneUtils {
	
	public static Phone convertPhoneDtoToPhone(PhoneDto phoneDto) {
		Phone phone = new Phone();
		phone.setId(phoneDto.getId());
		phone.setNumber(phoneDto.getNumber());
		phone.setCitycode(phoneDto.getCitycode());
		phone.setCountrycode(phoneDto.getCountrycode());
		return phone;
	}
	
	public static PhoneDto convertPhoneToPhoneDto(Phone phone) {
		PhoneDto phoneDto = new PhoneDto();
		phoneDto.setId(phone.getId());
		phoneDto.setNumber(phone.getNumber());
		phoneDto.setCitycode(phone.getCitycode());
		phoneDto.setCountrycode(phone.getCountrycode());
		return phoneDto;
	}

}
