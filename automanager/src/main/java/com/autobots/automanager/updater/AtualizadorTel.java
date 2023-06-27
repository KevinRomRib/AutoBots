package com.autobots.automanager.updater;

import java.util.Set;

import com.autobots.automanager.entity.Tel;
import com.autobots.automanager.model.VerificadSeNulo;

public class AtualizadorTel {
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

	public void atualizar(Set<Tel> tels, Set<Tel> atualizacoes) {
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