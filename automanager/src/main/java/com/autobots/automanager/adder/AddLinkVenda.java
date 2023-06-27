package com.autobots.automanager.adder;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controller.VendaControle;
import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.entity.Mercadoria;
import com.autobots.automanager.entity.User;
import com.autobots.automanager.entity.Venda;

@Component
public class AddLinkVenda implements AddLink<Venda> {

	@Override
	public void adicionarLink(List<Venda> lista) {
		for (Venda venda : lista) {
			long id = venda.getId();
			Link linkProprioObterVenda = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(VendaControle.class)
							.obterVenda(id))
					.withSelfRel();
			venda.add(linkProprioObterVenda);
			
			Link linkProprioObterVendas = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(VendaControle.class)
							.obterVendas())
					.withSelfRel();
			venda.add(linkProprioObterVendas);

			Link linkProprioAtualizarVenda = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(VendaControle.class)
							.updateVenda(venda))
					.withSelfRel();
			venda.add(linkProprioAtualizarVenda);
			
		}
	}

	@Override
	public void adicionarLink(Venda objeto) {
		Link linkProprioObterVenda = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(VendaControle.class)
						.obterVenda(objeto.getId()))
				.withRel("venda");
		objeto.add(linkProprioObterVenda);
		
		Link linkProprioObterVendas = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(VendaControle.class)
						.obterVendas())
				.withRel("venda");
		objeto.add(linkProprioObterVendas);
		
		Link linkProprioAtualizarVenda = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(VendaControle.class)
						.updateVenda(objeto))
				.withRel("atualizar");
		objeto.add(linkProprioAtualizarVenda);
		
	
		
	}
	
	@Override
	public void adicionarLink(Venda objeto, Emp emp) {
		Link linkProprioExcluirVenda = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(VendaControle.class)
						.deleteVenda(objeto, emp.getId()))
				.withRel("excluir");
		objeto.add(linkProprioExcluirVenda);
		
	}

	@Override
	public void adicionarLink(Venda objeto, User user) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void adicionarLink(Venda objeto, Mercadoria mercadoria) {
		// TODO Auto-generated method stub
		
	}
}
