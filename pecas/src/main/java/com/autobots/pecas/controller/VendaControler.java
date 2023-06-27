package com.autobots.pecas.controller;


import com.autobots.pecas.entity.Venda;
import com.autobots.pecas.model.AddLinkVenda;
import com.autobots.pecas.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/venda")

public class VendaControler {

	@Autowired
	private VendaService vendaService;

	@Autowired
	private AddLinkVenda linkVenda;

	
	@GetMapping("/findAll")
	public ResponseEntity<List<Venda>> buscarTodos(){
		List<Venda> venda = vendaService.findAll();
		linkVenda.adicionarLink(venda);
		return new ResponseEntity<List<Venda>>(venda, HttpStatus.FOUND);
	}
	
	@GetMapping("/findOne/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_GERENTE') or hasAnyAuthority('ROLE_ADMIN') or hasAnyAuthority('ROLE_VENDEDOR') or hasAnyAuthority('ROLE_CLIENTE')")
	public ResponseEntity<Venda> buscarPorId(@PathVariable Long id){
		Venda venda = vendaService.findById(id);
		HttpStatus status = HttpStatus.CONFLICT;
		if(venda == null) {
			status = HttpStatus.NOT_FOUND;	
		}else{
			linkVenda.adicionarLink(venda);
			status = HttpStatus.FOUND;
		}
		return new ResponseEntity<Venda>(venda, status);
	}


	
	
}
