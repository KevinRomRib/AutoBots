package com.autobots.automanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entity.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
}