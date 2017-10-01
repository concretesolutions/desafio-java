package br.com.desafio.services.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.desafio.entities.Phone;
import br.com.desafio.entities.User;
import br.com.desafio.enums.ProfileEnum;
import br.com.desafio.exceptions.EmailAlreadyExistsException;
import br.com.desafio.exceptions.InvalidSessionException;
import br.com.desafio.exceptions.NotAuthorizedException;
import br.com.desafio.exceptions.UserAndOrPasswordInvalidException;
import br.com.desafio.repositories.UserRepository;
import br.com.desafio.security.utils.JwtTokenUtils;
import br.com.desafio.services.PhoneService;
import br.com.desafio.services.UserService;
import br.com.desafio.utils.PasswordUtils;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PhoneService phoneService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtils jwtTokenUtil;
	
	/* (non-Javadoc)
	 * @see br.com.desafio.services.UserService#register(br.com.desafio.entities.User)
	 * Método recebe o usuário e faz as verificações a partir do usuário passado no login
	 * se o email for encontrado usuário já existe e é lançada a exception com a mensagem de erro
	 * se não ele cria um novo usuário, encripta o password e salva no banco.
	 * após isso ele gera o token utilizando o user que vem do Spring Security e faz o update no banco
	 */
	@Override
	@Transactional
	public User register(User user) throws EmailAlreadyExistsException {
		
		if (userRepository.findByEmail(user.getEmail()) != null) {
			throw new EmailAlreadyExistsException();
		}
		
		Calendar calendar = Calendar.getInstance();
		
		String password = PasswordUtils.generateBCrypt(user.getPassword());
		
		List<Phone> phones = new ArrayList<Phone>();
		for (Phone phone : user.getPhones()) {
			phones.add(phoneService.save(phone));
		}
		
		user.setCreated(calendar);
		user.setModified(calendar);
		user.setLastLogin(calendar);
		user.setPassword(password);
		user.setPhones(phones);
		user.setProfile(ProfileEnum.ROLE_USER);
		
		user = userRepository.save(user);
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
		
		String token = jwtTokenUtil.getToken(userDetails);
		user.setToken(token);
		
		user = userRepository.save(user);
		
		return user;
		
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return Optional.ofNullable(userRepository.findByEmail(email));
	}
	
	
	/* (non-Javadoc)
	 * @see br.com.desafio.services.UserService#register(br.com.desafio.entities.User)
	 * Método recebe o usuário e senha para realizar o login
	 * se usuário for nulo, lança exception com a mensagem de erro correspondente
	 * depois recebe o password por parâmetro faz a encriptação e compara, caso seja falso, lança a exception correspondente
	 * se não, modifica a data de login e ultima modificação e faz o update no banco
	 */
	@Override
	@Transactional
	public User login(String email, String password) throws UserAndOrPasswordInvalidException {
		
		User user = userRepository.findByEmail(email);
		
		if (user == null) {
			throw new UserAndOrPasswordInvalidException();
		}
		
		if (PasswordUtils.validPassword(password, user.getPassword()) == false) {
			throw new UserAndOrPasswordInvalidException();
		}
		
		//Depois que o token se expirar preciso renovar?
		/*if(!jwtTokenUtil.validToken(user.getToken())) { 
			
			user.setToken(jwtTokenUtil.refreshToken(user.getToken()));
		}*/
		
		Calendar calendar = Calendar.getInstance();
		
		user.setModified(calendar);
		user.setLastLogin(calendar);
		
		user = userRepository.save(user);
		
		return user;
		
	}
	
	
	/* (non-Javadoc)
	 * @see br.com.desafio.services.UserService#register(br.com.desafio.entities.User)
	 * Método recebe o usuário e o token para validar a sessão
	 * se usuário for nulo, lança exception com a mensagem de erro correspondente
	 * se o token passado na requisição for diferente do token do banco, lança a exception correspondente
	 * se o ultimo login do banco tiver mais que 30 minutos do login atual, lança exception correspondente 
	 */
	@Override
	public User getUser(Long id, String token) throws NotAuthorizedException, InvalidSessionException {
		
		User user = userRepository.findOne(id);
		
		if (user == null) {
			throw new NotAuthorizedException();
		}
		
		if (!user.getToken().equals(token)) {
			throw new NotAuthorizedException();
		}
		
		long difference = System.currentTimeMillis() - user.getLastLogin().getTimeInMillis();
		long differenceInMinutes = difference / (60 * 1000);
		if (differenceInMinutes >= 30) {
			throw new InvalidSessionException();
		}
		
		return user;
		
	}
	
}
