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

import com.autobots.automanager.adder.AddLinkService;
import com.autobots.automanager.updater.AtualizadorService;
import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.entity.Service;
import com.autobots.automanager.repository.RepositorioEmpresa;
import com.autobots.automanager.repository.RepositorioServico;
import com.autobots.automanager.selecter.EmpresaServicoSelecionador;
import com.autobots.automanager.selecter.SelecionadorService;

@RestController
@RequestMapping("/service")
public class ServiceControle {
	@Autowired
	private RepositorioServico repositorioServico;
	@Autowired
	private RepositorioEmpresa repositorioEmpresa;
	@Autowired
	private SelecionadorService selecionadorServico;
	@Autowired
	private EmpresaServicoSelecionador selecionadorEmpServico;
	@Autowired
	private AddLinkService adicionadorLinkServico;

	@GetMapping("/findOne/{id}")
	public ResponseEntity<Service> obterService(@PathVariable long id) {
		List<Service> services = repositorioServico.findAll();
		Service service = selecionadorServico.selecionar(services, id);
		if (service == null) {
			ResponseEntity<Service> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLinkServico.adicionarLink(service);
			List<Emp> emps = repositorioEmpresa.findAll();
			for (Service ser : services) {
				Emp emp = selecionadorEmpServico.selecionar(emps, service);
				adicionadorLinkServico.adicionarLink(ser, emp);
			}
			ResponseEntity<Service> resposta = new ResponseEntity<Service>(service, HttpStatus.FOUND);
			return resposta;
		} 
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Service>> obterServices() {
		List<Service> services = repositorioServico.findAll();
		if (services.isEmpty()) {
			ResponseEntity<List<Service>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLinkServico.adicionarLink(services);
			List<Emp> emps = repositorioEmpresa.findAll();
			for (Service service : services) {
				Emp emp = selecionadorEmpServico.selecionar(emps, service);
				adicionadorLinkServico.adicionarLink(service, emp);
			}
			ResponseEntity<List<Service>> resposta = new ResponseEntity<>(services, HttpStatus.FOUND);
			return resposta;
		}
	}

	@PostMapping("/cad/{id}")
	public ResponseEntity<?> cadService(@RequestBody Service service, @PathVariable long id) {
		HttpStatus status = HttpStatus.CONFLICT;
		if (service.getId() == null) {
			Emp emp = repositorioEmpresa.getById(id);
			emp.getServices().add(service);
		    repositorioEmpresa.save(emp);
			status = HttpStatus.CREATED;
		}
		return new ResponseEntity<>(status);

	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateService(@RequestBody Service atualizacao) {
		HttpStatus status = HttpStatus.CONFLICT;
		Service service = repositorioServico.getById(atualizacao.getId());
		if (service != null) {
			AtualizadorService atualizador = new AtualizadorService();
			atualizador.atualizar(service, atualizacao);
			repositorioServico.save(service);
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(status);
		
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteService(@RequestBody Service exclusao, @PathVariable long id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Service service = repositorioServico.getById(exclusao.getId());
		if (service != null) {
			Emp emp = repositorioEmpresa.getById(id);
			Set<Service> servicosEmpresa = emp.getServices();
			for (Service serv : servicosEmpresa) {
		        if (serv.getId() == exclusao.getId()) {
		        	servicosEmpresa.remove(serv);
		            break;
		        }
		    }
		    emp.setServices(servicosEmpresa);
			repositorioEmpresa.save(emp);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);
		
	}
}
