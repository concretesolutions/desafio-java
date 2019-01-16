package br.com.concrete.service;

import java.util.Optional;
import java.util.UUID;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.concrete.domain.UserEntity;
import br.com.concrete.dtos.UserRequestDto;
import br.com.concrete.dtos.UserResponseDto;
import br.com.concrete.repository.UserRepository;
import br.com.concrete.security.SecurityUtil;
import br.com.concrete.service.converter.UserRequestConverter;
import br.com.concrete.service.converter.UserResponseConverter;

@Service
public class UserService {

	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private UserRequestConverter userRequestConverter;

	@Autowired
	private UserResponseConverter userResponseConverter;

	@Autowired
	private UserRepository registerRepository;

	public UserResponseDto newUser(final UserRequestDto registerDto) {
		if (isUserExist(registerDto.getEmail())) {
			throw new DataIntegrityViolationException("O registro já existe");
		}
		UserEntity registerEntity = this.userRequestConverter.apply(registerDto);
		applyHashAndSalt(registerEntity);
		applyToken(registerEntity);
		applyUUID(registerEntity);
		final UserEntity registerEntitySaved = this.registerRepository.save(registerEntity);
		final UserResponseDto userResponseDto = this.userResponseConverter.apply(registerEntitySaved);
		return userResponseDto;
	}

	public UserResponseDto getUser(final String id) throws LoginException {
		Optional<UserEntity> registerOptional = this.registerRepository.findById(id);
		if (registerOptional.isPresent()) {
			return userResponseConverter.apply(registerOptional.get());
		}
		throw new LoginException("Não autorizado");
	}
	
	private boolean isUserExist(final String email) {
		Optional<UserEntity> registerOptional = this.registerRepository.findByEmail(email);
		return registerOptional.isPresent();
	}

	private void applyUUID(final UserEntity registerEntity) {
		registerEntity.setId(UUID.randomUUID().toString());
	}

	private void applyHashAndSalt(final UserEntity registerEntity) {
		String hashPassword = this.securityUtil.hashPassword(registerEntity.getPassword());
		registerEntity.setPassword(hashPassword);
	}

	private void applyToken(final UserEntity registerEntity) {
		String hashPassword = this.securityUtil.createJWT(registerEntity.getEmail());
		registerEntity.setToken(hashPassword);
	}

}
