package com.autobots.automanager.controller;

import com.autobots.automanager.entity.Endereco;
import com.autobots.automanager.model.AdicionadorLinkEndereco;
import com.autobots.automanager.model.EnderecoAtualizador;
import com.autobots.automanager.model.EnderecoSelecionador;
import com.autobots.automanager.repository.EnderecoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/end")
public class EndControle {
    @Autowired
    private EnderecoRepositorio repositorio;

    @Autowired
    private EnderecoSelecionador selecionador;

    @Autowired
    private AdicionadorLinkEndereco adicionadorLink;


    @GetMapping("/findAll")
    public ResponseEntity<List<Endereco>> buscarEnd(){
        List<Endereco> enderecos = repositorio.findAll();
        if (enderecos.isEmpty()) {
            ResponseEntity<List<Endereco>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;
        } else {
            adicionadorLink.adicionarLink(enderecos);
            ResponseEntity<List<Endereco>> resposta = new ResponseEntity<>(enderecos, HttpStatus.FOUND);
            return resposta;
        }
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<Endereco> buscarEndPorId(@PathVariable Long id){
        List<Endereco> enderecos = repositorio.findAll();
        Endereco endereco = selecionador.selecionar(enderecos, id);
        if (endereco == null) {
            ResponseEntity<Endereco> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;
        } else {
            adicionadorLink.adicionarLink(endereco);
            ResponseEntity<Endereco> resposta = new ResponseEntity<>(endereco, HttpStatus.FOUND);
            return resposta;
        }
    }

    @PostMapping("/cad")
    public ResponseEntity<?> cadEnd(@RequestBody Endereco endereco) {
        HttpStatus status = HttpStatus.CONFLICT;
        if (endereco.getId() == null) {
            repositorio.save(endereco);
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(status);
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateEnd(@RequestBody Endereco atualizacao) {
        HttpStatus status = HttpStatus.CONFLICT;
        Endereco endereco = repositorio.getById(atualizacao.getId());
        if (endereco != null) {
            EnderecoAtualizador atualizador = new EnderecoAtualizador();
            atualizador.atualizar(endereco, atualizacao);
            repositorio.save(endereco);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEnd(@RequestBody Endereco exclusao) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Endereco endereco = repositorio.getById(exclusao.getId());
        if (endereco != null) {
            repositorio.delete(endereco);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(status);
    }
}
