package com.autobots.automanager.controller;

import com.autobots.automanager.entity.End;
import com.autobots.automanager.model.AtualizadorEnd;
import com.autobots.automanager.model.SelectEnd;
import com.autobots.automanager.repositorios.RepositorioEnd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/end")
public class EndControle {
    @Autowired
    private RepositorioEnd repositorio;

    @Autowired
    private SelectEnd selecionador;

    @GetMapping("/findAll")
    public List<End> buscarEnd(){
        List<End> ends = repositorio.findAll();
        return ends;
    }

    @GetMapping("/findOne/{id}")
    public End buscarEndPorId(@PathVariable Long id){
        List<End> end = repositorio.findAll();
        return selecionador.selecionar(end, id);
    }

    @PostMapping("/cad")
    public void cadEnd(@RequestBody End end) {
        repositorio.save(end);
    }


    @PutMapping("/update")
    public void updateEnd(@RequestBody End atualizacao) {
        End end = repositorio.getById(atualizacao.getId());
        AtualizadorEnd atualizador = new AtualizadorEnd();
        atualizador.atualizar(end, atualizacao);
        repositorio.save(end);
    }

    @DeleteMapping("/delete")
    public void deleteEnd(@RequestBody End exclusao) {
        End end = repositorio.getById(exclusao.getId());
        repositorio.delete(end);
    }
}
