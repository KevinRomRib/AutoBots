package com.autobots.automanager.repository;

import com.autobots.automanager.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioService extends JpaRepository<Service, Long> {

}
