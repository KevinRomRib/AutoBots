package com.autobots.automanager.repository;

import com.autobots.automanager.entity.LoginDeUsuario;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RepositorioCredencial extends JpaRepository<LoginDeUsuario, Long> {

}
