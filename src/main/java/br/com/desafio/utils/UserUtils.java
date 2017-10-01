package br.com.desafio.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.desafio.dto.PhoneDto;
import br.com.desafio.dto.UserDto;
import br.com.desafio.entities.Phone;
import br.com.desafio.entities.User;

public class UserUtils {
	
	public static User convertUserDtoToUser(UserDto userDto) {
		
		List<Phone> phones = new ArrayList<Phone>();
		for (PhoneDto phoneDto : userDto.getPhones()) {
			phones.add(PhoneUtils.convertPhoneDtoToPhone(phoneDto));
		}
		
		User user = new User();
		user.setId(userDto.getId());
		user.setCreated(userDto.getCreated());
		user.setModified(userDto.getModified());
		user.setLastLogin(userDto.getLastLogin());
		user.setToken(userDto.getToken());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setPhones(phones);
		user.setProfile(userDto.getProfile());
		
		return user;
		
	}
	
	public static UserDto convertUserToUserDto(User user) {
		
		List<PhoneDto> phones = new ArrayList<PhoneDto>();
		for (Phone phone : user.getPhones()) {
			phones.add(PhoneUtils.convertPhoneToPhoneDto(phone));
		}
		
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setCreated(user.getCreated());
		userDto.setModified(user.getModified());
		userDto.setLastLogin(user.getLastLogin());
		userDto.setToken(user.getToken());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		// userDto.setPassword(user.getPassword());
		userDto.setPhones(phones);
		userDto.setProfile(user.getProfile());
		
		return userDto;
		
	}
	
}
