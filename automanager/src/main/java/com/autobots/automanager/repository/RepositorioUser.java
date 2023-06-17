package com.autobots.automanager.repository;

import com.autobots.automanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositorioUser extends JpaRepository<User, Long> {

}
