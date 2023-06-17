package com.autobots.automanager.controller;

import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.entity.User;
import com.autobots.automanager.entity.Veiculo;
import com.autobots.automanager.entity.Venda;
import com.autobots.automanager.model.AddLinkVenda;
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
@RequestMapping("/venda")

public class VendaControle {

	@Autowired
	private ServiceVenda serviceVenda;
	
	@Autowired
	private ServiceEmp serviceEmp;
	
	@Autowired
	private ServiceVeiculo serviceVeiculo;
	
	@Autowired
	private ServiceUser serviceUser;
	
	@Autowired
	private AddLinkVenda linkVenda;
	
	@PostMapping("/cad/{id}")
	public ResponseEntity<?> cad(@RequestBody Venda venda, @PathVariable Long id){
		Emp empSelecionada = serviceEmp.findById(id);
		if(empSelecionada == null) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		User funcionarioSelecionado = serviceUser.findById(venda.getFuncionario().getId());
		User clienteSelecionado = serviceUser.findById(venda.getCliente().getId());
		Veiculo veiculoSelecionado = serviceVeiculo.findById(venda.getVeiculo().getId());
		venda.setVeiculo(veiculoSelecionado);
		venda.setCliente(clienteSelecionado);
		venda.setFuncionario(funcionarioSelecionado);
		serviceUser.create(funcionarioSelecionado);

		empSelecionada.getVendas().add(venda);
		serviceEmp.create(empSelecionada);

		serviceVenda.create(venda);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Venda>> buscarTodos(){
		List<Venda> venda = serviceVenda.findAll();
		linkVenda.adicionarLink(venda);
		return new ResponseEntity<List<Venda>>(venda, HttpStatus.FOUND);
	}
	
	@GetMapping("/findOne/{id}")
	public ResponseEntity<Venda> buscarPorId(@PathVariable Long id){
		Venda venda = serviceVenda.findById(id);
		HttpStatus status = HttpStatus.CONFLICT;
		if(venda == null) {
			status = HttpStatus.NOT_FOUND;	
		}else{
			linkVenda.adicionarLink(venda);
			status = HttpStatus.FOUND;
		}
		return new ResponseEntity<Venda>(venda, status);
	}

	@PutMapping("/update/{id}")
		public ResponseEntity<?> updateVenda(@RequestBody Venda venda, @PathVariable Long id){
			Venda vendaExistente = serviceVenda.findById(id);
			if (vendaExistente == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			venda.setId(id);
			serviceVenda.update(venda);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
	
	
	
}
