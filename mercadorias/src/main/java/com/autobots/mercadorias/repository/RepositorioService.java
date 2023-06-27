package com.autobots.mercadorias.repository;


import com.autobots.mercadorias.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioService extends JpaRepository<Service, Long> {

}
