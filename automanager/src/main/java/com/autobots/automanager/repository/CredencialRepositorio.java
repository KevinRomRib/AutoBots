package com.autobots.automanager.repository;

import com.autobots.automanager.entity.CredencialUsuarioSenha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CredencialRepositorio extends JpaRepository<CredencialUsuarioSenha, Long> {

}
