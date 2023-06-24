package com.autobots.automanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.adder.AddLinkDoc;
import com.autobots.automanager.entity.Client;
import com.autobots.automanager.entity.Doc;
import com.autobots.automanager.model.SelectCliDoc;
import com.autobots.automanager.model.AtualizadorDoc;
import com.autobots.automanager.model.SelectDoc;
import com.autobots.automanager.repository.RepositorioCli;
import com.autobots.automanager.repository.RepositorioDoc;

@RestController
@RequestMapping("/doc")
public class DocControle {
	@Autowired
	private RepositorioDoc repositorioDocumento;
	@Autowired
	private RepositorioCli repositorioCliente;
	@Autowired
	private SelectDoc selecionadorDocumento;
	@Autowired
	private SelectCliDoc selecionadorCliDocumento;
	@Autowired
	private AddLinkDoc adicionadorLinkDocumento;

	@GetMapping("/findOne/{id}")
	public ResponseEntity<Doc> obterDoc(@PathVariable long id) {
		List<Doc> docs = repositorioDocumento.findAll();
		Doc documento = selecionadorDocumento.selecionar(docs, id);
		if (documento == null) {
			ResponseEntity<Doc> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLinkDocumento.addLink(documento);
			List<Client> clients = repositorioCliente.findAll();
			for (Doc doc : docs) {
				Client client = selecionadorCliDocumento.selecionar(clients, documento);
				adicionadorLinkDocumento.addLink(doc, client);
			}
			ResponseEntity<Doc> resposta = new ResponseEntity<Doc>(documento, HttpStatus.FOUND);
			return resposta;
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Doc>> obterDoc() {
		List<Doc> docs = repositorioDocumento.findAll();
		if (docs.isEmpty()) {
			ResponseEntity<List<Doc>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLinkDocumento.addLink(docs);
			List<Client> clients = repositorioCliente.findAll();
			for (Doc doc : docs) {
				Client client = selecionadorCliDocumento.selecionar(clients, doc);
				adicionadorLinkDocumento.addLink(doc, client);
			}
			ResponseEntity<List<Doc>> resposta = new ResponseEntity<>(docs, HttpStatus.FOUND);
			return resposta;
		}
	}

	@PostMapping("/cad/{id}")
	public ResponseEntity<?> cadDoc(@RequestBody Doc doc, @PathVariable long id) {
		HttpStatus status = HttpStatus.CONFLICT;
		if (doc.getId() == null) {
			Client client = repositorioCliente.getById(id);
			client.getDocs().add(doc);
			repositorioCliente.save(client);
			status = HttpStatus.CREATED;
		}
		return new ResponseEntity<>(status);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateDoc(@RequestBody Doc atualizacao) {
		HttpStatus status = HttpStatus.CONFLICT;
		Doc doc = repositorioDocumento.getById(atualizacao.getId());
		if (doc != null) {
			AtualizadorDoc atualizador = new AtualizadorDoc();
			atualizador.atualizar(doc, atualizacao);
			repositorioDocumento.save(doc);
			adicionadorLinkDocumento.addLink(doc);
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(status);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteDoc(@RequestBody Doc exclusao, @PathVariable long id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Doc doc = repositorioDocumento.getById(exclusao.getId());
		if (doc != null) {
			Client client = repositorioCliente.getById(id);
			client.getDocs().remove(doc);
			repositorioCliente.save(client);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);
		
	}
}
	