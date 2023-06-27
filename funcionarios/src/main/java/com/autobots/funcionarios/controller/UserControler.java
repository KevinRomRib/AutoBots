package com.autobots.funcionarios.controller;

import com.autobots.funcionarios.entity.User;

import com.autobots.funcionarios.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserControler {

	@Autowired
	private UsuarioService usuarioService;

	
	@GetMapping("/buscar")
	public ResponseEntity<List<User>> buscarTodos(){
		List<User> user = usuarioService.findAll();
		return new ResponseEntity<List<User>>(user, HttpStatus.FOUND);
	}
	


}
