package org.asalas.desafio.converters;

import java.util.ArrayList;
import java.util.List;

import org.asalas.desafio.domain.Phone;
import org.asalas.desafio.domain.Usuario;
import org.asalas.desafio.dto.PhoneDto;
import org.asalas.desafio.dto.UsuarioDto;

public class UsuarioUtils {
public static Usuario convertUsuarioDtoToUsuario(UsuarioDto usuarioDto) {
		
		List<Phone> phones = new ArrayList<Phone>();
		for (PhoneDto phoneDto : usuarioDto.getPhones()) {
			phones.add(PhoneUtils.convertPhoneDtoToPhone(phoneDto));
		}
		
		Usuario user = new Usuario();
		user.setId(usuarioDto.getId());
		user.setCreated(usuarioDto.getCreated());
		user.setModified(usuarioDto.getModified());
		user.setLastLogin(usuarioDto.getLastLogin());
		user.setToken(usuarioDto.getToken());
		user.setName(usuarioDto.getName());
		user.setEmail(usuarioDto.getEmail());
		user.setPassword(usuarioDto.getPassword());
		user.setPhones(phones);
		user.setProfile(usuarioDto.getProfile());
		
		return user;	
	}
	
	public static UsuarioDto convertUsuarioToUsuarioDto(Usuario user) {
		
		List<PhoneDto> phones = new ArrayList<PhoneDto>();
		for (Phone phone : user.getPhones()) {
			phones.add(PhoneUtils.convertPhoneToPhoneDto(phone));
		}
		
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto.setId(user.getId());
		usuarioDto.setCreated(user.getCreated());
		usuarioDto.setModified(user.getModified());
		usuarioDto.setLastLogin(user.getLastLogin());
		usuarioDto.setToken(user.getToken());
		usuarioDto.setName(user.getName());
		usuarioDto.setEmail(user.getEmail());
		usuarioDto.setPassword(user.getPassword());
		usuarioDto.setPhones(phones);
		usuarioDto.setProfile(user.getProfile());
		
		return usuarioDto;	
	}	
}