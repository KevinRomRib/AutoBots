package com.autobots.veiculos.model;

import java.util.List;


import com.autobots.veiculos.controller.VeiculoControler;
import com.autobots.veiculos.entity.Veiculo;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;





@Component
public class AddLinkVeiculo implements AddLink<Veiculo> {
	
	@Override
	public void adicionarLink( List<Veiculo> lista ) {
		for (Veiculo veiculo : lista) {
			Link linkVeiculo =  WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(VeiculoControler.class)
							.buscarPorId(veiculo.getId()))
					.withSelfRel();
			veiculo.add(linkVeiculo);
			
		}
	}
	@Override
	public void adicionarLink( Veiculo veiculo ) {
			Link linkVeiculo =  WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(VeiculoControler.class)
							.buscarTodos())
					.withRel("Todos Clientes");
			veiculo.add(linkVeiculo);
			
}
	}
