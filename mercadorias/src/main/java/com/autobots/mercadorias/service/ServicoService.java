package com.autobots.mercadorias.service;



import com.autobots.mercadorias.entity.Service;
import com.autobots.mercadorias.repository.RepositorioService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServicoService {
    @Autowired
    private RepositorioService repositorio;

    public Long create(Service service) {
        return repositorio.save(service).getId();
    }

    public List<Service> findAll(){
        List<Service> services = repositorio.findAll();
        return services;
    }

    public Service findById(Long id) {
        Service service = repositorio.findById(id).orElse(null);
        return service;
    }

    public Service update(Service serviceAtualizacao) {
        Service serviceExistente = findById(serviceAtualizacao.getId());
        serviceAtualizacao.setId(serviceExistente.getId());

        if (serviceAtualizacao.getNome() == null){
            serviceAtualizacao.setNome(serviceExistente.getNome());
        }
        if (serviceAtualizacao.getDescricao() == null){
            serviceAtualizacao.setDescricao(serviceExistente.getDescricao());
        }
        return repositorio.save(serviceAtualizacao);
    }

    public void delete(Service service) {
        repositorio.delete(service);
    }

}
