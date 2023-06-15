package com.autobots.automanager.model;

import java.util.List;

import com.autobots.automanager.entity.Tel;

public class TelefoneAtualizador {
	private VerificadSeNulo verificador = new VerificadSeNulo();

	public void atualizar(Tel tel, Tel atualizacao) {
		if (atualizacao != null) {
			if (!verificador.verificar(atualizacao.getDdd())) {
				tel.setDdd(atualizacao.getDdd());
			}
			if (!verificador.verificar(atualizacao.getNumero())) {
				tel.setNumero(atualizacao.getNumero());
			}
		}
	}

	public void atualizar(List<Tel> tels, List<Tel> atualizacoes) {
		for (Tel atualizacao : atualizacoes) {
			for (Tel tel : tels) {
				if (atualizacao.getId() != null) {
					if (atualizacao.getId() == tel.getId()) {
						atualizar(tel, atualizacao);
					}
				}
			}
		}
	}
}