package com.autobots.funcionarios.service;

import com.autobots.funcionarios.entity.User;
import com.autobots.funcionarios.repository.RepositorioUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private RepositorioUser repositorio;

    public Long create(User user) {
        return repositorio.save(user).getId();
    }

    public List<User> findAll(){
        List<User> users = repositorio.findAll();
        return users;
    }

    public User findById(Long id) {
        User user = repositorio.findById(id).orElse(null);
        return user;
    }

    public User update(User userAtualizacao) {
        User userExistente = findById(userAtualizacao.getId());
        userAtualizacao.setId(userExistente.getId());

        if (userAtualizacao.getNome() == null){
            userAtualizacao.setNome(userExistente.getNome());
        }
        if (userAtualizacao.getNomeSocial() == null){
            userAtualizacao.setNomeSocial(userExistente.getNomeSocial());
        }
        if (userAtualizacao.getPerfis() == null){
            userAtualizacao.setPerfis(userExistente.getPerfis());
        }
        if (userAtualizacao.getTels() == null){
            userAtualizacao.setTels(userExistente.getTels());
        }
        if (userAtualizacao.getEnd() == null){
            userAtualizacao.setEnd(userExistente.getEnd());
        }
        if (userAtualizacao.getDocs() == null){
            userAtualizacao.setDocs(userExistente.getDocs());
        }
        if (userAtualizacao.getEmails() == null){
            userAtualizacao.setEmails(userExistente.getEmails());
        }
        if (userAtualizacao.getCredenciais() == null){
            userAtualizacao.setCredenciais(userExistente.getCredenciais());
        }
        if (userAtualizacao.getMercadorias() == null){
            userAtualizacao.setMercadorias(userExistente.getMercadorias());
        }
        if (userAtualizacao.getVendas() == null){
            userAtualizacao.setVendas(userExistente.getVendas());
        }
        if (userAtualizacao.getVeiculos() == null){
            userAtualizacao.setVeiculos(userExistente.getVeiculos());
        }
        return repositorio.save(userAtualizacao);
    }

    public void delete(User user) {
        repositorio.delete(user);
    }
}
