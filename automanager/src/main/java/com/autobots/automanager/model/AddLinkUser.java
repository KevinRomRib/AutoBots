package com.autobots.automanager.model;

import com.autobots.automanager.controller.UserControle;
import com.autobots.automanager.entity.User;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddLinkUser implements AddLink<User> {
	@Override
	public void adicionarLink( List<User> lista ) {
		for (User cliente : lista) {
			Link linkUsuario =  WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(UserControle.class)
							.pegarUm(cliente.getId()))
					.withSelfRel();
			cliente.add(linkUsuario);
		}
	}
	
	@Override
	public void adicionarLink( User user) {
			Link linkUsuario =  WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(UserControle.class)
							.pegarTodos())
					.withRel("Todos Veiculos");
			user.add(linkUsuario);
	}
}
