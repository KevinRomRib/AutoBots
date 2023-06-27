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

import com.autobots.automanager.adder.AddLinkTel;
import com.autobots.automanager.updater.AtualizadorTel;
import com.autobots.automanager.entity.Tel;
import com.autobots.automanager.entity.User;
import com.autobots.automanager.repository.RepositorioTel;
import com.autobots.automanager.repository.UsuarioRepositorio;
import com.autobots.automanager.selecter.SelecionadorTel;

@RestController
@RequestMapping("/tel")
public class TelControle {
	@Autowired
	private RepositorioTel repositorioTelefone;
	@Autowired
	private UsuarioRepositorio repositorioUsuario;
	@Autowired
	private SelecionadorTel selecionadorTelefone;
	@Autowired
	private AddLinkTel adicionadorLinkTelefone;

	@GetMapping("/findOne/{id}")
	public ResponseEntity<Tel> obterTel(@PathVariable long id) {
		List<Tel> tels = repositorioTelefone.findAll();
		Tel tel = selecionadorTelefone.selecionar(tels, id);
		if (tel == null) {
			ResponseEntity<Tel> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLinkTelefone.adicionarLink(tel);
			ResponseEntity<Tel> resposta = new ResponseEntity<Tel>(tel, HttpStatus.FOUND);
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
			adicionadorLinkTelefone.adicionarLink(tels);
			ResponseEntity<List<Tel>> resposta = new ResponseEntity<>(tels, HttpStatus.FOUND);
			return resposta;
		}
	}

	@PostMapping("/cad/{id}")
	public ResponseEntity<?> cadTel(@RequestBody Tel tel, @PathVariable long id) {
		HttpStatus status = HttpStatus.CONFLICT;
		if (tel.getId() == null) {
			User user = repositorioUsuario.getById(id);
			user.getTels().add(tel);
		    repositorioUsuario.save(user);
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
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(status);
		
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteTel(@RequestBody Tel exclusao, @PathVariable long id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Tel telefone = repositorioTelefone.getById(exclusao.getId());
		if (telefone != null) {
			User user = repositorioUsuario.getById(id);
			Set<Tel> telefonesUsuario = user.getTels();
			for (Tel tel : telefonesUsuario) {
		        if (tel.getId() == exclusao.getId()) {
		            telefonesUsuario.remove(tel);
		            break;
		        }
		    }
		    user.setTels(telefonesUsuario);
			repositorioUsuario.save(user);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);
		
	}
}
