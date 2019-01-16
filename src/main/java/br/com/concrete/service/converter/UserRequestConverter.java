package br.com.concrete.service.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.concrete.domain.PhoneEntity;
import br.com.concrete.domain.UserEntity;
import br.com.concrete.dtos.PhoneDto;
import br.com.concrete.dtos.UserRequestDto;

@Component
public class UserRequestConverter implements Function<UserRequestDto, UserEntity> {
	
	@Autowired
	private PhoneRequestConverter phoneConverter;

	@Override
	public UserEntity apply(final UserRequestDto registerDto) {
		final UserEntity registerEntity = new UserEntity();
		registerEntity.setEmail(registerDto.getEmail());
		registerEntity.setName(registerDto.getName());
		registerEntity.setPassword(registerDto.getPassword());
		for (PhoneDto phoneDto: registerDto.getPhones()) {
			PhoneEntity phoneEntity = phoneConverter.apply(phoneDto);
			registerEntity.getPhones().add(phoneEntity);
		}
		return registerEntity;
	}

}
