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

import com.autobots.automanager.adder.AddLinkTel;
import com.autobots.automanager.entity.Client;
import com.autobots.automanager.entity.Tel;
import com.autobots.automanager.model.SelectCliTel;
import com.autobots.automanager.model.AtualizadorTel;
import com.autobots.automanager.model.SelectTel;
import com.autobots.automanager.repository.RepositorioCli;
import com.autobots.automanager.repository.RepositorioTel;

@RestController
@RequestMapping("/tel")
public class TelControle {
	@Autowired
	private RepositorioTel repositorioTelefone;
	@Autowired
	private RepositorioCli repositorioCliente;
	@Autowired
	private SelectTel selecionadorTelefone;
	@Autowired
	private SelectCliTel selecionadorCliTelefone;
	@Autowired
	private AddLinkTel adicionadorLinkTelefone;

	@GetMapping("/findOne/{id}")
	public ResponseEntity<Tel> obterTel(@PathVariable long id) {
		List<Tel> tels = repositorioTelefone.findAll();
		Tel telefone = selecionadorTelefone.selecionar(tels, id);
		if (telefone == null) {
			ResponseEntity<Tel> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLinkTelefone.addLink(telefone);
			List<Client> clients = repositorioCliente.findAll();
			for (Tel tel : tels) {
				Client client = selecionadorCliTelefone.selecionar(clients, telefone);
				adicionadorLinkTelefone.addLink(tel, client);
			}
			ResponseEntity<Tel> resposta = new ResponseEntity<Tel>(telefone, HttpStatus.FOUND);
			return resposta;
		} 
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Tel>> obterTel() {
		List<Tel> tels = repositorioTelefone.findAll();
		if (tels.isEmpty()) {
			ResponseEntity<List<Tel>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLinkTelefone.addLink(tels);
			List<Client> clients = repositorioCliente.findAll();
			for (Tel tel : tels) {
				Client client = selecionadorCliTelefone.selecionar(clients, tel);
				adicionadorLinkTelefone.addLink(tel, client);
			}
			ResponseEntity<List<Tel>> resposta = new ResponseEntity<>(tels, HttpStatus.FOUND);
			return resposta;
		}
	}

	@PostMapping("/cad/{id}")
	public ResponseEntity<?> cadTel(@RequestBody Tel tel, @PathVariable long id) {
		HttpStatus status = HttpStatus.CONFLICT;
		if (tel.getId() == null) {
			Client client = repositorioCliente.getById(id);
			client.getTels().add(tel);
		    repositorioCliente.save(client);
			status = HttpStatus.CREATED;
		}
		return new ResponseEntity<>(status);

	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateTel(@RequestBody Tel atualizacao) {
		HttpStatus status = HttpStatus.CONFLICT;
		Tel tel = repositorioTelefone.getById(atualizacao.getId());
		if (tel != null) {
			AtualizadorTel atualizador = new AtualizadorTel();
			atualizador.atualizar(tel, atualizacao);
			repositorioTelefone.save(tel);
			adicionadorLinkTelefone.addLink(tel);
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(status);
		
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteTel(@RequestBody Tel exclusao, @PathVariable long id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Tel tel = repositorioTelefone.getById(exclusao.getId());
		if (tel != null) {
			Client client = repositorioCliente.getById(id);
			client.getTels().remove(tel);
			repositorioCliente.save(client);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);
		
	}
}
