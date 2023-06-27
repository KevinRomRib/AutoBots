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

import com.autobots.automanager.adder.AddLinkVeiculo;
import com.autobots.automanager.updater.AtualizadorVeiculo;
import com.autobots.automanager.entity.User;
import com.autobots.automanager.entity.Veiculo;
import com.autobots.automanager.repository.UsuarioRepositorio;
import com.autobots.automanager.repository.VeiculoRepositorio;
import com.autobots.automanager.selecter.VeiculoSelecionador;

@RestController
@RequestMapping("/veiculo")
public class VeiculoControle {
	@Autowired
	private VeiculoRepositorio repositorioVeiculo;
	@Autowired
	private UsuarioRepositorio repositorioUsuario;
	@Autowired
	private VeiculoSelecionador selecionadorVeiculo;
	@Autowired
	private AddLinkVeiculo adicionadorLinkVeiculo;

	@GetMapping("/findOne/{id}")
	public ResponseEntity<Veiculo> obterVeiculo(@PathVariable long id) {
		List<Veiculo> veiculos = repositorioVeiculo.findAll();
		Veiculo veiculo = selecionadorVeiculo.selecionar(veiculos, id);
		if (veiculo == null) {
			ResponseEntity<Veiculo> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLinkVeiculo.adicionarLink(veiculo);
			ResponseEntity<Veiculo> resposta = new ResponseEntity<Veiculo>(veiculo, HttpStatus.FOUND);
			return resposta;
		} 
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Veiculo>> obterVeiculos() {
		List<Veiculo> veiculos = repositorioVeiculo.findAll();
		if (veiculos.isEmpty()) {
			ResponseEntity<List<Veiculo>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLinkVeiculo.adicionarLink(veiculos);
			ResponseEntity<List<Veiculo>> resposta = new ResponseEntity<>(veiculos, HttpStatus.FOUND);
			return resposta;
		}
	}

	@PostMapping("/cad/{id}")
	public ResponseEntity<?> cadVeiculo(@RequestBody Veiculo veiculo, @PathVariable long id) {
		HttpStatus status = HttpStatus.CONFLICT;
		if (veiculo.getId() == null) {
			User user = repositorioUsuario.getById(id);
			user.getVeiculos().add(veiculo);
		    repositorioUsuario.save(user);
			status = HttpStatus.CREATED;
		}
		return new ResponseEntity<>(status);

	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateVeiculo(@RequestBody Veiculo atualizacao) {
		HttpStatus status = HttpStatus.CONFLICT;
		Veiculo veiculo = repositorioVeiculo.getById(atualizacao.getId());
		if (veiculo != null) {
			AtualizadorVeiculo atualizador = new AtualizadorVeiculo();
			atualizador.atualizar(veiculo, atualizacao);
			repositorioVeiculo.save(veiculo);
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(status);
		
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteVeiculo(@RequestBody Veiculo exclusao, @PathVariable long id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Veiculo veiculo = repositorioVeiculo.getById(exclusao.getId());
		if (veiculo != null) {
			User user = repositorioUsuario.getById(id);
			Set<Veiculo> veiculosUsuario = user.getVeiculos();
			for (Veiculo ve : veiculosUsuario) {
		        if (ve.getId() == exclusao.getId()) {
		        	veiculosUsuario.remove(ve);
		            break;
		        }
		    }
		    user.setVeiculos(veiculosUsuario);
			repositorioUsuario.save(user);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);
		
	}
}
