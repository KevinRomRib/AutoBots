package com.autobots.automanager.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.adder.AddLinkMercadoria;
import com.autobots.automanager.updater.AtualizadorMercadoria;
import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.entity.Mercadoria;
import com.autobots.automanager.repository.RepositorioMercadoria;
import com.autobots.automanager.repository.RepositorioEmp;
import com.autobots.automanager.selecter.MercadoriaSelecionador;

@RestController
@RequestMapping("/mercadoria")
public class MercadoriaControle {
	@Autowired
	private RepositorioMercadoria repositorioMercadoria;
	@Autowired
	private RepositorioEmp repositorioEmp;
	@Autowired
	private MercadoriaSelecionador selecionadorMercadoria;
	@Autowired
	private AddLinkMercadoria adicionadorLinkMercadoria;
	
	

	@GetMapping("/findOne/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_GERENTE', 'ROLE_VENDEDOR')")
	public ResponseEntity<Mercadoria> obterMercadoria(@PathVariable long id) {
		List<Mercadoria> mercadorias = repositorioMercadoria.findAll();
		Mercadoria mercadoria = selecionadorMercadoria.selecionar(mercadorias, id);
		if (mercadoria == null) {
			ResponseEntity<Mercadoria> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLinkMercadoria.adicionarLink(mercadoria);
			ResponseEntity<Mercadoria> resposta = new ResponseEntity<Mercadoria>(mercadoria, HttpStatus.FOUND);
			return resposta;
		} 
	}

	@GetMapping("/findAll")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_GERENTE', 'ROLE_VENDEDOR')")
	public ResponseEntity<List<Mercadoria>> obterMercadorias() {
		List<Mercadoria> mercadorias = repositorioMercadoria.findAll();
		if (mercadorias.isEmpty()) {
			ResponseEntity<List<Mercadoria>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLinkMercadoria.adicionarLink(mercadorias);
			ResponseEntity<List<Mercadoria>> resposta = new ResponseEntity<>(mercadorias, HttpStatus.FOUND);
			return resposta;
		}
	}

	@PostMapping("/cad/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_GERENTE')")
	public ResponseEntity<?> cadMercadoria(@RequestBody Mercadoria mercadoria, @PathVariable long id) {
		HttpStatus status = HttpStatus.CONFLICT;
		if (mercadoria.getId() == null) {
			Emp emp = repositorioEmp.getById(id);
			emp.getMercadorias().add(mercadoria);
		    repositorioEmp.save(emp);
			status = HttpStatus.CREATED;
		}
		return new ResponseEntity<>(status);

	}
	
	@PutMapping("/update")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_GERENTE')")
	public ResponseEntity<?> updateMercadoria(@RequestBody Mercadoria atualizacao) {
		HttpStatus status = HttpStatus.CONFLICT;
		Mercadoria mercadoria = repositorioMercadoria.getById(atualizacao.getId());
		if (mercadoria != null) {
			AtualizadorMercadoria atualizador = new AtualizadorMercadoria();
			atualizador.atualizar(mercadoria, atualizacao);
			repositorioMercadoria.save(mercadoria);
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(status);
		
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_GERENTE')")
	public ResponseEntity<?> deleteMercadoria(@RequestBody Mercadoria exclusao, @PathVariable long id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Mercadoria mercadoria = repositorioMercadoria.getById(exclusao.getId());
		if (mercadoria != null) {
			Emp emp = repositorioEmp.getById(id);
			Set<Mercadoria> mercadoriasEmpresa = emp.getMercadorias();
			for (Mercadoria mercad : mercadoriasEmpresa) {
		        if (mercad.getId() == exclusao.getId()) {
		        	mercadoriasEmpresa.remove(mercad);
		            break;
		        }
		    }
		    emp.setMercadorias(mercadoriasEmpresa);
			repositorioEmp.save(emp);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);
		
	}
}
