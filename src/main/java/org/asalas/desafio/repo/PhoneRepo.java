package org.asalas.desafio.repo;

import org.asalas.desafio.domain.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepo extends JpaRepository<Phone, Long> {

}
