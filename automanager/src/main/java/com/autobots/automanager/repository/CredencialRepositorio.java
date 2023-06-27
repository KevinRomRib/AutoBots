package com.autobots.automanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entity.Credencial;


public interface CredencialRepositorio extends JpaRepository<Credencial, Long> {

}
