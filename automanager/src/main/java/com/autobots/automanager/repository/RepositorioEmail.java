package com.autobots.automanager.repository;

import com.autobots.automanager.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioEmail extends JpaRepository<Email, Long> {

}
