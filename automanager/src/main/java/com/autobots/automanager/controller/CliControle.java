package com.autobots.automanager.controller;

import java.util.List;

import com.autobots.automanager.model.AdicionadorLinkCliente;
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

import com.autobots.automanager.entity.Cliente;
import com.autobots.automanager.model.ClienteAtualizador;
import com.autobots.automanager.model.ClienteSelecionador;
import com.autobots.automanager.repository.ClienteRepositorio;

@RestController
@RequestMapping("/cli")
public class CliControle {
	@Autowired
	private ClienteRepositorio repositorio;
	@Autowired
	private ClienteSelecionador selecionador;

	@Autowired
	private AdicionadorLinkCliente adicionadorLink;

	@GetMapping("/findOne/{id}")
	public  ResponseEntity<Cliente> obterCli(@PathVariable long id) {
		List<Cliente> clientes = repositorio.findAll();
		Cliente cliente = selecionador.selecionar(clientes, id);
		if (cliente == null) {
			ResponseEntity<Cliente> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLink.adicionarLink(cliente);
			ResponseEntity<Cliente> resposta = new ResponseEntity<Cliente>(cliente, HttpStatus.FOUND);
			return resposta;
		}
	}
	@GetMapping("/findAll")
	public ResponseEntity<List<Cliente>> obterCli() {
		List<Cliente> clientes = repositorio.findAll();
		if (clientes.isEmpty()) {
			ResponseEntity<List<Cliente>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLink.adicionarLink(clientes);
			ResponseEntity<List<Cliente>> resposta = new ResponseEntity<>(clientes, HttpStatus.FOUND);
			return resposta;
		}
	}

	@PostMapping("/cad")
	public ResponseEntity<?> cadCli(@RequestBody Cliente cliente) {
		HttpStatus status = HttpStatus.CONFLICT;
		if (cliente.getId() == null) {
			repositorio.save(cliente);
			status = HttpStatus.CREATED;
		}
		return new ResponseEntity<>(status);

	}

	@PutMapping("/update")
	public ResponseEntity<?> updateCli(@RequestBody Cliente atualizacao) {
		HttpStatus status = HttpStatus.CONFLICT;
		Cliente cliente = repositorio.getById(atualizacao.getId());
		if (cliente != null) {
			ClienteAtualizador atualizador = new ClienteAtualizador();
			atualizador.atualizar(cliente, atualizacao);
			repositorio.save(cliente);
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(status);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteCli(@RequestBody Cliente exclusao) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Cliente cliente = repositorio.getById(exclusao.getId());
		if (cliente != null) {
			repositorio.delete(cliente);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);
	}
}
