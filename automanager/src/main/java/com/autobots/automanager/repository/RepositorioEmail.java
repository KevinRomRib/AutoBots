package com.autobots.automanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entity.Email;


public interface RepositorioEmail extends JpaRepository<Email, Long> {

}
