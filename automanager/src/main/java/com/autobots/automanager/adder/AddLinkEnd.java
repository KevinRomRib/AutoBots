package com.autobots.automanager.adder;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controller.EndControle;
import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.entity.End;
import com.autobots.automanager.entity.Mercadoria;
import com.autobots.automanager.entity.User;

@Component
public class AddLinkEnd implements AddLink<End> {

	@Override
	public void adicionarLink(List<End> lista) {
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
	public void adicionarLink(End objeto) {
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
	public void adicionarLink(End objeto, User user) {
		Link linkProprioExcluirEndereco = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EndControle.class)
						.deleteEnd(user.getId()))
				.withRel("excluirEndereco");
		objeto.add(linkProprioExcluirEndereco);
		
	}

	@Override
	public void adicionarLink(End objeto, Emp emp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adicionarLink(End objeto, Mercadoria mercadoria) {
		// TODO Auto-generated method stub
		
	}

}
