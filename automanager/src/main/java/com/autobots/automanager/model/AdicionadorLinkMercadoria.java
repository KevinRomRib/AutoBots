package com.autobots.automanager.model;

import java.util.List;

import com.autobots.automanager.entity.Mercadoria;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controller.MercadoriaControler;

@Component
public class AdicionadorLinkMercadoria implements AdicionadorLink<Mercadoria> {
	
	@Override
	public void adicionarLink (List<Mercadoria> lista) {
		for (Mercadoria mercadoria : lista) {
			Link linkMercadoria = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(MercadoriaControler.class)
							.buscarPorId(mercadoria.getId()))
					.withSelfRel();
			mercadoria.add(linkMercadoria);
		}
	}
	
	@Override
	public void adicionarLink( Mercadoria mercadoria ) {
		Link linkMercadoria =  WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(MercadoriaControler.class)
						.buscarTodos())
				.withRel("Todas Mercadorias");
		mercadoria.add(linkMercadoria);
}
}
