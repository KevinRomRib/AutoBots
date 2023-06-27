package com.autobots.automanager.updater;

import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.model.VerificadSeNulo;

public class AtualizadorEmp {
	private VerificadSeNulo verificador = new VerificadSeNulo();
	private AtualizadorEnd atualizadorEnd = new AtualizadorEnd();
	private AtualizadorTel atualizadorTel = new AtualizadorTel();
	private AtualizadorMercadoria atualizadorMercadoria = new AtualizadorMercadoria();
	private AtualizadorVenda atualizadorVenda = new AtualizadorVenda();
	private AtualizadorUser atualizadorUser = new AtualizadorUser();
	private AtualizadorService atualizadorService = new AtualizadorService();
	
	public void atualizar(Emp emp, Emp atualizacao) {
		if (atualizacao != null) {
			if (!verificador.verificar(atualizacao.getRazaoSocial())) {
				emp.setRazaoSocial(atualizacao.getRazaoSocial());
			}
			if (!verificador.verificar(atualizacao.getNomeFantasia())) {
				emp.setNomeFantasia(atualizacao.getNomeFantasia());
			}
			if (!verificador.verificar(atualizacao.getCadastro())) {
				emp.setCadastro(atualizacao.getCadastro());
			}
		}
	}

	public void atualizarInformacoes(Emp emp, Emp atualizacao) {
		atualizar(emp, atualizacao);
		atualizadorTel.atualizar(emp.getTels(), atualizacao.getTels());
		atualizadorEnd.atualizar(emp.getEnd(), atualizacao.getEnd());
		atualizadorUser.atualizar(emp.getUsers(), atualizacao.getUsers());
		atualizadorMercadoria.atualizar(emp.getMercadorias(), atualizacao.getMercadorias());
		atualizadorService.atualizar(emp.getServices(), atualizacao.getServices());
		atualizadorVenda.atualizar(emp.getVendas(), atualizacao.getVendas());
	}
}