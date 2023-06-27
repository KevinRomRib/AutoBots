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

import com.autobots.automanager.adder.AddLinkVenda;
import com.autobots.automanager.updater.AtualizadorVenda;
import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.entity.Venda;
import com.autobots.automanager.repository.RepositorioEmp;
import com.autobots.automanager.repository.VendaRepositorio;
import com.autobots.automanager.selecter.VendaSelecionador;

@RestController
@RequestMapping("/veiculo")
public class VendaControle {
	@Autowired
	private VendaRepositorio repositorioVenda;
	@Autowired
	private RepositorioEmp repositorioEmp;
	@Autowired
	private VendaSelecionador selecionadorVenda;
	@Autowired
	private AddLinkVenda adicionadorLinkVenda;

	@GetMapping("/findOne/{id}")
	public ResponseEntity<Venda> obterVenda(@PathVariable long id) {
		List<Venda> vendas = repositorioVenda.findAll();
		Venda venda = selecionadorVenda.selecionar(vendas, id);
		if (venda == null) {
			ResponseEntity<Venda> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLinkVenda.adicionarLink(venda);
			ResponseEntity<Venda> resposta = new ResponseEntity<Venda>(venda, HttpStatus.FOUND);
			return resposta;
		} 
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Venda>> obterVendas() {
		List<Venda> vendas = repositorioVenda.findAll();
		if (vendas.isEmpty()) {
			ResponseEntity<List<Venda>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLinkVenda.adicionarLink(vendas);
			ResponseEntity<List<Venda>> resposta = new ResponseEntity<>(vendas, HttpStatus.FOUND);
			return resposta;
		}
	}

	@PostMapping("/cad/{id}")
	public ResponseEntity<?> cadVenda(@RequestBody Venda venda, @PathVariable long id) {
		HttpStatus status = HttpStatus.CONFLICT;
		if (venda.getId() == null) {
			Emp emp = repositorioEmp.getById(id);
			emp.getVendas().add(venda);
		    repositorioEmp.save(emp);
			status = HttpStatus.CREATED;
		}
		return new ResponseEntity<>(status);

	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateVenda(@RequestBody Venda atualizacao) {
		HttpStatus status = HttpStatus.CONFLICT;
		Venda venda = repositorioVenda.getById(atualizacao.getId());
		if (venda != null) {
			AtualizadorVenda atualizador = new AtualizadorVenda();
			atualizador.atualizar(venda, atualizacao);
			repositorioVenda.save(venda);
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(status);
		
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteVenda(@RequestBody Venda exclusao, @PathVariable long id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Venda venda = repositorioVenda.getById(exclusao.getId());
		if (venda != null) {
			Emp emp = repositorioEmp.getById(id);
			Set<Venda> vendasEmpresa = emp.getVendas();
			for (Venda vend : vendasEmpresa) {
		        if (vend.getId() == exclusao.getId()) {
		        	vendasEmpresa.remove(vend);
		            break;
		        }
		    }
		    emp.setVendas(vendasEmpresa);
			repositorioEmp.save(emp);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);
		
	}
}
