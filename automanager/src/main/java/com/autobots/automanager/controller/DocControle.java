package com.autobots.automanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entity.Client;
import com.autobots.automanager.entity.Doc;
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

	@GetMapping("/findOne/{id}")
	public Doc obterDoc(@PathVariable long id) {
		List<Doc> docs = repositorioDocumento.findAll();
		return selecionadorDocumento.selecionar(docs, id);
	}

	@GetMapping("/findAll")
	public List<Doc> obterDoc() {
		List<Doc> docs = repositorioDocumento.findAll();
		return docs;
	}

	@PostMapping("/cad/{id}")
	public void cadDoc(@RequestBody Doc doc, @PathVariable long id) {
		Client client = repositorioCliente.getById(id);
		client.getDocs().add(doc);
	    repositorioCliente.save(client);
		
	}

	@PutMapping("/update")
	public void updateDoc(@RequestBody Doc atualizacao) {
		Doc doc = repositorioDocumento.getById(atualizacao.getId());
		AtualizadorDoc atualizador = new AtualizadorDoc();
		atualizador.atualizar(doc, atualizacao);
		repositorioDocumento.save(doc);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteDoc(@RequestBody Doc exclusao, @PathVariable long id) {
		Client client = repositorioCliente.getById(id);
		List<Doc> documentosCliente = client.getDocs();
		for (Doc doc : documentosCliente) {
	        if (doc.getId() == exclusao.getId()) {
	            documentosCliente.remove(doc);
	            break;
	        }
	    }
	    client.setDocs(documentosCliente);
		repositorioCliente.save(client);
	}
}
