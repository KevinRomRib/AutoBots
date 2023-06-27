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

import com.autobots.automanager.adder.AddLinkDoc;
import com.autobots.automanager.updater.AtualizadorDoc;
import com.autobots.automanager.entity.Doc;
import com.autobots.automanager.entity.User;
import com.autobots.automanager.repository.RepositorioDoc;
import com.autobots.automanager.repository.UsuarioRepositorio;
import com.autobots.automanager.selecter.SelecionadorDoc;

@RestController
@RequestMapping("/doc")
public class DocControle {
	@Autowired
	private RepositorioDoc repositorioDocumento;
	@Autowired
	private UsuarioRepositorio repositorioUsuario;
	@Autowired
	private SelecionadorDoc selecionadorDocumento;
	@Autowired
	private AddLinkDoc adicionadorLinkDocumento;

	@GetMapping("/findOne/{id}")
	public ResponseEntity<Doc> obterDoc(@PathVariable long id) {
		List<Doc> docs = repositorioDocumento.findAll();
		Doc doc = selecionadorDocumento.selecionar(docs, id);
		if (doc == null) {
			ResponseEntity<Doc> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLinkDocumento.adicionarLink(doc);
			ResponseEntity<Doc> resposta = new ResponseEntity<Doc>(doc, HttpStatus.FOUND);
			return resposta;
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Doc>> obterDoc() {
		List<Doc> docs = repositorioDocumento.findAll();
		if (docs.isEmpty()) {
			ResponseEntity<List<Doc>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLinkDocumento.adicionarLink(docs);
			ResponseEntity<List<Doc>> resposta = new ResponseEntity<>(docs, HttpStatus.FOUND);
			return resposta;
		}
	}

	@PostMapping("/cad/{id}")
	public ResponseEntity<?> cadDoc(@RequestBody Doc doc, @PathVariable long id) {
		HttpStatus status = HttpStatus.CONFLICT;
		if (doc.getId() == null) {
			User user = repositorioUsuario.getById(id);
			user.getDocs().add(doc);
			repositorioUsuario.save(user);
			status = HttpStatus.CREATED;
		}
		return new ResponseEntity<>(status);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateDoc(@RequestBody Doc atualizacao) {
		HttpStatus status = HttpStatus.CONFLICT;
		Doc doc = repositorioDocumento.getById(atualizacao.getId());
		if (doc != null) {
			AtualizadorDoc atualizador = new AtualizadorDoc();
			atualizador.atualizar(doc, atualizacao);
			repositorioDocumento.save(doc);
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(status);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteDoc(@RequestBody Doc exclusao, @PathVariable long id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Doc documento = repositorioDocumento.getById(exclusao.getId());
		if (documento != null) {
			User user = repositorioUsuario.getById(id);
			Set<Doc> documentosUsuario = user.getDocs();
			for (Doc doc : documentosUsuario) {
		        if (doc.getId() == exclusao.getId()) {
		            documentosUsuario.remove(doc);
		            break;
		        }
		    }
		    user.setDocs(documentosUsuario);
			repositorioUsuario.save(user);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);
		
	}
}
