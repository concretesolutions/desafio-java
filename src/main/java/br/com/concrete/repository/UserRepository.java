package br.com.concrete.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.concrete.domain.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, String> {

	public Optional<UserEntity> findByEmail(String email);

	public Optional<UserEntity> findByToken(String token);

}
