package com.autobots.automanager.service;

import com.autobots.automanager.repository.RepositorioService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private RepositorioService repositorio;

    public Long create(com.autobots.automanager.entity.Service service) {
        return repositorio.save(service).getId();
    }

    public List<com.autobots.automanager.entity.Service> findAll(){
        List<com.autobots.automanager.entity.Service> services = repositorio.findAll();
        return services;
    }

    public com.autobots.automanager.entity.Service findById(Long id) {
        com.autobots.automanager.entity.Service service = repositorio.findById(id).orElse(null);
        return service;
    }

    public com.autobots.automanager.entity.Service update(com.autobots.automanager.entity.Service serviceAtualizacao) {
        com.autobots.automanager.entity.Service serviceExistente = findById(serviceAtualizacao.getId());
        serviceAtualizacao.setId(serviceExistente.getId());

        if (serviceAtualizacao.getNome() == null){
            serviceAtualizacao.setNome(serviceExistente.getNome());
        }
        if (serviceAtualizacao.getDescricao() == null){
            serviceAtualizacao.setDescricao(serviceExistente.getDescricao());
        }
        return repositorio.save(serviceAtualizacao);
    }

    public void delete(com.autobots.automanager.entity.Service service) {
        repositorio.delete(service);
    }

}
