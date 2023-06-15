package com.autobots.automanager.controller;

import com.autobots.automanager.entity.Doc;
import com.autobots.automanager.model.AtualizadorDoc;
import com.autobots.automanager.model.SelectDoc;
import com.autobots.automanager.repositorios.RepositorioDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocControle {

    @Autowired
    private RepositorioDoc repositorio;

    @Autowired
    private SelectDoc selecionador;

    @GetMapping("/findAll")
    public List<Doc> buscarDoc(){
        List<Doc> docs = repositorio.findAll();
        return docs;
    }

    @GetMapping("/findOne/{id}")
    public Doc buscarDocPorId(@PathVariable Long id){
        List<Doc> doc = repositorio.findAll();
        return selecionador.selecionar(doc, id);
    }

    @PostMapping("/cad")
    public void cadDoc(@RequestBody Doc doc) {
        repositorio.save(doc);
    }


    @PutMapping("/update")
    public void updateDoc(@RequestBody Doc atualizacao) {
        Doc doc = repositorio.getById(atualizacao.getId());
        AtualizadorDoc atualizador = new AtualizadorDoc();
        atualizador.atualizar(doc, atualizacao);
        repositorio.save(doc);
    }

    @DeleteMapping("/delete")
    public void deleteDoc(@RequestBody Doc exclusao) {
        Doc doc = repositorio.getById(exclusao.getId());
        repositorio.delete(doc);
    }
}
