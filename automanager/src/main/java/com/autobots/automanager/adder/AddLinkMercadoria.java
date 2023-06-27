package com.autobots.automanager.adder;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controller.MercadoriaControle;
import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.entity.Mercadoria;
import com.autobots.automanager.entity.User;

@Component
public class AddLinkMercadoria implements AddLink<Mercadoria> {

	@Override
	public void adicionarLink(List<Mercadoria> lista) {
		for (Mercadoria mercadoria : lista) {
			long id = mercadoria.getId();
			Link linkProprioObterMercadoria = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(MercadoriaControle.class)
							.obterMercadoria(id))
					.withSelfRel();
			mercadoria.add(linkProprioObterMercadoria);
			
			Link linkProprioObterMercadorias = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(MercadoriaControle.class)
							.obterMercadorias())
					.withSelfRel();
			mercadoria.add(linkProprioObterMercadorias);
					
			Link linkProprioAtualizarMercadoria = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(MercadoriaControle.class)
							.updateMercadoria(mercadoria))
					.withSelfRel();
			mercadoria.add(linkProprioAtualizarMercadoria);
			
		}
	}
	
	@Override
	public void adicionarLink(Mercadoria objeto) {
		Link linkProprioObterMercadoria = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(MercadoriaControle.class)
						.obterMercadoria(objeto.getId()))
				.withRel("mercadoria");
		objeto.add(linkProprioObterMercadoria);
		
		Link linkProprioObterMercadorias = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(MercadoriaControle.class)
						.obterMercadorias())
				.withRel("mercadorias");
		objeto.add(linkProprioObterMercadorias);
		
		Link linkProprioAtualizarMercadoria = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(MercadoriaControle.class)
						.updateMercadoria(objeto))
				.withRel("atualizarMercadoria");
		objeto.add(linkProprioAtualizarMercadoria);
		
	}
	
	
	@Override
	public void adicionarLink(Mercadoria objeto, Emp emp) {
		Link linkProprioExcluirMercadoria = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(MercadoriaControle.class)
						.deleteMercadoria(objeto, emp.getId()))
				.withRel("excluirMercadoria");
		objeto.add(linkProprioExcluirMercadoria);
		
	}

	@Override
	public void adicionarLink(Mercadoria objeto, User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adicionarLink(Mercadoria objeto, Mercadoria mercadoria) {
		// TODO Auto-generated method stub
		
	}

}