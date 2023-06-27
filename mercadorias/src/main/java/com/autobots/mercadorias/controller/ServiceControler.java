package com.autobots.mercadorias.controller;


import com.autobots.mercadorias.entity.Service;
import com.autobots.mercadorias.model.AddLinkServico;
import com.autobots.mercadorias.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceControler {

	@Autowired
	private ServicoService servicoService;


	
	@Autowired
	private AddLinkServico adicionadorLinkServico;
	

	
	@GetMapping("/findAll")
	public ResponseEntity<List<Service>> buscarTodos(){
		List<Service> services = servicoService.findAll();
		adicionadorLinkServico.adicionarLink(services);
		return new ResponseEntity<List<Service>>(services, HttpStatus.FOUND);
	}

	@GetMapping("/findOne/{id}")
	public ResponseEntity<Service> buscarPorId(@PathVariable Long id){
		Service service = servicoService.findById(id);
		HttpStatus status = HttpStatus.CONFLICT;
		if(service == null) {
			status = HttpStatus.NOT_FOUND;
		}else{
			adicionadorLinkServico.adicionarLink(service);
			status = HttpStatus.FOUND;
		}
		return new ResponseEntity<Service>(service, status);
	}


	
}
