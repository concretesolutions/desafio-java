package br.com.concrete.service.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import br.com.concrete.domain.PhoneEntity;
import br.com.concrete.dtos.PhoneDto;

@Component
public class PhoneRequestConverter implements Function<PhoneDto, PhoneEntity> {

	@Override
	public PhoneEntity apply(final PhoneDto phoneDto) {
		final PhoneEntity phoneEntity = new PhoneEntity();
		phoneEntity.setDdd(phoneDto.getDdd());
		phoneEntity.setPhoneNumber(phoneDto.getNumber());
		return phoneEntity;
	}

}
