package com.autobots.automanager.repository;

import com.autobots.automanager.entity.Doc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioDoc extends JpaRepository<Doc, Long> {
}
