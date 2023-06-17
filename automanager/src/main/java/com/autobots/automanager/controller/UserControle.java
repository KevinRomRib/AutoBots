package com.autobots.automanager.controller;


import com.autobots.automanager.entity.*;
import com.autobots.automanager.model.AddLinkUser;
import com.autobots.automanager.service.ServiceEmp;
import com.autobots.automanager.service.ServiceUser;
import com.autobots.automanager.service.ServiceVeiculo;
import com.autobots.automanager.service.ServiceVenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserControle {

	@Autowired
	private ServiceUser serviceUser;
	
	@Autowired
	private ServiceEmp serviceEmp;
	
	@Autowired
	private ServiceVenda serviceVenda;
	
	@Autowired
	private ServiceVeiculo serviceVeiculo;
	
	@Autowired
	private AddLinkUser adicionadorLinkUsuario;
	
	@PostMapping("/cadastrar/{id}")
	public ResponseEntity<?> cadastrar(@RequestBody User user, @PathVariable Long id){
		Emp empSelecionada = serviceEmp.findById(id);
		
		if(empSelecionada != null) {
	        if(user.getPerfis().toString().contains("FORNECEDOR")) {
	        	if(user.getMercadorias().size() > 0)
	        	empSelecionada.getMercadorias().addAll(user.getMercadorias());
	        }	
	        
	        serviceUser.create(user);
	        
	        empSelecionada.getUsers().add(user);
	        serviceEmp.create(empSelecionada);

	        return new ResponseEntity<> (HttpStatus.CREATED);
	        
		}
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        	
	}
	
	
	@GetMapping("/buscar")
	public ResponseEntity<List<User>> pegarTodos(){
		List<User> user = serviceUser.findAll();
		adicionadorLinkUsuario.adicionarLink(user);
		return new ResponseEntity<List<User>>(user, HttpStatus.FOUND);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<User> pegarUm(@PathVariable Long id){
		User user = serviceUser.findById(id);
		HttpStatus status = HttpStatus.CONFLICT;
		if(user == null) {
			status = HttpStatus.NOT_FOUND;	
		}else{
			adicionadorLinkUsuario.adicionarLink(user);
			status = HttpStatus.FOUND;
		}
		return new ResponseEntity<User>(user, status);
	}
	
	@PutMapping("/cadastrar-credencial/{id}")
	public ResponseEntity<?> cadastroCredencial(@PathVariable Long id, @RequestBody LoginDeUsuario credencialUsuario){
		User user = serviceUser.findById(id);
		if(user == null) {
			  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		user.getCredenciais().add(credencialUsuario);
		serviceUser.create(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		User userSelecionado = serviceUser.findById(id);
		if (userSelecionado == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		for (Emp emp : serviceEmp.findAll()) {
			for(User funcionario : emp.getUsers()) {
				if(funcionario.getId() == userSelecionado.getId()) {
					emp.getUsers().remove(funcionario);
				}
			}
		}
		
		 for(Venda venda : serviceVenda.findAll()) {
			 if(venda.getFuncionario().getId() == userSelecionado.getId()) {
				 venda.setFuncionario(null);
			 }
			 if(venda.getCliente().getId() == userSelecionado.getId()) {
				 venda.setCliente(null);
			 }
		 }
		
		for (Veiculo veiculo : serviceVeiculo.findAll()) {
			if(veiculo.getProprietario().getId() == userSelecionado.getId()) {
				veiculo.setProprietario(null);
			}
		}
		
		userSelecionado.getDocs().removeAll(userSelecionado.getDocs());
		userSelecionado.getTels().removeAll(userSelecionado.getTels());
		userSelecionado.getEmails().removeAll(userSelecionado.getEmails());
		userSelecionado.getCredenciais().removeAll(userSelecionado.getCredenciais());
		userSelecionado.getMercadorias().removeAll(userSelecionado.getMercadorias());
		userSelecionado.getVeiculos().removeAll(userSelecionado.getVeiculos());
		userSelecionado.getVendas().removeAll(userSelecionado.getVendas());
		userSelecionado.setEnd(null);
		
		
		serviceUser.delete(userSelecionado);
		
		return new ResponseEntity<>(HttpStatus.OK);
			
		}

}
