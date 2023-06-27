package com.autobots.automanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entity.User;


public interface RepositorioUsuario extends JpaRepository<User, Long> {

}
