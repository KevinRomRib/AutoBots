package com.autobots.pecas.repository;


import com.autobots.pecas.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioVenda extends JpaRepository<Venda,Long > {

}
