package com.autobots.automanager.adder;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controller.EmpControle;
import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.entity.Mercadoria;
import com.autobots.automanager.entity.User;

@Component
public class AddLinkEmp implements AddLink<Emp> {

	@Override
	public void adicionarLink(List<Emp> lista) {
		for (Emp emp : lista) {
			long id = emp.getId();
			Link linkProprioObterEmpresa = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(EmpControle.class)
							.obterEmp(id))
					.withSelfRel();
			emp.add(linkProprioObterEmpresa);
			
			Link linkProprioObterEmpresas = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(EmpControle.class)
							.obterEmp())
					.withSelfRel();
			emp.add(linkProprioObterEmpresas);
					
			Link linkProprioAtualizarEmpresa = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(EmpControle.class)
							.updateEmp(emp))
					.withSelfRel();
			emp.add(linkProprioAtualizarEmpresa);
			
		}
	}

	@Override
	public void adicionarLink(Emp objeto) {
		Link linkProprioObterEmpresa = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EmpControle.class)
						.obterEmp(objeto.getId()))
				.withRel("empresa");
		objeto.add(linkProprioObterEmpresa);
		
		Link linkProprioObterEmpresas = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EmpControle.class)
						.obterEmp())
				.withRel("empresas");
		objeto.add(linkProprioObterEmpresas);
		
		Link linkProprioAtualizarEmpresa = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EmpControle.class)
						.updateEmp(objeto))
				.withRel("atualizar");
		objeto.add(linkProprioAtualizarEmpresa);
		
		Link linkProprioExcluirEmpresa = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EmpControle.class)
						.deleteEmp(objeto))
				.withRel("excluir");
		objeto.add(linkProprioExcluirEmpresa);
		
	}

	@Override
	public void adicionarLink(Emp objeto, User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adicionarLink(Emp objeto, Emp emp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adicionarLink(Emp objeto, Mercadoria mercadoria) {
		// TODO Auto-generated method stub
		
	}



}