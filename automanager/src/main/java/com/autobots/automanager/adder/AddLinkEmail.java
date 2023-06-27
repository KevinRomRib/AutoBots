package com.autobots.automanager.adder;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controller.EmailControle;
import com.autobots.automanager.entity.Email;
import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.entity.Mercadoria;
import com.autobots.automanager.entity.User;

@Component
public class AddLinkEmail implements AddLink<Email> {

	@Override
	public void adicionarLink(List<Email> lista) {
		for (Email email : lista) {
			long id = email.getId();
			Link linkProprioObterEmail = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(EmailControle.class)
							.obterEmail(id))
					.withSelfRel();
			email.add(linkProprioObterEmail);
			
			Link linkProprioObterEmails = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(EmailControle.class)
							.obterEmails())
					.withSelfRel();
			email.add(linkProprioObterEmails);
					
			Link linkProprioAtualizarEmail = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(EmailControle.class)
							.updateEmail(email))
					.withSelfRel();
			email.add(linkProprioAtualizarEmail);
			
		}
	}
	
	@Override
	public void adicionarLink(Email objeto) {
		Link linkProprioObterEmail = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EmailControle.class)
						.obterEmail(objeto.getId()))
				.withRel("email");
		objeto.add(linkProprioObterEmail);
		
		Link linkProprioObterEmails = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EmailControle.class)
						.obterEmails())
				.withRel("emails");
		objeto.add(linkProprioObterEmails);
		
		Link linkProprioAtualizarEmail = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EmailControle.class)
						.updateEmail(objeto))
				.withRel("atualizarTelefone");
		objeto.add(linkProprioAtualizarEmail);
		
	}
	
	
	@Override
	public void adicionarLink(Email objeto, User user) {
		Link linkProprioExcluirEmail = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EmailControle.class)
						.deleteEmail(objeto, user.getId()))
				.withRel("excluirTelefone");
		objeto.add(linkProprioExcluirEmail);
		
	}

	@Override
	public void adicionarLink(Email objeto, Emp emp) {
	}

	

	@Override
	public void adicionarLink(Email objeto, Mercadoria mercadoria) {		
	}

}
