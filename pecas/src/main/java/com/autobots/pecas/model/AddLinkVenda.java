package com.autobots.pecas.model;


import com.autobots.pecas.controller.VendaControler;
import com.autobots.pecas.entity.Venda;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddLinkVenda implements AddLink<Venda> {
	@Override
	public void adicionarLink (List<Venda> lista) {
		for (Venda vendas : lista) {
			Link linkVenda = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(VendaControler.class)
							.buscarPorId(vendas.getId()))
					.withSelfRel();
			vendas.add(linkVenda);
		}
	}
	
	@Override
	public void adicionarLink (Venda venda) {
		Link linkVenda = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(VendaControler.class)
						.buscarTodos())
				.withRel("Todas Vendas");
		venda.add(linkVenda);
	}

}
