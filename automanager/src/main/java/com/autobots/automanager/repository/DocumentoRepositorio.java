package com.autobots.automanager.repository;

import com.autobots.automanager.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepositorio extends JpaRepository<Documento, Long> {
}
