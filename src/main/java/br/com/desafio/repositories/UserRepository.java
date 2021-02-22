package br.com.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	
	User findByEmailAndPassword(String email, String password);

}
