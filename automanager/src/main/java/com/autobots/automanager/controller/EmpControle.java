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

import com.autobots.automanager.adder.AddLinkEmp;
import com.autobots.automanager.updater.AtualizadorEmp;
import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.repository.RepositorioEmp;
import com.autobots.automanager.selecter.SelecionadorEmp;

@RestController
@RequestMapping("/emp")
public class EmpControle {
	@Autowired
	private SelecionadorEmp selecionadorEmpresa;
	@Autowired
	private RepositorioEmp repositorioEmp;
	@Autowired
	private AddLinkEmp adicionadorLinkEmpresa;

	@GetMapping("/findOne/{id}")
	public ResponseEntity<Emp> obterEmp(@PathVariable long id) {
		List<Emp> emps = repositorioEmp.findAll();
		Emp emp = selecionadorEmpresa.selecionar(emps, id);
		if (emp == null) {
			ResponseEntity<Emp> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLinkEmpresa.adicionarLink(emp);
			ResponseEntity<Emp> resposta = new ResponseEntity<Emp>(emp, HttpStatus.FOUND);
			return resposta;
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Emp>> obterEmp() {
		List<Emp> emps = repositorioEmp.findAll();
		if (emps.isEmpty()) {
			ResponseEntity<List<Emp>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLinkEmpresa.adicionarLink(emps);
			ResponseEntity<List<Emp>> resposta = new ResponseEntity<>(emps, HttpStatus.FOUND);
			return resposta;
		}
	}

	@PostMapping("/cad")
	public ResponseEntity<?> cadEmp(@RequestBody Emp emp) {
		HttpStatus status = HttpStatus.CONFLICT;
		if (emp.getId() == null) {
			repositorioEmp.save(emp);
			status = HttpStatus.CREATED;
		}
		return new ResponseEntity<>(status);

	}

	@PutMapping("/update")
	public ResponseEntity<?> updateEmp(@RequestBody Emp atualizacao) {
		HttpStatus status = HttpStatus.CONFLICT;
		Emp emp = repositorioEmp.getById(atualizacao.getId());
		if (emp != null) {
			AtualizadorEmp atualizador = new AtualizadorEmp();
			atualizador.atualizar(emp, atualizacao);
			repositorioEmp.save(emp);
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(status);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteEmp(@RequestBody Emp exclusao) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Emp emp = repositorioEmp.getById(exclusao.getId());
		if (emp != null) {
			repositorioEmp.delete(emp);
			status = HttpStatus.OK;
		}
		
		return new ResponseEntity<>(status);
	}
}
