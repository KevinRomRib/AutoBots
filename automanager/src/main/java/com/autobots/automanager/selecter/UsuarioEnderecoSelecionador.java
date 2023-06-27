package com.autobots.automanager.selecter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entity.User;
import com.autobots.automanager.entity.End;

@Component
public class UsuarioEnderecoSelecionador {
	public User selecionar(List<User> users, End endereco) {
		User selecionado = null;
		for (User user : users) {
			End end = user.getEnd();
			if (end.getId() == endereco.getId()) {
				selecionado = user;
			}
		}
		return selecionado;
	}
}

