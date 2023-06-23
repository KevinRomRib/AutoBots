package com.autobots.automanager.controller;

import com.autobots.automanager.entity.Documento;
import com.autobots.automanager.model.AdicionadorLinkDocumento;
import com.autobots.automanager.model.DocumentoAtualizador;
import com.autobots.automanager.model.DocumentoSelecionador;
import com.autobots.automanager.repository.DocumentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocControle {

    @Autowired
    private DocumentoRepositorio repositorio;

    @Autowired
    private DocumentoSelecionador selecionador;
    @Autowired
    private AdicionadorLinkDocumento adicionadorLink;

    @GetMapping("/findAll")
    public ResponseEntity<List<Documento>> buscarDoc(){
        List<Documento> documentos = repositorio.findAll();
        if (documentos.isEmpty()) {
            ResponseEntity<List<Documento>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;
        } else {
            adicionadorLink.adicionarLink(documentos);
            ResponseEntity<List<Documento>> resposta = new ResponseEntity<>(documentos, HttpStatus.FOUND);
            return resposta;
        }
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<Documento> buscarDocPorId(@PathVariable Long id){
        List<Documento> documentos = repositorio.findAll();
        Documento documento =  selecionador.selecionar(documentos, id);
        if (documento == null) {
            ResponseEntity<Documento> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;
        } else {
            adicionadorLink.adicionarLink(documento);
            ResponseEntity<Documento> resposta = new ResponseEntity<>(documento, HttpStatus.FOUND);
            return resposta;
        }
    }

    @PostMapping("/cad")
    public ResponseEntity<?> cadDoc(@RequestBody Documento documento) {
        HttpStatus status = HttpStatus.CONFLICT;
        if (documento.getId() == null) {
            repositorio.save(documento);
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(status);
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateDoc(@RequestBody Documento atualizacao) {
        HttpStatus status = HttpStatus.CONFLICT;
        Documento documento = repositorio.getById(atualizacao.getId());
        if (documento != null) {
            DocumentoAtualizador atualizador = new DocumentoAtualizador();
            atualizador.atualizar(documento, atualizacao);
            repositorio.save(documento);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteDoc(@RequestBody Documento exclusao) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Documento documento = repositorio.getById(exclusao.getId());
        if (documento != null) {
            repositorio.delete(documento);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(status);

    }
}
