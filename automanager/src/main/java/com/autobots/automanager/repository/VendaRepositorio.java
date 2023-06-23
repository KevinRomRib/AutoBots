package com.autobots.automanager.repository;

import com.autobots.automanager.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepositorio extends JpaRepository<Venda,Long > {

}
