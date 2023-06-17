package com.autobots.automanager.model;

import com.autobots.automanager.entity.End;

public class AtualizadorEnd {
	private VerificadSeNulo verificador = new VerificadSeNulo();

	public void atualizar(End end, End atualizacao) {
		if (atualizacao != null) {
			if (!verificador.verificar(atualizacao.getEstado())) {
				end.setEstado(atualizacao.getEstado());
			}
			if (!verificador.verificar(atualizacao.getCidade())) {
				end.setCidade(atualizacao.getCidade());
			}
			if (!verificador.verificar(atualizacao.getBairro())) {
				end.setBairro(atualizacao.getBairro());
			}
			if (!verificador.verificar(atualizacao.getRua())) {
				end.setRua(atualizacao.getRua());
			}
			if (!verificador.verificar(atualizacao.getNumero())) {
				end.setNumero(atualizacao.getNumero());
			}
			if (!verificador.verificar(atualizacao.getInformacoesAdicionais())) {
				end.setInformacoesAdicionais(atualizacao.getInformacoesAdicionais());
			}
		}
	}
}