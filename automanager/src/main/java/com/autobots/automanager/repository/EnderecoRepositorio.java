package com.autobots.automanager.repository;

import com.autobots.automanager.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepositorio extends JpaRepository<Endereco, Long> {
}
