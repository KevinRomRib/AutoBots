package com.autobots.automanager.controller;

import com.autobots.automanager.entity.Tel;
import com.autobots.automanager.model.TelAddLink;
import com.autobots.automanager.model.TelefoneAtualizador;
import com.autobots.automanager.model.TelefoneSelecionador;
import com.autobots.automanager.repository.RepositorioTel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tel")
public class TelControle {

    @Autowired
    private RepositorioTel repositorio;

    @Autowired
    private TelefoneSelecionador selecionador;

    @Autowired
    private TelAddLink adicionadorLink;

    @GetMapping("/findAll")
    public ResponseEntity<List<Tel>> buscarTel(){
        List<Tel> tels = repositorio.findAll();
        if (tels.isEmpty()) {
            ResponseEntity<List<Tel>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;
        } else {
            adicionadorLink.addLink(tels);
            ResponseEntity<List<Tel>> resposta = new ResponseEntity<>(tels, HttpStatus.FOUND);
            return resposta;
        }
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<Tel> buscarTelPorId(@PathVariable Long id){
        List<Tel> tels = repositorio.findAll();
        Tel tel = selecionador.selecionar(tels, id);
        if (tel == null) {
            ResponseEntity<Tel> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;
        } else {
            adicionadorLink.addLink(tel);
            ResponseEntity<Tel> resposta = new ResponseEntity<>(tel, HttpStatus.FOUND);
            return resposta;
        }
    }

    @PostMapping("/cad")
    public ResponseEntity<?> cadTel(@RequestBody Tel tel) {
        HttpStatus status = HttpStatus.CONFLICT;
        if (tel.getId() == null) {
            repositorio.save(tel);
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(status);
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateTel(@RequestBody Tel atualizacao) {
        HttpStatus status = HttpStatus.CONFLICT;
        Tel tel = repositorio.getById(atualizacao.getId());
        if (tel != null) {
            TelefoneAtualizador atualizador = new TelefoneAtualizador();
            atualizador.atualizar(tel, atualizacao);
            repositorio.save(tel);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTel(@RequestBody Tel exclusao) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Tel tel = repositorio.getById(exclusao.getId());
        if (tel != null) {
            repositorio.delete(tel);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(status);
    }
}
