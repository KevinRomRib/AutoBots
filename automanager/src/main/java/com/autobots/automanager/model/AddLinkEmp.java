package com.autobots.automanager.model;

import com.autobots.automanager.entity.Emp;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

import com.autobots.automanager.controller.EmpControle;


@Component
public class AddLinkEmp implements AddLink<Emp> {

	@Override
	public void adicionarLink(List<Emp> lista) {
		for (Emp emp : lista) {
			Link linkEmpresa = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(EmpControle.class)
							.buscarEmpPorId(emp.getId()))
					.withSelfRel();
			emp.add(linkEmpresa);
		}
	}
	
	@Override
	public void adicionarLink(Emp emp) {
		Link linkEmpresa = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EmpControle.class)
						.buscarEmp())
				.withRel("Todas Empresas");
		emp.add(linkEmpresa);
	}
}
