package com.autobots.automanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entity.Service;


public interface RepositorioServico extends JpaRepository<Service, Long> {

}
