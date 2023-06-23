package com.autobots.automanager.repository;

import com.autobots.automanager.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepositorio extends JpaRepository<Veiculo, Long> {

}
