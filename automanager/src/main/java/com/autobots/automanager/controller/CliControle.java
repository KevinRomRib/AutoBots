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
import com.autobots.automanager.model.AtualizadorCliente;
import com.autobots.automanager.model.ClientSelect;
import com.autobots.automanager.repositorios.RepositorioCli;

@RestController
@RequestMapping("/client")
public class CliControle {
	@Autowired
	private RepositorioCli repositorio;
	@Autowired
	private ClientSelect selecionador;

	@GetMapping("/findOne/{id}")
	public Client obterCli(@PathVariable long id) {
		List<Client> clients = repositorio.findAll();
		return selecionador.selecionar(clients, id);
	}

	@GetMapping("/findAll")
	public List<Client> obterCli() {
		List<Client> clients = repositorio.findAll();
		return clients;
	}

	@PostMapping("/cad")
	public void cadCli(@RequestBody Client client) {
		repositorio.save(client);
	}

	@PutMapping("/update")
	public void updateCli(@RequestBody Client atualizacao) {
		Client client = repositorio.getById(atualizacao.getId());
		AtualizadorCliente atualizador = new AtualizadorCliente();
		atualizador.atualizar(client, atualizacao);
		repositorio.save(client);
	}

	@DeleteMapping("/delete")
	public void deleteCli(@RequestBody Client exclusao) {
		Client client = repositorio.getById(exclusao.getId());
		repositorio.delete(client);
	}
}
