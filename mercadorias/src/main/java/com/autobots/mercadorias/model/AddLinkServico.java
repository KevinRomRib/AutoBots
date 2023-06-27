package com.autobots.mercadorias.model;


import com.autobots.mercadorias.controller.ServiceControler;
import com.autobots.mercadorias.entity.Service;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddLinkServico implements AddLink<Service> {

	@Override
	public void adicionarLink( List<Service> lista ) {
		for (Service service : lista) {
			Link linkServico =  WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(ServiceControler.class)
							.buscarPorId(service.getId()))
					.withSelfRel();
			service.add(linkServico);

		}
	}
	@Override
	public void adicionarLink( Service service) {
			Link linkServico =  WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(ServiceControler.class)
							.buscarTodos())
					.withRel("Todos Serviços");
			service.add(linkServico);
			
}
}
