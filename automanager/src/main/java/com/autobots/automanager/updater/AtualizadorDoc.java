package com.autobots.automanager.updater;

import java.util.Set;

import com.autobots.automanager.entity.Doc;
import com.autobots.automanager.model.VerificadSeNulo;

public class AtualizadorDoc {
	private VerificadSeNulo verificador = new VerificadSeNulo();

	public void atualizar(Doc doc, Doc atualizacao) {
		if (atualizacao != null) {
			if (!verificador.verificarDocumento(atualizacao.getTipo())) {
				doc.setTipo(atualizacao.getTipo());
			}
			if (!verificador.verificar(atualizacao.getNumero())) {
				doc.setNumero(atualizacao.getNumero());
			}
		}
	}

	public void atualizar(Set<Doc> docs, Set<Doc> atualizacoes) {
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
