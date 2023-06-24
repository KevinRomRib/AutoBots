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
import com.autobots.automanager.entity.Tel;
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

	@GetMapping("/tel/{id}")
	public Tel obterTel(@PathVariable long id) {
		List<Tel> tels = repositorioTelefone.findAll();
		return selecionadorTelefone.selecionar(tels, id);
	}

	@GetMapping("/findAll")
	public List<Tel> obterTel() {
		List<Tel> tels = repositorioTelefone.findAll();
		return tels;
	}

	@PostMapping("/cad/{id}")
	public void cadTel(@RequestBody Tel tel, @PathVariable long id) {
		Client client = repositorioCliente.getById(id);
		client.getTels().add(tel);
	    repositorioCliente.save(client);
	}
	
	@PutMapping("/update")
	public void updateTel(@RequestBody Tel atualizacao) {
		Tel tel = repositorioTelefone.getById(atualizacao.getId());
		AtualizadorTel atualizador = new AtualizadorTel();
		atualizador.atualizar(tel, atualizacao);
		repositorioTelefone.save(tel);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteTel(@RequestBody Tel exclusao, @PathVariable long id) {
		Client client = repositorioCliente.getById(id);
		List<Tel> telefonesCliente = client.getTels();
		for (Tel tel : telefonesCliente) {
	        if (tel.getId() == exclusao.getId()) {
	            telefonesCliente.remove(tel);
	            break;
	        }
	    }
	    client.setTels(telefonesCliente);
		repositorioCliente.save(client);
	}
}
