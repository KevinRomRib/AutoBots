package com.autobots.mercadorias.repository;


import com.autobots.mercadorias.entity.Mercadoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositorioMercadoria extends JpaRepository<Mercadoria, Long> {

}
