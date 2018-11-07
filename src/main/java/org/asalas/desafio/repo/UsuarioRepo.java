package org.asalas.desafio.repo;

import org.asalas.desafio.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Long> {
	Usuario findByEmail(String email);
	
	Usuario findByEmailAndPassword(String email, String password);
}
