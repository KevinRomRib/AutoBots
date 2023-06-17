package com.autobots.automanager.controller;

import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.entity.Venda;
import com.autobots.automanager.service.ServiceEmp;
import com.autobots.automanager.service.Service;
import com.autobots.automanager.service.ServiceVenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceControle {

	@Autowired
	private Service service;

	@Autowired
	private ServiceVenda serviceVenda;

	@Autowired
	ServiceEmp serviceEmp;
	
	@Autowired
	private com.autobots.automanager.link.AddLinkService adicionadorLinkServico;
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@RequestBody com.autobots.automanager.entity.Service service, @PathVariable Long id){
		com.autobots.automanager.entity.Service serv = this.service.findById(id);
		if(serv == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		service.setId(id);
		this.service.update(service);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/cad/{id}")
	public ResponseEntity<?> cad(@RequestBody com.autobots.automanager.entity.Service service, @PathVariable Long id ){
	Emp emp = serviceEmp.findById(id);
	if (emp == null) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	emp.getServices().add(service);
	serviceEmp.create(emp);
	return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<com.autobots.automanager.entity.Service>> buscarTodos(){
		List<com.autobots.automanager.entity.Service> services = service.findAll();
		adicionadorLinkServico.adicionarLink(services);
		return new ResponseEntity<List<com.autobots.automanager.entity.Service>>(services, HttpStatus.FOUND);
	}
	
	@GetMapping("/findOne/{id}")
	public ResponseEntity<com.autobots.automanager.entity.Service> buscarPorId(@PathVariable Long id){
		com.autobots.automanager.entity.Service service = this.service.findById(id);
		HttpStatus status = HttpStatus.CONFLICT;
		if(service == null) {
			status = HttpStatus.NOT_FOUND;
		}else{
			adicionadorLinkServico.adicionarLink(service);
			status = HttpStatus.FOUND;
		}
		return new ResponseEntity<com.autobots.automanager.entity.Service>(service, status);
	}	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		com.autobots.automanager.entity.Service serviceSelecionado = service.findById(id);
		
		if(serviceSelecionado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<Emp> emps = serviceEmp.findAll();
	    List<Venda> vendas = serviceVenda.findAll();
	    
	    for (Emp emp : emps) {
	        for (com.autobots.automanager.entity.Service service : emp.getServices()) {
	          if (service.getId().equals(id)) {
	        	  emp.getServices().remove(service);
	          }
	        }
	      }
	    
	      for (Venda venda : vendas) {
	        for (com.autobots.automanager.entity.Service service : venda.getServices()) {
	          if (service.getId().equals(id)) {
	        	  venda.getServices().remove(service);
	          }
	        }
	      }
	
	    service.delete(serviceSelecionado);
		return new ResponseEntity<>(HttpStatus.OK);
	    
	}
	
}
