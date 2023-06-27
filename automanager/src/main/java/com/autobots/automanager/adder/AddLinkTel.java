package com.autobots.automanager.adder;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controller.TelControle;
import com.autobots.automanager.entity.User;
import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.entity.Mercadoria;
import com.autobots.automanager.entity.Tel;

@Component
public class AddLinkTel implements AddLink<Tel> {

	@Override
	public void adicionarLink(List<Tel> lista) {
		for (Tel tel : lista) {
			long id = tel.getId();
			Link linkProprioObterTelefone = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(TelControle.class)
							.obterTel(id))
					.withSelfRel();
			tel.add(linkProprioObterTelefone);
			
			Link linkProprioObterTelefones = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(TelControle.class)
							.obterTel())
					.withSelfRel();
			tel.add(linkProprioObterTelefones);
					
			Link linkProprioAtualizarTelefone = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(TelControle.class)
							.updateTel(tel))
					.withSelfRel();
			tel.add(linkProprioAtualizarTelefone);
			
		}
	}
	
	@Override
	public void adicionarLink(Tel objeto) {
		Link linkProprioObterTelefone = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(TelControle.class)
						.obterTel(objeto.getId()))
				.withRel("telefone");
		objeto.add(linkProprioObterTelefone);
		
		Link linkProprioObterTelefones = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(TelControle.class)
						.obterTel())
				.withRel("telefones");
		objeto.add(linkProprioObterTelefones);
		
		Link linkProprioAtualizarTelefone = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(TelControle.class)
						.updateTel(objeto))
				.withRel("atualizarTelefone");
		objeto.add(linkProprioAtualizarTelefone);
		
	}
	
	
	@Override
	public void adicionarLink(Tel objeto, User user) {
		Link linkProprioExcluirTelefone = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(TelControle.class)
						.deleteTel(objeto, user.getId()))
				.withRel("excluirTelefone");
		objeto.add(linkProprioExcluirTelefone);
		
	}

	@Override
	public void adicionarLink(Tel objeto, Emp emp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adicionarLink(Tel objeto, Mercadoria mercadoria) {
		// TODO Auto-generated method stub
		
	}

}
