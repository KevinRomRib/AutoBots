package com.autobots.automanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entity.Mercadoria;


public interface RepositorioMercadoria extends JpaRepository<Mercadoria, Long> {

}
