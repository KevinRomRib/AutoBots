package com.autobots.automanager.link;

import com.autobots.automanager.controller.ServiceControler;
import com.autobots.automanager.entity.Servico;
import com.autobots.automanager.model.AdicionadorLink;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkServico implements AdicionadorLink<Servico> {

	@Override
	public void adicionarLink( List<Servico> lista ) {
		for (Servico servico : lista) {
			Link linkServico =  WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(ServiceControler.class)
							.buscarPorId(servico.getId()))
					.withSelfRel();
			servico.add(linkServico);
			
		}
	}
	@Override
	public void adicionarLink( Servico servico ) {
			Link linkServico =  WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(ServiceControler.class)
							.buscarTodos())
					.withRel("Todos Serviços");
			servico.add(linkServico);
			
}
}
