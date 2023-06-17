package com.autobots.automanager.controller;

import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.entity.Mercadoria;
import com.autobots.automanager.entity.User;
import com.autobots.automanager.entity.Venda;
import com.autobots.automanager.model.AddLinkMercadoria;
import com.autobots.automanager.service.ServiceEmp;
import com.autobots.automanager.service.ServiceMercadoria;
import com.autobots.automanager.service.ServiceUser;
import com.autobots.automanager.service.ServiceVenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mercadoria")
public class MercadoriaControle {
	@Autowired
	private ServiceMercadoria serviceMercadoria;
	
	@Autowired
	private ServiceUser serviceUser;
	
	@Autowired
	private ServiceVenda serviceVenda;
	
	@Autowired
	ServiceEmp serviceEmp;
	
	@Autowired
	private AddLinkMercadoria adicionadorLinkMercadoria;
	
	@PostMapping("/cad/{id}")
	public ResponseEntity<?> cad(@RequestBody Mercadoria mercadoria, @PathVariable Long id){
		
		Long idMercadoria = serviceMercadoria.create(mercadoria);
		Mercadoria mercadoriaNova = serviceMercadoria.findById(idMercadoria);
		
		User user = serviceUser.findById(id);
		
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		for(Emp emp : serviceEmp.findAll()) {
			for (User usuarios : serviceUser.findAll()) {
				if(usuarios.getId().equals(user.getId())) {
					emp.getMercadorias().add(mercadoriaNova);
					serviceEmp.create(emp);
				}
			}
		}
		
		user.getMercadorias().add(mercadoriaNova);
		serviceUser.create(user);
		return new ResponseEntity<> (HttpStatus.CREATED);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Mercadoria>> buscarTodos(){
		List<Mercadoria> mercadoria = serviceMercadoria.findAll();
		if(mercadoria.isEmpty()) {
			return new ResponseEntity<List<Mercadoria>>(HttpStatus.NOT_FOUND);
		}
		adicionadorLinkMercadoria.adicionarLink(mercadoria);
		return new ResponseEntity<List<Mercadoria>>(mercadoria, HttpStatus.FOUND);
	}
	
	@GetMapping("/findOne/{id}")
	public ResponseEntity<Mercadoria> buscarPorId(@PathVariable Long id){
		Mercadoria mercadoria = serviceMercadoria.findById(id);
		HttpStatus status = HttpStatus.CONFLICT;
		if(mercadoria == null) {
			status = HttpStatus.NOT_FOUND;	
		}else{
			adicionadorLinkMercadoria.adicionarLink(mercadoria);
			status = HttpStatus.FOUND;
		}
		return new ResponseEntity<Mercadoria>(mercadoria, status);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateMercadoria(@RequestBody Mercadoria mercadoria, @PathVariable Long id){
		Mercadoria mercadoriaExistente  = serviceMercadoria.findById(id);
		if (mercadoriaExistente == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		mercadoria.setId(id);
		serviceMercadoria.update(mercadoria);
		
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Mercadoria mercadoriaSelecionada = serviceMercadoria.findById(id);
	
	    if(mercadoriaSelecionada == null) {
	    	return new ResponseEntity<>(HttpStatus.FOUND);
	    }
	    
	    List<User> users = serviceUser.findAll();
	    List<Emp> emps = serviceEmp.findAll();
	    List<Venda> vendas = serviceVenda.findAll();
	    
	    for(User user : users) {
	    	for(Mercadoria mercadoria : user.getMercadorias()) {
	    		if(mercadoria.getId() == id) {
	    			user.getMercadorias().remove(mercadoria);
					serviceUser.create(user);
	    		}
	    	}
	    }
	    
	    for(Emp emp : emps) {
	    	for(Mercadoria mercadoria : emp.getMercadorias()) {
	    		if(mercadoria.getId() == id) {
	    			emp.getMercadorias().remove(mercadoria);
	    			serviceEmp.create(emp);
	    		}
	    	}
	    }
	    
	    for(Venda venda : vendas) {
	    	for(Mercadoria mercadoria : venda.getMercadorias()) {
	    		if(mercadoria.getId() == id) {
	    			venda.getMercadorias().remove(mercadoria);
	    			serviceVenda.create(venda);
	    		}
	    	}
	    }

	    serviceMercadoria.delete(mercadoriaSelecionada);
	    return new ResponseEntity<>(HttpStatus.OK);

	}
	
}
