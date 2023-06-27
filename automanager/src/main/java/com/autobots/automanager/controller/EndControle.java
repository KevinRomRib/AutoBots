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
import com.autobots.automanager.updater.AtualizadorEnd;
import com.autobots.automanager.entity.End;
import com.autobots.automanager.entity.User;
import com.autobots.automanager.repository.RepositorioEndereco;
import com.autobots.automanager.repository.RepositorioUsuario;
import com.autobots.automanager.selecter.SelecionadorEnd;
import com.autobots.automanager.selecter.UsuarioEnderecoSelecionador;

@RestController
@RequestMapping("/end")
public class EndControle {
	@Autowired
	private RepositorioEndereco repositorioEndereco;
	@Autowired
	private RepositorioUsuario repositorioUsuario;
	@Autowired
	private SelecionadorEnd selecionadorEndereco;
	@Autowired
	private UsuarioEnderecoSelecionador selecionadorUsuEndereco;
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
			adicionadorLinkEndereco.adicionarLink(end);
			List<User> users = repositorioUsuario.findAll();
			User user = selecionadorUsuEndereco.selecionar(users, end);
			adicionadorLinkEndereco.adicionarLink(end, user);
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
			adicionadorLinkEndereco.adicionarLink(ends);
			ResponseEntity<List<End>> resposta = new ResponseEntity<>(ends, HttpStatus.FOUND);
			return resposta;
		}
		
	}

	@PostMapping("/cad/{id}")
	public ResponseEntity<?> cadEnd(@RequestBody End end, @PathVariable long id) {
		HttpStatus status = HttpStatus.CONFLICT;
		if (end.getId() == null) {
			User user = repositorioUsuario.getById(id);
			user.setEnd(end);
			repositorioUsuario.save(user);
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
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(status); 
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEnd(@PathVariable long id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		User user = repositorioUsuario.getById(id);
		if (user.getEnd()!= null) {
		    user.setEnd(null);
		    repositorioUsuario.save(user);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);

	}
}
