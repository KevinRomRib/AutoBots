package com.autobots.automanager.updater;

import java.util.List;

import com.autobots.automanager.entity.Veiculo;
import com.autobots.automanager.model.VerificadSeNulo;

public class AtualizadorVeiculo {
	private VerificadSeNulo verificador = new VerificadSeNulo();
	private static AtualizadorVenda atualizadorVenda = new AtualizadorVenda();
	
	public void atualizar(Veiculo veiculo, Veiculo atualizacao) {
		if (atualizacao != null) {
			if (!verificador.verificar(atualizacao.getTipo())) {
				veiculo.setTipo(atualizacao.getTipo());
			}
			if (!verificador.verificar(atualizacao.getModelo())) {
				veiculo.setModelo(atualizacao.getModelo());
			}
			if (!verificador.verificar(atualizacao.getPlaca())) {
				veiculo.setPlaca(atualizacao.getPlaca());
			}
			if (!verificador.verificar(atualizacao.getProprietario())) {
				veiculo.setProprietario(atualizacao.getProprietario());
			}
			
			
			
		}
	}

	public void atualizar(List<Veiculo> veiculos, List<Veiculo> atualizacoes) {
		for (Veiculo atualizacao : atualizacoes) {
			for (Veiculo veiculo : veiculos) {
				if (atualizacao.getId() != null) {
					if (atualizacao.getId() == veiculo.getId()) {
						atualizar(veiculo, atualizacao);
						atualizadorVenda.atualizar(veiculo.getVendas(), atualizacao.getVendas());
						
					}
				}
			}
		}
	}
}