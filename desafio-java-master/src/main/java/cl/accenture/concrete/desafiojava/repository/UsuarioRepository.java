package cl.accenture.concrete.desafiojava.repository;

import cl.accenture.concrete.desafiojava.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {

    //Buscador del mail
    boolean existsByEmail(String email);

    //Buscador de id
    boolean findById(Integer id);

    //Buscador del login
    List<Usuario> findByEmailAndPassword(String email, String password);

    //Buscador de usuario
    Usuario findByEmail(String email);


}
