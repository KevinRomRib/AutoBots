package com.autobots.automanager.adder;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controller.VeiculoControle;
import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.entity.Mercadoria;
import com.autobots.automanager.entity.User;
import com.autobots.automanager.entity.Veiculo;

@Component
public class AddLinkVeiculo implements AddLink<Veiculo> {

	@Override
	public void adicionarLink(List<Veiculo> lista) {
		for (Veiculo veiculo : lista) {
			long id = veiculo.getId();
			Link linkProprioObterVeiculo = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(VeiculoControle.class)
							.obterVeiculo(id))
					.withSelfRel();
			veiculo.add(linkProprioObterVeiculo);
			
			Link linkProprioObterVeiculos = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(VeiculoControle.class)
							.obterVeiculos())
					.withSelfRel();
			veiculo.add(linkProprioObterVeiculos);
					
			Link linkProprioAtualizarVeiculo = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(VeiculoControle.class)
							.updateVeiculo(veiculo))
					.withSelfRel();
			veiculo.add(linkProprioAtualizarVeiculo);			
		}
	}

	@Override
	public void adicionarLink(Veiculo objeto) {
		Link linkProprioObterVeiculo = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(VeiculoControle.class)
						.obterVeiculo(objeto.getId()))
				.withRel("veiculo");
		objeto.add(linkProprioObterVeiculo);
		
		Link linkProprioObterVeiculos = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(VeiculoControle.class)
						.obterVeiculos())
				.withRel("veiculos");
		objeto.add(linkProprioObterVeiculos);
				
		Link linkProprioAtualizarVeiculo = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(VeiculoControle.class)
						.updateVeiculo(objeto))
				.withRel("atualizarVeiculo");
		objeto.add(linkProprioAtualizarVeiculo);
		
	}
	
	@Override
	public void adicionarLink(Veiculo objeto, User user) {
		Link linkProprioExcluirVeiculo = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(VeiculoControle.class)
						.deleteVeiculo(objeto, user.getId()))
				.withRel("excluirVeiculo");
		objeto.add(linkProprioExcluirVeiculo);
		
	}

	@Override
	public void adicionarLink(Veiculo objeto, Emp emp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adicionarLink(Veiculo objeto, Mercadoria mercadoria) {
		// TODO Auto-generated method stub
		
	}



}
