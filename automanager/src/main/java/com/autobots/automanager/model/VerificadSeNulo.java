package com.autobots.automanager.model;

import java.util.Date;
import java.util.Set;

import com.autobots.automanager.entity.User;
import com.autobots.automanager.entity.Veiculo;

public class VerificadSeNulo {
	
	public boolean verificar(String dado) {
		boolean nulo = true;
		if (!(dado == null)) {
			if (!dado.isBlank()) {
				nulo = false;
			}
		}
		return nulo;
	}
	
	public boolean verificar(TipoVeiculo tipoVeiculo) {
		boolean nulo = true;
		if (!(tipoVeiculo == null)) {
			if (!(tipoVeiculo == null)) {
				nulo = false;
			}
		}
		return nulo;
	}
	
	public boolean verificarDocumento(TipoDocumento tipoDocumento) {
		boolean nulo = true;
		if (!(tipoDocumento == null)) {
				nulo = false;
		}
		return nulo;
	}

	public boolean verificarPerfil(Set<Perfil> perfis) {
		boolean nulo = true;
		if (!(perfis == null)) {
				nulo = false;
		}
		return nulo;
	}

	public boolean verificar(Date data) {
		boolean nulo = true;
		if (!(data == null)) {
				nulo = false;
		}
		return nulo;
	}

	public boolean verificar(long dado) {
		boolean nulo = true;
		if (!(dado == 0)) {
				nulo = false;
		}
		return nulo;
	}

	public boolean verificar(double dado) {
		boolean nulo = true;
		if (!(dado == 0)) {
				nulo = false;
		}
		return nulo;
	}

	public boolean verificar(User user) {
		boolean nulo = true;
		if (!(user == null)) {
				nulo = false;
		}
		return nulo;
	}

	public boolean verificar(Veiculo veiculo) {
		boolean nulo = true;
		if (!(veiculo == null)) {
				nulo = false;
		}
		return nulo;
	}
}