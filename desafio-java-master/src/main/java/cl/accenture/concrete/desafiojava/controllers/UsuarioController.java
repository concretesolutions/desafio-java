package cl.accenture.concrete.desafiojava.controllers;

import cl.accenture.concrete.desafiojava.models.Usuario;
import cl.accenture.concrete.desafiojava.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService=usuarioService;
    }

    @PostMapping("registro")
    public boolean registro(@RequestBody Usuario usuario){
        if (usuarioService.existeEmail(usuario.getEmail())){
            System.out.println("Este email ya se encuentra registrado");
            return false;
        }
        else {
            Usuario usuario1=new Usuario(usuario.getName(),usuario.getEmail(),usuario.getTelefonos(),usuario.getPassword());
            usuario1.setPassword(usuario1.encriptar(usuario1.getPassword()));
            usuario1.setUltimo_login(usuario1.dateToDate(new Date())+" "+usuario1.dateToTime(new Date()));
            usuarioService.registrarUsuario(usuario1);
            System.out.println("Guardado correctamente");
            return true;
        }



    }

    @PutMapping ("/login")
    public Usuario login(@RequestBody Usuario usuario){
        String newPass=usuarioService.encriptar(usuario.getPassword());
        String newEmail=usuario.getEmail();
        List<Usuario> usuarios=usuarioService.validador(newEmail,newPass);
        if (usuarios.size()!=0){
            usuarios.get(0).getUltimo_login();
            usuarios.get(0).setUltimo_login(usuario.dateToDate(new Date())+" "+usuario.dateToTime(new Date()));
            usuarioService.modificarFecha(usuarios.get(0));
            Usuario usu=usuarioService.buscarUsuario(usuario.getEmail());
            System.out.println("Bienvenido");
            System.out.println(toString());
            return usu;
        }
        else {
            System.out.println("Rut o clave incorrectos");
            return null;
        }
    }

    @GetMapping("/getusuario" )
    public Usuario mostrarUsuario(@RequestBody Usuario usuario){
        Usuario usu=usuarioService.buscarUsuario(usuario.getEmail());
        return usu;

    }

}
