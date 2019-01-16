package br.com.concrete.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.concrete.domain.UserEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testaIdPersistencia() {
		final String email = "franco@teste.com";
		UserEntity userEntity = new UserEntity();
		userEntity.setId("123");
		userEntity.setToken("XXXXXXXXXXX");
		userEntity.setName("Teste testando");
		userEntity.setEmail(email);
		userEntity.setPassword("UUUUUUUUU");
		this.entityManager.persistAndFlush(userEntity);
		assertTrue(this.userRepository.findById("123").isPresent());
	}
	
	@Test
	public void testaEmailPersistencia() {
		final String email = "franco@teste.com";
		UserEntity userEntity = new UserEntity();
		userEntity.setId("123");
		userEntity.setToken("XXXXXXXXXXX");
		userEntity.setName("Teste testando");
		userEntity.setEmail(email);
		userEntity.setPassword("UUUUUUUUU");
		this.entityManager.persistAndFlush(userEntity);
		assertTrue(this.userRepository.findByEmail(email).isPresent());
	}
	

	
	@Test
	public void testaEmailInexistente() {
		assertFalse(this.userRepository.findByEmail("ssss.@eee.com").isPresent());
	}

}
