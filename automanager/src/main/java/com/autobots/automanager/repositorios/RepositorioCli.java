package com.autobots.automanager.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entity.Client;

public interface RepositorioCli extends JpaRepository<Client, Long> {
}