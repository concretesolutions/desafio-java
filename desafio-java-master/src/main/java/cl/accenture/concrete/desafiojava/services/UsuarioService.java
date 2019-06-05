package cl.accenture.concrete.desafiojava.services;

import cl.accenture.concrete.desafiojava.models.Usuario;
import cl.accenture.concrete.desafiojava.repository.UsuarioRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils.*;

import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository=usuarioRepository;
    }

    public Usuario buscarUsuario(String email){
        return usuarioRepository.findByEmail(email);
    }

    public Boolean existeEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public void registrarUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public List<Usuario> validador(String email, String password){
        return usuarioRepository.findByEmailAndPassword(email, password);
    }

    public String encriptar(String password) {

        String result = DigestUtils.md5Hex(password);
        return result;

    }
    public void modificarFecha(Usuario usuario){
        usuarioRepository.save(usuario);
    }

}
