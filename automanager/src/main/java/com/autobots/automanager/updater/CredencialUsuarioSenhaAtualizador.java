package com.autobots.automanager.updater;

import com.autobots.automanager.entity.CredencialUsuarioSenha;
import com.autobots.automanager.model.VerificadSeNulo;

public class CredencialUsuarioSenhaAtualizador {
	private VerificadSeNulo verificador = new VerificadSeNulo();

	public void atualizar(CredencialUsuarioSenha credencialUsuarioSenha, CredencialUsuarioSenha atualizacao) {
		if (atualizacao != null) {
			if (!verificador.verificar(atualizacao.getNomeUsuario())) {
				credencialUsuarioSenha.setNomeUsuario(atualizacao.getNomeUsuario());
			}
			if (!verificador.verificar(atualizacao.getSenha())) {
				credencialUsuarioSenha.setSenha(atualizacao.getSenha());
			}
		}
	}
}	
