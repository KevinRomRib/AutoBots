package com.autobots.clientes.repository;

import com.autobots.clientes.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositorioClient extends JpaRepository<Client, Long> {
}