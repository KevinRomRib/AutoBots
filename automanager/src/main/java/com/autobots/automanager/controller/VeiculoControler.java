package com.autobots.automanager.controller;

import com.autobots.automanager.entity.Usuario;
import com.autobots.automanager.entity.Veiculo;
import com.autobots.automanager.entity.Venda;
import com.autobots.automanager.model.AdicionadorLinkVeiculo;
import com.autobots.automanager.service.UsuarioService;
import com.autobots.automanager.service.VeiculoService;
import com.autobots.automanager.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculo")
public class VeiculoControler {
	
	@Autowired
	private VeiculoService veiculoService;
	
	@Autowired
	private VendaService vendaService;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private AdicionadorLinkVeiculo adicionadorLinkVeiculo;
	
	@PostMapping("cad/{id}")
	public ResponseEntity<?> cad(@RequestBody Veiculo veiculo, @PathVariable Long id){
		Usuario usuario = usuarioService.findById(id);
		if(usuario == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			usuario.getVeiculos().add(veiculo);
			veiculo.setProprietario(usuario);
			veiculoService.create(veiculo);
			usuarioService.create(usuario);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Veiculo>> buscarTodos(){
		List<Veiculo> veiculo = veiculoService.findAll();
		adicionadorLinkVeiculo.adicionarLink(veiculo);
		return new ResponseEntity<List<Veiculo>>(veiculo, HttpStatus.FOUND);
	}
	
	@GetMapping("/findOne/{id}")
	public ResponseEntity<Veiculo> buscarPorId(@PathVariable Long id){
		Veiculo veiculo = veiculoService.findById(id);
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
		Veiculo vec = veiculoService.findById(id);
		if (vec == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		veiculo.setId(id);
		veiculoService.update(veiculo);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Veiculo veiculoSelecionado = veiculoService.findById(id);
		if(veiculoSelecionado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<Usuario> usuarios = usuarioService.findAll();
		List<Venda> vendas = vendaService.findAll();
		
		for(Usuario usuario : usuarios) {
			for(Veiculo veiculo: usuario.getVeiculos()) {
				if(veiculo.getId() == id) {
					usuario.getVeiculos().remove(veiculo);
					usuarioService.create(usuario);
				}
			}
		}
		
		for(Venda venda : vendas) {
				if(venda.getVeiculo().getId() == id) {
					venda.setVeiculo(null);
					vendaService.create(venda);
				}
			}

		veiculoService.delete(veiculoSelecionado);
		return new ResponseEntity<>(HttpStatus.OK);
		
		}
		
	
}

