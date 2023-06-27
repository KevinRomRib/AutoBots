package com.autobots.automanager.selecter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entity.CredencialUsuarioSenha;

@Component
public class CredencialUsuarioSenhaSelecionador {
	public CredencialUsuarioSenha selecionar(List<CredencialUsuarioSenha> credenciaisUsuariosSenhas, long id) {
		CredencialUsuarioSenha selecionado = null;
		for (CredencialUsuarioSenha credencialUsuarioSenha : credenciaisUsuariosSenhas) {
			if (credencialUsuarioSenha.getId() == id) {
				selecionado = credencialUsuarioSenha;
			}
		}
		return selecionado;
	}
}