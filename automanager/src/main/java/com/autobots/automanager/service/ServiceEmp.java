package com.autobots.automanager.service;

import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.repository.RepositorioEmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceEmp {

    @Autowired
    private RepositorioEmp repositorio;


    public void create(Emp emp) {
        repositorio.save(emp);
    }


    public List<Emp> findAll(){
        List<Emp> emps = repositorio.findAll();
        return emps;
    }

    public Emp findById(Long id) {
        Emp emp = repositorio.findById(id).orElse(null);
        return emp;
    }

    public Emp update(Emp empAtualizacao) {
        Emp empExistente = findById(empAtualizacao.getId());
        empAtualizacao.setId(empExistente.getId());
        empAtualizacao.setRazaoSocial(empExistente.getRazaoSocial());
        empAtualizacao.setCadastro(empExistente.getCadastro());
        if (empAtualizacao.getServices() == null){
            empAtualizacao.setServices(empExistente.getServices());
        }
        if (empAtualizacao.getMercadorias() == null){
            empAtualizacao.setMercadorias(empExistente.getMercadorias());
        }
        if (empAtualizacao.getUsers() == null){
            empAtualizacao.setUsers(empExistente.getUsers());
        }
        if (empAtualizacao.getVendas() == null){
            empAtualizacao.setVendas(empExistente.getVendas());
        }
        if (empAtualizacao.getEnd() == null){
            empAtualizacao.setEnd(empExistente.getEnd());
        }
        if (empAtualizacao.getTels() == null){
            empAtualizacao.setTels(empExistente.getTels());
        }

        return repositorio.save(empAtualizacao);
    }

    public void delete(Emp emp) {
        repositorio.delete(emp);
    }

}
