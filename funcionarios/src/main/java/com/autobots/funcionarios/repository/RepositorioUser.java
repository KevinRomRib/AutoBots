package com.autobots.funcionarios.repository;


import com.autobots.funcionarios.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositorioUser extends JpaRepository<User, Long> {
    User findByName(String nome);
}
