package com.autobots.automanager.repository;

import com.autobots.automanager.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepositorio extends JpaRepository<Telefone, Long> {
}
