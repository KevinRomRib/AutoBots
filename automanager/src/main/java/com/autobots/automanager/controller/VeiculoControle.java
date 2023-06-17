package com.autobots.automanager.controller;

import com.autobots.automanager.entity.User;
import com.autobots.automanager.entity.Veiculo;
import com.autobots.automanager.entity.Venda;
import com.autobots.automanager.model.AddLinkVeiculo;
import com.autobots.automanager.service.ServiceUser;
import com.autobots.automanager.service.ServiceVeiculo;
import com.autobots.automanager.service.ServiceVenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculo")
public class VeiculoControle {
	
	@Autowired
	private ServiceVeiculo serviceVeiculo;
	
	@Autowired
	private ServiceVenda serviceVenda;

	@Autowired
	private ServiceUser serviceUser;
	
	@Autowired
	private AddLinkVeiculo adicionadorLinkVeiculo;
	
	@PostMapping("cad/{id}")
	public ResponseEntity<?> cad(@RequestBody Veiculo veiculo, @PathVariable Long id){
		User user = serviceUser.findById(id);
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			user.getVeiculos().add(veiculo);
			veiculo.setProprietario(user);
			serviceVeiculo.create(veiculo);
			serviceUser.create(user);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Veiculo>> buscarTodos(){
		List<Veiculo> veiculo = serviceVeiculo.findAll();
		adicionadorLinkVeiculo.adicionarLink(veiculo);
		return new ResponseEntity<List<Veiculo>>(veiculo, HttpStatus.FOUND);
	}
	
	@GetMapping("/findOne/{id}")
	public ResponseEntity<Veiculo> buscarPorId(@PathVariable Long id){
		Veiculo veiculo = serviceVeiculo.findById(id);
		HttpStatus status = HttpStatus.CONFLICT;
		if(veiculo == null) {
			status = HttpStatus.NOT_FOUND;	
		}else{
			adicionadorLinkVeiculo.adicionarLink(veiculo);
			status = HttpStatus.FOUND;
		}
		return new ResponseEntity<Veiculo>(veiculo, status);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateVeiculo(@RequestBody Veiculo veiculo, @PathVariable Long id){
		Veiculo vec = serviceVeiculo.findById(id);
		if (vec == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		veiculo.setId(id);
		serviceVeiculo.update(veiculo);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		Veiculo veiculoSelecionado = serviceVeiculo.findById(id);
		if(veiculoSelecionado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<User> users = serviceUser.findAll();
		List<Venda> vendas = serviceVenda.findAll();
		
		for(User user : users) {
			for(Veiculo veiculo: user.getVeiculos()) {
				if(veiculo.getId() == id) {
					user.getVeiculos().remove(veiculo);
					serviceUser.create(user);
				}
			}
		}
		
		for(Venda venda : vendas) {
				if(venda.getVeiculo().getId() == id) {
					venda.setVeiculo(null);
					serviceVenda.create(venda);
				}
			}

		serviceVeiculo.delete(veiculoSelecionado);
		return new ResponseEntity<>(HttpStatus.OK);
		
		}
		
	
}

