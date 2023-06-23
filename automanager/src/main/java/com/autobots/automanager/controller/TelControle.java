package com.autobots.automanager.controller;

import com.autobots.automanager.entity.Telefone;
import com.autobots.automanager.model.AdicionadorLinkTelefone;
import com.autobots.automanager.model.TelefoneAtualizador;
import com.autobots.automanager.model.TelefoneSelecionador;
import com.autobots.automanager.repository.TelefoneRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tel")
public class TelControle {

    @Autowired
    private TelefoneRepositorio repositorio;

    @Autowired
    private TelefoneSelecionador selecionador;

    @Autowired
    private AdicionadorLinkTelefone adicionadorLink;

    @GetMapping("/findAll")
    public ResponseEntity<List<Telefone>> buscarTel(){
        List<Telefone> telefones = repositorio.findAll();
        if (telefones.isEmpty()) {
            ResponseEntity<List<Telefone>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;
        } else {
            adicionadorLink.adicionarLink(telefones);
            ResponseEntity<List<Telefone>> resposta = new ResponseEntity<>(telefones, HttpStatus.FOUND);
            return resposta;
        }
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<Telefone> buscarTelPorId(@PathVariable Long id){
        List<Telefone> telefones = repositorio.findAll();
        Telefone telefone = selecionador.selecionar(telefones, id);
        if (telefone == null) {
            ResponseEntity<Telefone> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;
        } else {
            adicionadorLink.adicionarLink(telefone);
            ResponseEntity<Telefone> resposta = new ResponseEntity<>(telefone, HttpStatus.FOUND);
            return resposta;
        }
    }

    @PostMapping("/cad")
    public ResponseEntity<?> cadTel(@RequestBody Telefone telefone) {
        HttpStatus status = HttpStatus.CONFLICT;
        if (telefone.getId() == null) {
            repositorio.save(telefone);
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(status);
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateTel(@RequestBody Telefone atualizacao) {
        HttpStatus status = HttpStatus.CONFLICT;
        Telefone telefone = repositorio.getById(atualizacao.getId());
        if (telefone != null) {
            TelefoneAtualizador atualizador = new TelefoneAtualizador();
            atualizador.atualizar(telefone, atualizacao);
            repositorio.save(telefone);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTel(@RequestBody Telefone exclusao) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Telefone telefone = repositorio.getById(exclusao.getId());
        if (telefone != null) {
            repositorio.delete(telefone);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(status);
    }
}
