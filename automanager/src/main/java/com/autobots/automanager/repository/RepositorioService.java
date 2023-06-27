package com.autobots.automanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entity.Service;


public interface RepositorioService extends JpaRepository<Service, Long> {

}
