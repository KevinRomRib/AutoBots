package com.autobots.automanager.updater;

import java.util.Set;

import com.autobots.automanager.entity.Venda;
import com.autobots.automanager.model.VerificadSeNulo;

public class AtualizadorVenda {
	private VerificadSeNulo verificador = new VerificadSeNulo();
	private static AtualizadorMercadoria atualizadorMercadoria = new AtualizadorMercadoria();
	private static AtualizadorService atualizadorService = new AtualizadorService();
	
	public void atualizar(Venda venda, Venda atualizacao) {
		if (atualizacao != null) {
			if (!verificador.verificar(atualizacao.getCadastro())) {
				venda.setCadastro(atualizacao.getCadastro());
			}
			if (!verificador.verificar(atualizacao.getIdentificacao())) {
				venda.setIdentificacao(atualizacao.getIdentificacao());
			}
			if (!verificador.verificar(atualizacao.getCliente())) {
				venda.setCliente(atualizacao.getCliente());
			}
			if (!verificador.verificar(atualizacao.getFuncionario())) {
				venda.setFuncionario(atualizacao.getFuncionario());
			}
			if (!verificador.verificar(atualizacao.getVeiculo())) {
				venda.setVeiculo(atualizacao.getVeiculo());
			}
			
			
			
		}
	}

	public void atualizar(Set<Venda> vendas, Set<Venda> atualizacoes) {
		for (Venda atualizacao : atualizacoes) {
			for (Venda venda : vendas) {
				if (atualizacao.getId() != null) {
					if (atualizacao.getId() == venda.getId()) {
						atualizar(venda, atualizacao);
						atualizadorMercadoria.atualizar(venda.getMercadorias(), atualizacao.getMercadorias());
						atualizadorService.atualizar(venda.getServices(), atualizacao.getServices());
						
					}
				}
			}
		}
	}
}