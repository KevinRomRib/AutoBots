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
import com.autobots.automanager.entity.End;
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

	@GetMapping("/findOne/{id}")
	public End obterEnd(@PathVariable long id) {
		List<End> ends = repositorioEndereco.findAll();
		return selecionadorEndereco.selecionar(ends, id);
	}

	@GetMapping("/findAll")
	public List<End> obterEnd() {
		List<End> ends = repositorioEndereco.findAll();
		return ends;
	}

	@PostMapping("/cad/{id}")
	public void cadEnd(@RequestBody End end, @PathVariable long id) {
		 Client client = repositorioCliente.getById(id);
		 client.setEnd(end);
		 repositorioCliente.save(client);
	}

	@PutMapping("/update")
	public void updateEnd(@RequestBody End atualizacao) {
		End end = repositorioEndereco.getById(atualizacao.getId());
		AtualizadorEnd atualizador = new AtualizadorEnd();
		atualizador.atualizar(end, atualizacao);
		repositorioEndereco.save(end);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteEnd(@PathVariable long id) {
		Client client = repositorioCliente.getById(id);
	    client.setEnd(null);
	    repositorioCliente.save(client);
		
	}
}
