package com.autobots.automanager.repository;

import com.autobots.automanager.entity.Emp;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositorioEmp extends JpaRepository<Emp, Long> {
	//public Empresa findByRazaoSocial(String nome);
}