package com.autobots.automanager.controller;

import com.autobots.automanager.entity.End;
import com.autobots.automanager.model.AddLinkEnd;
import com.autobots.automanager.model.AtualizadorEnd;
import com.autobots.automanager.model.SelectEnd;
import com.autobots.automanager.repository.RepositorioEnd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/end")
public class EndControle {
    @Autowired
    private RepositorioEnd repositorio;

    @Autowired
    private SelectEnd selecionador;

    @Autowired
    private AddLinkEnd adicionadorLink;


    @GetMapping("/findAll")
    public ResponseEntity<List<End>> buscarEnd(){
        List<End> ends = repositorio.findAll();
        if (ends.isEmpty()) {
            ResponseEntity<List<End>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;
        } else {
            adicionadorLink.adicionarLink(ends);
            ResponseEntity<List<End>> resposta = new ResponseEntity<>(ends, HttpStatus.FOUND);
            return resposta;
        }
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<End> buscarEndPorId(@PathVariable Long id){
        List<End> ends = repositorio.findAll();
        End end = selecionador.selecionar(ends, id);
        if (end == null) {
            ResponseEntity<End> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;
        } else {
            adicionadorLink.adicionarLink(end);
            ResponseEntity<End> resposta = new ResponseEntity<>(end, HttpStatus.FOUND);
            return resposta;
        }
    }

    @PostMapping("/cad")
    public ResponseEntity<?> cadEnd(@RequestBody End end) {
        HttpStatus status = HttpStatus.CONFLICT;
        if (end.getId() == null) {
            repositorio.save(end);
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(status);
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateEnd(@RequestBody End atualizacao) {
        HttpStatus status = HttpStatus.CONFLICT;
        End end = repositorio.getById(atualizacao.getId());
        if (end != null) {
            AtualizadorEnd atualizador = new AtualizadorEnd();
            atualizador.atualizar(end, atualizacao);
            repositorio.save(end);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEnd(@RequestBody End exclusao) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        End end = repositorio.getById(exclusao.getId());
        if (end != null) {
            repositorio.delete(end);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(status);
    }
}
