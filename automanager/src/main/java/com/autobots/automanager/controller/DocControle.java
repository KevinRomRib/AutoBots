package com.autobots.automanager.controller;

import com.autobots.automanager.entity.Doc;
import com.autobots.automanager.model.AddLinkDoc;
import com.autobots.automanager.model.AtualizadorDoc;
import com.autobots.automanager.model.SelectDoc;
import com.autobots.automanager.repository.RepositorioDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocControle {

    @Autowired
    private RepositorioDoc repositorio;

    @Autowired
    private SelectDoc selecionador;
    @Autowired
    private AddLinkDoc adicionadorLink;

    @GetMapping("/findAll")
    public ResponseEntity<List<Doc>> buscarDoc(){
        List<Doc> docs = repositorio.findAll();
        if (docs.isEmpty()) {
            ResponseEntity<List<Doc>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;
        } else {
            adicionadorLink.adicionarLink(docs);
            ResponseEntity<List<Doc>> resposta = new ResponseEntity<>(docs, HttpStatus.FOUND);
            return resposta;
        }
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<Doc> buscarDocPorId(@PathVariable Long id){
        List<Doc> docs = repositorio.findAll();
        Doc doc =  selecionador.selecionar(docs, id);
        if (doc == null) {
            ResponseEntity<Doc> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;
        } else {
            adicionadorLink.adicionarLink(doc);
            ResponseEntity<Doc> resposta = new ResponseEntity<>(doc, HttpStatus.FOUND);
            return resposta;
        }
    }

    @PostMapping("/cad")
    public ResponseEntity<?> cadDoc(@RequestBody Doc doc) {
        HttpStatus status = HttpStatus.CONFLICT;
        if (doc.getId() == null) {
            repositorio.save(doc);
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(status);
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateDoc(@RequestBody Doc atualizacao) {
        HttpStatus status = HttpStatus.CONFLICT;
        Doc doc = repositorio.getById(atualizacao.getId());
        if (doc != null) {
            AtualizadorDoc atualizador = new AtualizadorDoc();
            atualizador.atualizar(doc, atualizacao);
            repositorio.save(doc);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteDoc(@RequestBody Doc exclusao) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Doc doc = repositorio.getById(exclusao.getId());
        if (doc != null) {
            repositorio.delete(doc);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(status);

    }
}
