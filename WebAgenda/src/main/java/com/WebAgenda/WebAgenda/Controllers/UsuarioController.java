package com.WebAgenda.WebAgenda.Controllers;

import com.WebAgenda.WebAgenda.Model.Usuario;
import com.WebAgenda.WebAgenda.Model.Vacina;
import com.WebAgenda.WebAgenda.Repositories.AgendaRepository;
import com.WebAgenda.WebAgenda.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Integer id) {
        usuarioRepository.deleteById(id);
    }
}
