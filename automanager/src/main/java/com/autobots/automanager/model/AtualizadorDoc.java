package com.autobots.automanager.model;

import java.util.List;

import com.autobots.automanager.entity.Doc;

public class AtualizadorDoc {
	private VerificadSeNulo verificador = new VerificadSeNulo();

	public void atualizar(Doc doc, Doc atualizacao) {
		if (atualizacao != null) {
			if (!verificador.verificar(atualizacao.getTipo().toString())) {
				doc.setTipo(atualizacao.getTipo());
			}
			if (!verificador.verificar(atualizacao.getNumero())) {
				doc.setNumero(atualizacao.getNumero());
			}
		}
	}

	public void atualizar(List<Doc> docs, List<Doc> atualizacoes) {
		for (Doc atualizacao : atualizacoes) {
			for (Doc doc : docs) {
				if (atualizacao.getId() != null) {
					if (atualizacao.getId() == doc.getId()) {
						atualizar(doc, atualizacao);
					}
				}
			}
		}
	}
}
