package com.autobots.automanager.selecter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entity.User;
import com.autobots.automanager.entity.Veiculo;

@Component
public class UsuarioVeiculoSelecionador {
	public User selecionar(List<User> users, Veiculo veiculo) {
		User selecionado = null;
		for (User user : users) {
			for (Veiculo vei : user.getVeiculos()) {
				if (vei.getId() == veiculo.getId()) {
					selecionado = user;
				}
			}
		}
		return selecionado;
	}
}
