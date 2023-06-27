package com.autobots.clientes.controller;



import com.autobots.clientes.entity.Client;
import com.autobots.clientes.model.AddLinkCliente;
import com.autobots.clientes.model.AtualizadorClient;
import com.autobots.clientes.model.SelectClient;
import com.autobots.clientes.repository.RepositorioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cli")
public class ClientController {
	@Autowired
	private RepositorioClient repositorio;
	@Autowired
	private SelectClient selecionador;

	@Autowired
	private AddLinkCliente adicionadorLink;

	@GetMapping("/findOne/{id}")
	public  ResponseEntity<Client> obterCli(@PathVariable long id) {
		List<Client> clients = repositorio.findAll();
		Client client = selecionador.selecionar(clients, id);
		if (client == null) {
			ResponseEntity<Client> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLink.adicionarLink(client);
			ResponseEntity<Client> resposta = new ResponseEntity<Client>(client, HttpStatus.FOUND);
			return resposta;
		}
	}
	@GetMapping("/findAll")
	public ResponseEntity<List<Client>> obterCli() {
		List<Client> clients = repositorio.findAll();
		if (clients.isEmpty()) {
			ResponseEntity<List<Client>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLink.adicionarLink(clients);
			ResponseEntity<List<Client>> resposta = new ResponseEntity<>(clients, HttpStatus.FOUND);
			return resposta;
		}
	}

	@PostMapping("/cad")
	public ResponseEntity<?> cadCli(@RequestBody Client client) {
		HttpStatus status = HttpStatus.CONFLICT;
		if (client.getId() == null) {
			repositorio.save(client);
			status = HttpStatus.CREATED;
		}
		return new ResponseEntity<>(status);

	}

	@PutMapping("/update")
	public ResponseEntity<?> updateCli(@RequestBody Client atualizacao) {
		HttpStatus status = HttpStatus.CONFLICT;
		Client client = repositorio.getById(atualizacao.getId());
		if (client != null) {
			AtualizadorClient atualizador = new AtualizadorClient();
			atualizador.atualizar(client, atualizacao);
			repositorio.save(client);
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(status);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteCli(@RequestBody Client exclusao) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Client client = repositorio.getById(exclusao.getId());
		if (client != null) {
			repositorio.delete(client);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);
	}
}
