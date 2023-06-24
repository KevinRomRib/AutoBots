package com.autobots.automanager.adder;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controller.CliControle;
import com.autobots.automanager.entity.Client;

@Component
public class AddLinkCli implements AddLink<Client> {

	@Override
	public void addLink(List<Client> lista) {
		for (Client client : lista) {
			long id = client.getId();
			Link linkProprioObterCliente = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(CliControle.class)
							.obterCli(id))
					.withSelfRel();
			client.add(linkProprioObterCliente);
			
			Link linkProprioObterClientes = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(CliControle.class)
							.obterCli())
					.withSelfRel();
			client.add(linkProprioObterClientes);
					
			Link linkProprioAtualizarCliente = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(CliControle.class)
							.updateCli(client))
					.withSelfRel();
			client.add(linkProprioAtualizarCliente);
			
			Link linkProprioExcluirCliente = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(CliControle.class)
							.deleteCli(client))
					.withSelfRel();
			client.add(linkProprioExcluirCliente);
			
		}
	}

	@Override
	public void addLink(Client objeto) {
		Link linkProprioObterCliente = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(CliControle.class)
						.obterCli(objeto.getId()))
				.withRel("cliente");
		objeto.add(linkProprioObterCliente);
		
		Link linkProprioObterClientes = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(CliControle.class)
						.obterCli())
				.withRel("clientes");
		objeto.add(linkProprioObterClientes);
		
		Link linkProprioAtualizarCliente = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(CliControle.class)
						.updateCli(objeto))
				.withRel("atualizar");
		objeto.add(linkProprioAtualizarCliente);
		
		Link linkProprioExcluirCliente = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(CliControle.class)
						.deleteCli(objeto))
				.withRel("excluir");
		objeto.add(linkProprioExcluirCliente);
		
	}

	@Override
	public void addLink(Client objeto, Client client) {
		// TODO Auto-generated method stub
		
	}


}
