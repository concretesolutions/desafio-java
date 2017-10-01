package br.com.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.entities.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
