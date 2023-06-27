package com.autobots.automanager.updater;

import java.util.Set;

import com.autobots.automanager.entity.Email;
import com.autobots.automanager.model.VerificadSeNulo;

public class AtualizadorEmail {
	private VerificadSeNulo verificador = new VerificadSeNulo();

	public void atualizar(Email email, Email atualizacao) {
		if (atualizacao != null) {
			if (!verificador.verificar(atualizacao.getEndereco())) {
				email.setEndereco(atualizacao.getEndereco());
			}
		}
	}

	public void atualizar(Set<Email> emails, Set<Email> atualizacoes) {
		for (Email atualizacao : atualizacoes) {
			for (Email email : emails) {
				if (atualizacao.getId() != null) {
					if (atualizacao.getId() == email.getId()) {
						atualizar(email, atualizacao);
					}
				}
			}
		}
	}
}