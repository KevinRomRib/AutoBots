package com.autobots.automanager.controller;

import com.autobots.automanager.entity.Empresa;
import com.autobots.automanager.entity.Usuario;
import com.autobots.automanager.entity.Veiculo;
import com.autobots.automanager.entity.Venda;
import com.autobots.automanager.model.AdicionadorLinkVenda;
import com.autobots.automanager.service.EmpresaService;
import com.autobots.automanager.service.UsuarioService;
import com.autobots.automanager.service.VeiculoService;
import com.autobots.automanager.service.VendaService;
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
	private EmpresaService empresaService;
	
	@Autowired
	private VeiculoService veiculoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private AdicionadorLinkVenda linkVenda;
	
	@PostMapping("/cad/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_GERENTE') or hasAnyAuthority('ROLE_ADMIN') or hasAnyAuthority('ROLE_VENDEDOR')")
	public ResponseEntity<?> cad(@RequestBody Venda venda, @PathVariable Long id){
		Empresa empresaSelecionada = empresaService.findById(id);
		if(empresaSelecionada == null) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Usuario funcionarioSelecionado = usuarioService.findById(venda.getFuncionario().getId());
		Usuario clienteSelecionado = usuarioService.findById(venda.getCliente().getId());
		Veiculo veiculoSelecionado = veiculoService.findById(venda.getVeiculo().getId());
		venda.setVeiculo(veiculoSelecionado);
		venda.setCliente(clienteSelecionado);
		venda.setFuncionario(funcionarioSelecionado);
		usuarioService.create(funcionarioSelecionado);

		empresaSelecionada.getVendas().add(venda);
		empresaService.create(empresaSelecionada);

		vendaService.create(venda);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	@GetMapping("/findAll")
	@PreAuthorize("hasAnyAuthority('ROLE_GERENTE') or hasAnyAuthority('ROLE_ADMIN')")
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

	@PutMapping("/update/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_GERENTE') or hasAnyAuthority('ROLE_ADMIN')")
		public ResponseEntity<?> updateVenda(@RequestBody Venda venda, @PathVariable Long id){
			Venda vendaExistente = vendaService.findById(id);
			if (vendaExistente == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			venda.setId(id);
			vendaService.update(venda);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
	
	
	
}
