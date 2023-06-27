package com.autobots.automanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entity.Doc;


public interface RepositorioDoc extends JpaRepository<Doc, Long> {

}
