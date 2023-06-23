package com.autobots.automanager.repository;

import com.autobots.automanager.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepositorio extends JpaRepository<Email, Long> {

}
