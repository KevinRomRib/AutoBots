package com.autobots.automanager.updater;

import java.util.Set;

import com.autobots.automanager.entity.Credencial;
import com.autobots.automanager.model.VerificadSeNulo;

public class CredencialAtualizador {
	private VerificadSeNulo verificador = new VerificadSeNulo();

	public void atualizar(Credencial credencial, Credencial atualizacao) {
		if (atualizacao != null) {
			if (!verificador.verificar(atualizacao.getCriacao())) {
				credencial.setCriacao(atualizacao.getCriacao());
			}
			if (!verificador.verificar(atualizacao.getUltimoAcesso())) {
				credencial.setUltimoAcesso(atualizacao.getUltimoAcesso());
			}
			
		}
	}

	public void atualizar(Set<Credencial> credenciais, Set<Credencial> atualizacoes) {
		for (Credencial atualizacao : atualizacoes) {
			for (Credencial credencial : credenciais) {
				if (atualizacao.getId() != null) {
					if (atualizacao.getId() == credencial.getId()) {
						atualizar(credencial, atualizacao);
					}
				}
			}
		}
	}
}