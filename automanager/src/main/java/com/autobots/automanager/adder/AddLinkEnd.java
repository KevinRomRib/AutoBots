package com.autobots.automanager.adder;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controller.EndControle;
import com.autobots.automanager.entity.Client;
import com.autobots.automanager.entity.End;

@Component
public class AddLinkEnd implements AddLink<End> {

	@Override
	public void addLink(List<End> lista) {
		for (End end : lista) {
			long id = end.getId();
			Link linkProprioObterEndereco = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(EndControle.class)
							.obterEnd(id))
					.withSelfRel();
			end.add(linkProprioObterEndereco);
			
			Link linkProprioObterEnderecos = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(EndControle.class)
							.obterEnd())
					.withSelfRel();
			end.add(linkProprioObterEnderecos);
			
			Link linkProprioAtualizarEndereco = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(EndControle.class)
							.updateEnd(end))
					.withSelfRel();
			end.add(linkProprioAtualizarEndereco);
			
		}
	}

	@Override
	public void addLink(End objeto) {
		Link linkProprioObterEndereco = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EndControle.class)
						.obterEnd(objeto.getId()))
				.withRel("endereco");
		objeto.add(linkProprioObterEndereco);
		
		Link linkProprioObterEnderecos = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EndControle.class)
						.obterEnd())
				.withRel("enderecos");
		objeto.add(linkProprioObterEnderecos);
		
		Link linkProprioAtualizarEndereco = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EndControle.class)
						.updateEnd(objeto))
				.withRel("atualizarEndereco");
		objeto.add(linkProprioAtualizarEndereco);
	}
	
	@Override
	public void addLink(End objeto, Client client) {
		Link linkProprioExcluirEndereco = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EndControle.class)
						.deleteEnd(client.getId()))
				.withRel("excluirEndereco");
		objeto.add(linkProprioExcluirEndereco);
		
	}

}
