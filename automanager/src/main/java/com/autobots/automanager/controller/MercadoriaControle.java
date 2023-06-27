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

import com.autobots.automanager.adder.AddLinkMercadoria;
import com.autobots.automanager.updater.AtualizadorMercadoria;
import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.entity.Mercadoria;
import com.autobots.automanager.repository.RepositorioMercadoria;
import com.autobots.automanager.repository.RepositorioEmpresa;
import com.autobots.automanager.selecter.EmpresaMercadoriaSelecionador;
import com.autobots.automanager.selecter.MercadoriaSelecionador;

@RestController
@RequestMapping("/mercadoria")
public class MercadoriaControle {
	@Autowired
	private RepositorioMercadoria repositorioMercadoria;
	@Autowired
	private RepositorioEmpresa repositorioEmpresa;
	@Autowired
	private MercadoriaSelecionador selecionadorMercadoria;
	@Autowired
	private EmpresaMercadoriaSelecionador selecionadorEmpMercadoria;
	@Autowired
	private AddLinkMercadoria adicionadorLinkMercadoria;

	@GetMapping("/findOne/{id}")
	public ResponseEntity<Mercadoria> obterMercadoria(@PathVariable long id) {
		List<Mercadoria> mercadorias = repositorioMercadoria.findAll();
		Mercadoria mercadoria = selecionadorMercadoria.selecionar(mercadorias, id);
		if (mercadoria == null) {
			ResponseEntity<Mercadoria> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLinkMercadoria.adicionarLink(mercadoria);
			List<Emp> emps = repositorioEmpresa.findAll();
			for (Mercadoria mer : mercadorias) {
				Emp emp = selecionadorEmpMercadoria.selecionar(emps, mercadoria);
				adicionadorLinkMercadoria.adicionarLink(mer, emp);
			}
			ResponseEntity<Mercadoria> resposta = new ResponseEntity<Mercadoria>(mercadoria, HttpStatus.FOUND);
			return resposta;
		} 
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Mercadoria>> obterMercadorias() {
		List<Mercadoria> mercadorias = repositorioMercadoria.findAll();
		if (mercadorias.isEmpty()) {
			ResponseEntity<List<Mercadoria>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLinkMercadoria.adicionarLink(mercadorias);
			List<Emp> emps = repositorioEmpresa.findAll();
			for (Mercadoria mercadoria : mercadorias) {
				Emp emp = selecionadorEmpMercadoria.selecionar(emps, mercadoria);
				adicionadorLinkMercadoria.adicionarLink(mercadoria, emp);
			}
			ResponseEntity<List<Mercadoria>> resposta = new ResponseEntity<>(mercadorias, HttpStatus.FOUND);
			return resposta;
		}
	}

	@PostMapping("/cad/{id}")
	public ResponseEntity<?> cadMercadoria(@RequestBody Mercadoria mercadoria, @PathVariable long id) {
		HttpStatus status = HttpStatus.CONFLICT;
		if (mercadoria.getId() == null) {
			Emp emp = repositorioEmpresa.getById(id);
			emp.getMercadorias().add(mercadoria);
		    repositorioEmpresa.save(emp);
			status = HttpStatus.CREATED;
		}
		return new ResponseEntity<>(status);

	}
	
	@PutMapping("/update")
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
	public ResponseEntity<?> deleteMercadoria(@RequestBody Mercadoria exclusao, @PathVariable long id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Mercadoria mercadoria = repositorioMercadoria.getById(exclusao.getId());
		if (mercadoria != null) {
			Emp emp = repositorioEmpresa.getById(id);
			Set<Mercadoria> mercadoriasEmpresa = emp.getMercadorias();
			for (Mercadoria mercad : mercadoriasEmpresa) {
		        if (mercad.getId() == exclusao.getId()) {
		        	mercadoriasEmpresa.remove(mercad);
		            break;
		        }
		    }
		    emp.setMercadorias(mercadoriasEmpresa);
			repositorioEmpresa.save(emp);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);
		
	}
}
