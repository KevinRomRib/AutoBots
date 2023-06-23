package com.autobots.automanager.model;

import com.autobots.automanager.entity.Empresa;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

import com.autobots.automanager.controller.EmpControle;


@Component
public class AdicionadorLinkEmpresa implements AdicionadorLink<Empresa> {

	@Override
	public void adicionarLink(List<Empresa> lista) {
		for (Empresa empresa : lista) {
			Link linkEmpresa = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(EmpControle.class)
							.buscarPorId(empresa.getId()))
					.withSelfRel();
			empresa.add(linkEmpresa);
		}
	}
	
	@Override
	public void adicionarLink(Empresa empresa) {
		Link linkEmpresa = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EmpControle.class)
						.buscarTodos())
				.withRel("Todas Empresas");
		empresa.add(linkEmpresa);
	}
}
