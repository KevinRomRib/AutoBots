package com.autobots.automanager.controller;

import com.autobots.automanager.entity.Doc;
import com.autobots.automanager.entity.Tel;
import com.autobots.automanager.model.TelefoneAtualizador;
import com.autobots.automanager.model.TelefoneSelecionador;
import com.autobots.automanager.repositorios.RepositorioTel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tel")
public class TelControle {

    @Autowired
    private RepositorioTel repositorio;

    @Autowired
    private TelefoneSelecionador selecionador;

    @GetMapping("/findAll")
    public List<Tel> buscarTel(){
        List<Tel> tels = repositorio.findAll();
        return tels;
    }

    @GetMapping("/findOne/{id}")
    public Tel buscarTelPorId(@PathVariable Long id){
        List<Tel> tel = repositorio.findAll();
        return selecionador.selecionar(tel, id);
    }

    @PostMapping("/cad")
    public void cadTel(@RequestBody Tel tel) {
        repositorio.save(tel);
    }


    @PutMapping("/update")
    public void updateTel(@RequestBody Tel atualizacao) {
        Tel tel = repositorio.getById(atualizacao.getId());
        TelefoneAtualizador atualizador = new TelefoneAtualizador();
        atualizador.atualizar(tel, atualizacao);
        repositorio.save(tel);
    }

    @DeleteMapping("/delete")
    public void deleteTel(@RequestBody Doc exclusao) {
        Tel tel = repositorio.getById(exclusao.getId());
        repositorio.delete(tel);
    }
}
