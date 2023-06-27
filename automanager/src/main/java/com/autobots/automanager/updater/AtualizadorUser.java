package com.autobots.automanager.updater;

import java.util.Set;

import com.autobots.automanager.entity.User;
import com.autobots.automanager.model.VerificadSeNulo;

public class AtualizadorUser {
	private VerificadSeNulo verificador = new VerificadSeNulo();
	private AtualizadorEnd atualizadorEnd = new AtualizadorEnd();
	private AtualizadorDoc atualizadorDoc = new AtualizadorDoc();
	private AtualizadorTel atualizadorTel = new AtualizadorTel();
	private AtualizadorEmail atualizadorEmail = new AtualizadorEmail();
	private CredencialAtualizador credencialAtualizador = new CredencialAtualizador();
	private AtualizadorMercadoria atualizadorMercadoria = new AtualizadorMercadoria();
	private AtualizadorVenda atualizadorVenda = new AtualizadorVenda();

	private void atualizarDados(User user, User atualizacao) {
		if (!verificador.verificar(atualizacao.getNome())) {
			user.setNome(atualizacao.getNome());
		}
		if (!verificador.verificar(atualizacao.getNomeSocial())) {
			user.setNomeSocial(atualizacao.getNomeSocial());
		}
		if (!(verificador.verificarPerfil(atualizacao.getPerfis()))) {
			user.setPerfis(atualizacao.getPerfis());
		}
		
	}

	public void atualizar(User user, User atualizacao) {
		atualizarDados(user, atualizacao);
		atualizadorTel.atualizar(user.getTels(), atualizacao.getTels());
		atualizadorEnd.atualizar(user.getEnd(), atualizacao.getEnd());
		atualizadorDoc.atualizar(user.getDocs(), atualizacao.getDocs());
		atualizadorEmail.atualizar(user.getEmails(), atualizacao.getEmails());
		credencialAtualizador.atualizar(user.getCredenciais(), atualizacao.getCredenciais());
		atualizadorMercadoria.atualizar(user.getMercadorias(), atualizacao.getMercadorias());
		atualizadorVenda.atualizar(user.getVendas(), atualizacao.getVendas());
	}
	
	public void atualizar(Set<User> users, Set<User> atualizacoes) {
		for (User atualizacao : atualizacoes) {
			for (User user : users) {
				if (atualizacao.getId() != null) {
					if (atualizacao.getId() == user.getId()) {
						atualizar(user, atualizacao);
					}
				}
			}
		}
	}
}
