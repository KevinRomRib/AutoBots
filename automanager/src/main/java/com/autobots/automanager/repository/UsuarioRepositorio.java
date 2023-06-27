package com.autobots.automanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entity.User;


public interface UsuarioRepositorio extends JpaRepository<User, Long> {
	User findByNome(String nomeUsuario);
}
 