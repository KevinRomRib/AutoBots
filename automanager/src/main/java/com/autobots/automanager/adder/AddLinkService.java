package com.autobots.automanager.adder;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controller.ServiceControle;
import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.entity.Mercadoria;
import com.autobots.automanager.entity.Service;
import com.autobots.automanager.entity.User;

@Component
public class AddLinkService implements AddLink<Service> {

	@Override
	public void adicionarLink(List<Service> lista) {
		for (Service service : lista) {
			long id = service.getId();
			Link linkProprioObterServico = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(ServiceControle.class)
							.obterService(id))
					.withSelfRel();
			service.add(linkProprioObterServico);
			
			Link linkProprioObterServicos = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(ServiceControle.class)
							.obterServices())
					.withSelfRel();
			service.add(linkProprioObterServicos);

			Link linkProprioAtualizarServico = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(ServiceControle.class)
							.updateService(service))
					.withSelfRel();
			service.add(linkProprioAtualizarServico);
			
		}
	}

	@Override
	public void adicionarLink(Service objeto) {
		Link linkProprioObterServico = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(ServiceControle.class)
						.obterService(objeto.getId()))
				.withRel("servico");
		objeto.add(linkProprioObterServico);
		
		Link linkProprioObterServicos = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(ServiceControle.class)
						.obterServices())
				.withRel("servicos");
		objeto.add(linkProprioObterServicos);
			
		Link linkProprioAtualizarServico = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(ServiceControle.class)
						.updateService(objeto))
				.withRel("atualizar");
		objeto.add(linkProprioAtualizarServico);
		
	}
	
	@Override
	public void adicionarLink(Service objeto, Emp emp) {
		Link linkProprioExcluirServico = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(ServiceControle.class)
						.deleteServico(objeto, emp.getId()))
				.withRel("excluirServico");
		objeto.add(linkProprioExcluirServico);
		
	}

	@Override
	public void adicionarLink(Service objeto, User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adicionarLink(Service objeto, Mercadoria mercadoria) {
		// TODO Auto-generated method stub
		
	}
}
