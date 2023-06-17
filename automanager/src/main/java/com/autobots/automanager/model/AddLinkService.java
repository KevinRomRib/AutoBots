package com.autobots.automanager.link;

import com.autobots.automanager.controller.ServiceControle;
import com.autobots.automanager.entity.Service;
import com.autobots.automanager.model.AddLink;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddLinkService implements AddLink<Service> {

	@Override
	public void adicionarLink( List<Service> lista ) {
		for (Service service : lista) {
			Link linkServico =  WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(ServiceControle.class)
							.buscarPorId(service.getId()))
					.withSelfRel();
			service.add(linkServico);
			
		}
	}
	@Override
	public void adicionarLink( Service service) {
			Link linkServico =  WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(ServiceControle.class)
							.buscarTodos())
					.withRel("Todos Serviços");
			service.add(linkServico);
			
}
}
