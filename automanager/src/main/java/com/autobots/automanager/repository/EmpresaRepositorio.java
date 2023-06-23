package com.autobots.automanager.repository;

import com.autobots.automanager.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmpresaRepositorio extends JpaRepository<Empresa, Long> {

}