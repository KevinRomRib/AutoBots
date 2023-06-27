package com.autobots.automanager.updater;

import java.util.Set;

import com.autobots.automanager.entity.Service;
import com.autobots.automanager.model.VerificadSeNulo;

public class AtualizadorService {
	private VerificadSeNulo verificador = new VerificadSeNulo();

	public void atualizar(Service service, Service atualizacao) {
		if (atualizacao != null) {
			if (!verificador.verificar(atualizacao.getNome())) {
				service.setNome(atualizacao.getNome());
			}
			if (!verificador.verificar(atualizacao.getValor())) {
				service.setValor(atualizacao.getValor());
			}
			if (!verificador.verificar(atualizacao.getDescricao())) {
				service.setDescricao(atualizacao.getDescricao());
			}
			
			
			
		}
	}

	public void atualizar(Set<Service> services, Set<Service> atualizacoes) {
		for (Service atualizacao : atualizacoes) {
			for (Service service : services) {
				if (atualizacao.getId() != null) {
					if (atualizacao.getId() == service.getId()) {
						atualizar(service, atualizacao);
					}
				}
			}
		}
	}
}