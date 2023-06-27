package com.autobots.automanager.controller;

import java.util.List;
import java.util.Set;

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

import com.autobots.automanager.adder.AddLinkEmail;
import com.autobots.automanager.updater.AtualizadorEmail;
import com.autobots.automanager.entity.Email;
import com.autobots.automanager.entity.User;
import com.autobots.automanager.repository.RepositorioEmail;
import com.autobots.automanager.repository.UsuarioRepositorio;
import com.autobots.automanager.selecter.EmailSelecionador;

@RestController
@RequestMapping("/email")
public class EmailControle {
	@Autowired
	private RepositorioEmail repositorioEmail;
	@Autowired
	private UsuarioRepositorio repositorioUsuario;
	@Autowired
	private EmailSelecionador selecionadorEmail;
	@Autowired
	private AddLinkEmail adicionadorLinkEmail;

	@GetMapping("/findOne/{id}")
	public ResponseEntity<Email> obterEmail(@PathVariable long id) {
		List<Email> emails = repositorioEmail.findAll();
		Email email = selecionadorEmail.selecionar(emails, id);
		if (email == null) {
			ResponseEntity<Email> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLinkEmail.adicionarLink(email);
			ResponseEntity<Email> resposta = new ResponseEntity<Email>(email, HttpStatus.FOUND);
			return resposta;
		} 
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Email>> obterEmails() {
		List<Email> emails = repositorioEmail.findAll();
		if (emails.isEmpty()) {
			ResponseEntity<List<Email>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLinkEmail.adicionarLink(emails);
			ResponseEntity<List<Email>> resposta = new ResponseEntity<>(emails, HttpStatus.FOUND);
			return resposta;
		}
	}

	@PostMapping("/cad/{id}")
	public ResponseEntity<?> cadEmail(@RequestBody Email email, @PathVariable long id) {
		HttpStatus status = HttpStatus.CONFLICT;
		if (email.getId() == null) {
			User user = repositorioUsuario.getById(id);
			user.getEmails().add(email);
		    repositorioUsuario.save(user);
			status = HttpStatus.CREATED;
		}
		return new ResponseEntity<>(status);

	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateEmail(@RequestBody Email atualizacao) {
		HttpStatus status = HttpStatus.CONFLICT;
		Email email = repositorioEmail.getById(atualizacao.getId());
		if (email != null) {
			AtualizadorEmail atualizador = new AtualizadorEmail();
			atualizador.atualizar(email, atualizacao);
			repositorioEmail.save(email);
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(status);
		
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmail(@RequestBody Email exclusao, @PathVariable long id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Email email = repositorioEmail.getById(exclusao.getId());
		if (email != null) {
			User user = repositorioUsuario.getById(id);
			Set<Email> emailsUsuario = user.getEmails();
			for (Email e : emailsUsuario) {
		        if (e.getId() == exclusao.getId()) {
		            emailsUsuario.remove(e);
		            break;
		        }
		    }
		    user.setEmails(emailsUsuario);
			repositorioUsuario.save(user);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);
		
	}
}
