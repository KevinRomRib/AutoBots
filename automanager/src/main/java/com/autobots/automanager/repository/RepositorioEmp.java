package com.autobots.automanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entity.Emp;

public interface RepositorioEmp extends JpaRepository<Emp, Long> {
	//public Empresa findByRazaoSocial(String nome);
}