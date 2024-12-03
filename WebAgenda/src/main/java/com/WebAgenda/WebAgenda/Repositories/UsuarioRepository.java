package com.WebAgenda.WebAgenda.Repositories;

import com.WebAgenda.WebAgenda.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}