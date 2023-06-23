package com.autobots.automanager.repository;

import com.autobots.automanager.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepositorio extends JpaRepository<Servico, Long> {

}
