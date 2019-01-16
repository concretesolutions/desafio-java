package br.com.concrete.service.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import br.com.concrete.domain.PhoneEntity;
import br.com.concrete.dtos.PhoneDto;

@Component
public class PhoneResponseConverter implements Function<PhoneEntity, PhoneDto> {

	@Override
	public PhoneDto apply(final PhoneEntity phoneEntity) {
		final PhoneDto phoneDto = new PhoneDto();
		phoneDto.setDdd(phoneEntity.getDdd());
		phoneDto.setNumber(phoneEntity.getPhoneNumber());
		return phoneDto;
	}

}
