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

import com.autobots.automanager.adder.AddLinkUser;
import com.autobots.automanager.updater.AtualizadorUser;
import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.entity.User;
import com.autobots.automanager.repository.RepositorioEmpresa;
import com.autobots.automanager.repository.RepositorioUsuario;
import com.autobots.automanager.selecter.EmpresaUsuarioSelecionador;
import com.autobots.automanager.selecter.UsuarioSelecionador;

@RestController
@RequestMapping("/user")
public class UserControle {
	@Autowired
	private RepositorioUsuario repositorioUsuario;
	@Autowired
	private UsuarioSelecionador selecionadorUsuario;
	@Autowired
	private EmpresaUsuarioSelecionador selecionadorEmpUsuario;
	@Autowired
	private RepositorioEmpresa repositorioEmpresa;
	@Autowired
	private AddLinkUser adicionadorLink;

	@GetMapping("/findOne/{id}")
	public ResponseEntity<User> obterUser(@PathVariable long id) {
		List<User> users = repositorioUsuario.findAll();
		User user = selecionadorUsuario.selecionar(users, id);
		if (user == null) {
			ResponseEntity<User> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLink.adicionarLink(user);
			List<Emp> emps = repositorioEmpresa.findAll();
			for (User usu : users) {
				Emp emp = selecionadorEmpUsuario.selecionar(emps, usu);
				adicionadorLink.adicionarLink(usu, emp);
			}
			ResponseEntity<User> resposta = new ResponseEntity<User>(user, HttpStatus.FOUND);
			return resposta;
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<User>> obterUsers() {
		List<User> users = repositorioUsuario.findAll();
		if (users.isEmpty()) {
			ResponseEntity<List<User>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLink.adicionarLink(users);
			List<Emp> emps = repositorioEmpresa.findAll();
			for (User user : users) {
				Emp emp = selecionadorEmpUsuario.selecionar(emps, user);
				adicionadorLink.adicionarLink(user, emp);
			}
			ResponseEntity<List<User>> resposta = new ResponseEntity<>(users, HttpStatus.FOUND);
			return resposta;
		}
	}

	@PostMapping("/cad/{id}")
	public ResponseEntity<?> cadUser(@RequestBody User user, @PathVariable long id) {
		HttpStatus status = HttpStatus.CONFLICT;
		if (user.getId() == null) {
			Emp emp = repositorioEmpresa.getById(id);
			Set<User> usuariosEmpresa = emp.getUsers();
			usuariosEmpresa.add(user);
			emp.setUsers(usuariosEmpresa);
			repositorioEmpresa.save(emp);
			status = HttpStatus.CREATED;
		}
		return new ResponseEntity<>(status);

	}

	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody User atualizacao) {
		HttpStatus status = HttpStatus.CONFLICT;
		User user = repositorioUsuario.getById(atualizacao.getId());
		if (user != null) {
			AtualizadorUser atualizador = new AtualizadorUser();
			atualizador.atualizar(user, atualizacao);
			repositorioUsuario.save(user);
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(status);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@RequestBody User exclusao, @PathVariable long id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		User user = repositorioUsuario.getById(exclusao.getId());
		if (user != null) {
			Emp emp = repositorioEmpresa.getById(id);
			Set<User> users = emp.getUsers();
			for (User usu: users) {
				if (user.getId() == exclusao.getId()) {
					users.remove(usu);
					break;
				}
			}
			emp.setUsers(users);
			repositorioEmpresa.save(emp);
			status = HttpStatus.OK;
		}
		
		return new ResponseEntity<>(status);
	}
}
