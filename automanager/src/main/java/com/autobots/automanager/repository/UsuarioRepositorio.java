package com.autobots.automanager.repository;

import com.autobots.automanager.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Usuario findByName(String nome);
}
