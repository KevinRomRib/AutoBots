package com.autobots.automanager.model;

import com.autobots.automanager.entity.Client;

public class AtualizadorCliente {
	private VerificadSeNulo verif = new VerificadSeNulo();
	private AtualizadorEnd atualizadorEnd = new AtualizadorEnd();
	private AtualizadorDoc atualizadorDoc = new AtualizadorDoc();
	private TelefoneAtualizador atualizadorTel = new TelefoneAtualizador();

	private void atualizarDados(Client client, Client atualizacao) {
		if (!verif.verificar(atualizacao.getNome())) {
			client.setNome(atualizacao.getNome());
		}
		if (!verif.verificar(atualizacao.getNomeSocial())) {
			client.setNomeSocial(atualizacao.getNomeSocial());
		}
		if (!(atualizacao.getDataCadastro() == null)) {
			client.setDataCadastro(atualizacao.getDataCadastro());
		}
		if (!(atualizacao.getDataNascimento() == null)) {
			client.setDataNascimento(atualizacao.getDataNascimento());
		}
	}

	public void atualizar(Client client, Client atualizacao) {
		atualizarDados(client, atualizacao);
		atualizadorEnd.atualizar(client.getEnd(), atualizacao.getEnd());
		atualizadorDoc.atualizar(client.getDocs(), atualizacao.getDocs());
		atualizadorTel.atualizar(client.getTels(), atualizacao.getTels());
	}
}
