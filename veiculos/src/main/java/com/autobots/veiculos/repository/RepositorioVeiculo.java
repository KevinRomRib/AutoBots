package com.autobots.veiculos.repository;


import com.autobots.veiculos.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioVeiculo extends JpaRepository<Veiculo, Long> {

}
