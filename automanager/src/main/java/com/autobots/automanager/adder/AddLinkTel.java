package com.autobots.automanager.adder;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controller.TelControle;
import com.autobots.automanager.entity.Client;
import com.autobots.automanager.entity.Tel;

@Component
public class AddLinkTel implements AddLink<Tel> {

	@Override
	public void addLink(List<Tel> lista) {
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
	public void addLink(Tel objeto) {
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
	public void addLink(Tel objeto, Client client) {
		Link linkProprioExcluirTelefone = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(TelControle.class)
						.deleteTel(objeto, client.getId()))
				.withRel("excluirTelefone");
		objeto.add(linkProprioExcluirTelefone);
		
	}

}
