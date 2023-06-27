package com.autobots.automanager.adder;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controller.UserControle;
import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.entity.Mercadoria;
import com.autobots.automanager.entity.User;

@Component
public class AddLinkUser implements AddLink<User> {

	@Override
	public void adicionarLink(List<User> lista) {
		for (User user : lista) {
			long id = user.getId();
			Link linkProprioObterUsuario = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(UserControle.class)
							.obterUser(id))
					.withSelfRel();
			user.add(linkProprioObterUsuario);
			
			Link linkProprioObterUsuarios = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(UserControle.class)
							.obterUsers())
					.withSelfRel();
			user.add(linkProprioObterUsuarios);
					
			Link linkProprioAtualizarCliente = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(UserControle.class)
							.updateUser(user))
					.withSelfRel();
			user.add(linkProprioAtualizarCliente);
		}
	}

	@Override
	public void adicionarLink(User objeto) {
		Link linkProprioObterUsuario = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(UserControle.class)
						.obterUser(objeto.getId()))
				.withRel("usuario");
		objeto.add(linkProprioObterUsuario);
		
		Link linkProprioObterUsuarios = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(UserControle.class)
						.obterUsers())
				.withRel("usuarios");
		objeto.add(linkProprioObterUsuarios);
		
		Link linkProprioAtualizarUsuario = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(UserControle.class)
						.updateUser(objeto))
				.withRel("atualizar");
		objeto.add(linkProprioAtualizarUsuario);
	}

	@Override
	public void adicionarLink(User objeto, Emp emp) {
		Link linkProprioExcluirUsuario = WebMvcLinkBuilder
			.linkTo(WebMvcLinkBuilder
				.methodOn(UserControle.class)
				.deleteUser(objeto, emp.getId()))
			.withRel("excluir");
		objeto.add(linkProprioExcluirUsuario);
	}

	@Override
	public void adicionarLink(User objeto, User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adicionarLink(User objeto, Mercadoria mercadoria) {
		// TODO Auto-generated method stub
		
	}

}
