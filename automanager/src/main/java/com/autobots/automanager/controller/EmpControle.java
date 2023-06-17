package com.autobots.automanager.controller;

import java.util.Date;
import java.util.List;

import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.model.AddLinkEmp;
import com.autobots.automanager.service.ServiceEmp;
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



@RestController
@RequestMapping("/emp")
public class EmpControle {
	
	@Autowired
	private ServiceEmp serviceEmp;
	
	@Autowired
	private AddLinkEmp empresaLink;

	@PostMapping("/cad")
	public ResponseEntity<?> cadEmp(@RequestBody Emp emp){
		emp.setCadastro(new Date());
		serviceEmp.create(emp);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/findOne")
	public ResponseEntity<List<Emp>> buscarEmp(){
		List<Emp> todos = serviceEmp.findAll();
		HttpStatus status = HttpStatus.CONFLICT;
		if(todos.isEmpty()) {
			status = HttpStatus.NOT_FOUND;
			return new ResponseEntity<List<Emp>>(status);
		}else {
			status = HttpStatus.FOUND;
			ResponseEntity<List<Emp>> resposta = new ResponseEntity<List<Emp>>(todos, status);
			empresaLink.adicionarLink(todos);
			return resposta;
		}
	}

	@GetMapping("/findOne/{id}")
	public ResponseEntity<Emp> buscarEmpPorId(@PathVariable Long id){
		Emp emp = serviceEmp.findById(id);
		if(emp == null) {
			return new ResponseEntity<Emp>(HttpStatus.NOT_FOUND);
		}else {
			empresaLink.adicionarLink(emp);
			return new ResponseEntity<Emp>(emp, HttpStatus.FOUND);
		}
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateEmp(@PathVariable Long id, @RequestBody Emp emp){
		Emp empExistente = serviceEmp.findById(id);
		if(empExistente == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		emp.setId(id);
		serviceEmp.update(empExistente);
		serviceEmp.create(emp);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmp(@PathVariable Long id){
		Emp empSelecionada = serviceEmp.findById(id);
		if(empSelecionada == null) {
			return new ResponseEntity<Emp>(HttpStatus.NOT_FOUND);
		}
		serviceEmp.delete(empSelecionada);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}