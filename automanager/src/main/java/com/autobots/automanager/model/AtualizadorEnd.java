package com.autobots.automanager.model;

import com.autobots.automanager.entity.End;

public class AtualizadorEnd {
	private VerificadSeNulo verif = new VerificadSeNulo();

	public void atualizar(End end, End atualizacao) {
		if (atualizacao != null) {
			if (!verif.verificar(atualizacao.getEstado())) {
				end.setEstado(atualizacao.getEstado());
			}
			if (!verif.verificar(atualizacao.getCidade())) {
				end.setCidade(atualizacao.getCidade());
			}
			if (!verif.verificar(atualizacao.getBairro())) {
				end.setBairro(atualizacao.getBairro());
			}
			if (!verif.verificar(atualizacao.getRua())) {
				end.setRua(atualizacao.getRua());
			}
			if (!verif.verificar(atualizacao.getNumero())) {
				end.setNumero(atualizacao.getNumero());
			}
			if (!verif.verificar(atualizacao.getInformacoesAdicionais())) {
				end.setInformacoesAdicionais(atualizacao.getInformacoesAdicionais());
			}
		}
	}
}