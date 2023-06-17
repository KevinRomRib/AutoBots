package com.autobots.automanager.controller;

import com.autobots.automanager.entity.Tel;
import com.autobots.automanager.model.AddLinkTel;
import com.autobots.automanager.model.AtualizadorTel;
import com.autobots.automanager.model.SelectTel;
import com.autobots.automanager.repository.RepositorioTel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/telefone")
public class TelControle {

    @Autowired
    private RepositorioTel repositorio;

    @Autowired
    private SelectTel selecionador;

    @Autowired
    private AddLinkTel adicionadorLink;

    @GetMapping("/telefones")
    public ResponseEntity<List<Tel>> buscarTelefones(){
        List<Tel> tels = repositorio.findAll();
        if (tels.isEmpty()) {
            ResponseEntity<List<Tel>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;
        } else {
            adicionadorLink.adicionarLink(tels);
            ResponseEntity<List<Tel>> resposta = new ResponseEntity<>(tels, HttpStatus.FOUND);
            return resposta;
        }
    }

    @GetMapping("/telefone/{id}")
    public ResponseEntity<Tel> buscarTelefonePorId(@PathVariable Long id){
        List<Tel> tels = repositorio.findAll();
        Tel tel = selecionador.selecionar(tels, id);
        if (tel == null) {
            ResponseEntity<Tel> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;
        } else {
            adicionadorLink.adicionarLink(tel);
            ResponseEntity<Tel> resposta = new ResponseEntity<>(tel, HttpStatus.FOUND);
            return resposta;
        }
    }

    @PostMapping("/telefone")
    public ResponseEntity<?> cadastrarTelefone(@RequestBody Tel tel) {
        HttpStatus status = HttpStatus.CONFLICT;
        if (tel.getId() == null) {
            repositorio.save(tel);
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(status);
    }


    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarTelefone(@RequestBody Tel atualizacao) {
        HttpStatus status = HttpStatus.CONFLICT;
        Tel tel = repositorio.getById(atualizacao.getId());
        if (tel != null) {
            AtualizadorTel atualizador = new AtualizadorTel();
            atualizador.atualizar(tel, atualizacao);
            repositorio.save(tel);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<?> excluirTelefone(@RequestBody Tel exclusao) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Tel tel = repositorio.getById(exclusao.getId());
        if (tel != null) {
            repositorio.delete(tel);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(status);
    }
}
