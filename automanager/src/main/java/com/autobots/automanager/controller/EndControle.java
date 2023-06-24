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

import com.autobots.automanager.adder.AddLinkEnd;
import com.autobots.automanager.entity.Client;
import com.autobots.automanager.entity.End;
import com.autobots.automanager.model.SelectCliEnd;
import com.autobots.automanager.model.AtualizadorEnd;
import com.autobots.automanager.model.SelectEnd;
import com.autobots.automanager.repository.RepositorioCli;
import com.autobots.automanager.repository.RepositorioEnd;

@RestController
@RequestMapping("/end")
public class EndControle {
	@Autowired
	private RepositorioEnd repositorioEndereco;
	@Autowired
	private RepositorioCli repositorioCliente;
	@Autowired
	private SelectEnd selecionadorEndereco;
	@Autowired
	private SelectCliEnd selecionadorCliEndereco;
	@Autowired
	private AddLinkEnd adicionadorLinkEndereco;
	
	@GetMapping("/findOne/{id}")
	public ResponseEntity<End> obterEnd(@PathVariable long id) {
		List<End> ends = repositorioEndereco.findAll();
		End end = selecionadorEndereco.selecionar(ends, id);
		if (end == null) {
			ResponseEntity<End> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLinkEndereco.addLink(end);
			List<Client> clients = repositorioCliente.findAll();
			Client client = selecionadorCliEndereco.selecionar(clients, end);
			adicionadorLinkEndereco.addLink(end, client);
			ResponseEntity<End> resposta = new ResponseEntity<End>(end, HttpStatus.FOUND);
			return resposta;
		}
		
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<End>> obterEnd() {
		List<End> ends = repositorioEndereco.findAll();
		if (ends.isEmpty()) {
			ResponseEntity<List<End>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLinkEndereco.addLink(ends);
			List<Client> clients = repositorioCliente.findAll();
			for (End end : ends) {
				Client client = selecionadorCliEndereco.selecionar(clients, end);
				adicionadorLinkEndereco.addLink(end, client);
			}
			ResponseEntity<List<End>> resposta = new ResponseEntity<>(ends, HttpStatus.FOUND);
			return resposta;
		}
		
	}

	@PostMapping("/cad/{id}")
	public ResponseEntity<?> cadEnd(@RequestBody End end, @PathVariable long id) {
		HttpStatus status = HttpStatus.CONFLICT;
		if (end.getId() == null) {
			Client client = repositorioCliente.getById(id);
			client.setEnd(end);
			repositorioCliente.save(client);
			status = HttpStatus.CREATED;
		}
		return new ResponseEntity<>(status);
		 
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateEnd(@RequestBody End atualizacao) {
		HttpStatus status = HttpStatus.CONFLICT;
		End end = repositorioEndereco.getById(atualizacao.getId());
		if (end != null) {
			AtualizadorEnd atualizador = new AtualizadorEnd();
			atualizador.atualizar(end, atualizacao);
			repositorioEndereco.save(end);
			adicionadorLinkEndereco.addLink(end);
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(status); 
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEnd(@PathVariable long id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Client client = repositorioCliente.getById(id);
		if (client.getEnd()!= null) {
		    client.setEnd(null);
		    repositorioCliente.save(client);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);

	}
}
