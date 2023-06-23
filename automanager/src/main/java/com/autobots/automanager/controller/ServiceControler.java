package com.autobots.automanager.controller;

import com.autobots.automanager.entity.Empresa;
import com.autobots.automanager.entity.Servico;
import com.autobots.automanager.entity.Venda;
import com.autobots.automanager.service.EmpresaService;
import com.autobots.automanager.service.ServicoService;
import com.autobots.automanager.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceControler {

	@Autowired
	private ServicoService servicoService;

	@Autowired
	private VendaService vendaService;

	@Autowired
	EmpresaService empresaService;
	
	@Autowired
	private com.autobots.automanager.link.AdicionadorLinkServico adicionadorLinkServico;
	
	@PutMapping("/update/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_GERENTE') or hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> update(@RequestBody Servico servico , @PathVariable Long id){
		Servico serv = servicoService.findById(id);
		if(serv == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		servico.setId(id);
		servicoService.update(servico);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/cad/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_GERENTE') or hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> cad(@RequestBody Servico servico, @PathVariable Long id ){
	Empresa empresa = empresaService.findById(id);
	if (empresa == null) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	empresa.getServicos().add(servico);
	empresaService.create(empresa);
	return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/findAll")
	@PreAuthorize("hasAnyAuthority('ROLE_GERENTE') or hasAnyAuthority('ROLE_ADMIN') or hasAnyAuthority('ROLE_VENDEDOR')")
	public ResponseEntity<List<Servico>> buscarTodos(){
		List<Servico> servicos = servicoService.findAll();
		adicionadorLinkServico.adicionarLink(servicos);
		return new ResponseEntity<List<Servico>>(servicos, HttpStatus.FOUND);
	}
	
	@GetMapping("/findOne/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_GERENTE') or hasAnyAuthority('ROLE_ADMIN') or hasAnyAuthority('ROLE_VENDEDOR')")
	public ResponseEntity<Servico> buscarPorId(@PathVariable Long id){
		Servico servico = servicoService.findById(id);
		HttpStatus status = HttpStatus.CONFLICT;
		if(servico == null) {
			status = HttpStatus.NOT_FOUND;
		}else{
			adicionadorLinkServico.adicionarLink(servico);
			status = HttpStatus.FOUND;
		}
		return new ResponseEntity<Servico>(servico, status);
	}	
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_GERENTE') or hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Servico servicoSelecionado = servicoService.findById(id);
		
		if(servicoSelecionado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<Empresa> empresas = empresaService.findAll();
	    List<Venda> vendas = vendaService.findAll();
	    
	    for (Empresa empresa : empresas) {
	        for (Servico servico : empresa.getServicos()) {
	          if (servico.getId().equals(id)) {
	        	  empresa.getServicos().remove(servico);
	          }
	        }
	      }
	    
	      for (Venda venda : vendas) {
	        for (Servico servico : venda.getServicos()) {
	          if (servico.getId().equals(id)) {
	        	  venda.getServicos().remove(servico);
	          }
	        }
	      }
	
	    servicoService.delete(servicoSelecionado);
		return new ResponseEntity<>(HttpStatus.OK);
	    
	}
	
}
