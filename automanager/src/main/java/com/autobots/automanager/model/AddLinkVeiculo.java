package com.autobots.automanager.model;

import java.util.List;

import com.autobots.automanager.entity.Veiculo;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controller.VeiculoControle;



@Component
public class AddLinkVeiculo implements AddLink<Veiculo> {
	
	@Override
	public void adicionarLink( List<Veiculo> lista ) {
		for (Veiculo veiculo : lista) {
			Link linkVeiculo =  WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(VeiculoControle.class)
							.buscarPorId(veiculo.getId()))
					.withSelfRel();
			veiculo.add(linkVeiculo);
			
		}
	}
	@Override
	public void adicionarLink( Veiculo veiculo ) {
			Link linkVeiculo =  WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(VeiculoControle.class)
							.buscarTodos())
					.withRel("Todos Clientes");
			veiculo.add(linkVeiculo);
			
}
	}
