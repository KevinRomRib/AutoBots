package com.autobots.automanager.selecter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entity.User;
import com.autobots.automanager.entity.Tel;

@Component
public class UsuarioTelefoneSelecionador {
	public User selecionar(List<User> users, Tel telefone) {
		User selecionado = null;
		for (User user : users) {
			for (Tel tel : user.getTels()) {
				if (tel.getId() == telefone.getId()) {
					selecionado = user;
				}
			}
		}
		return selecionado;
	}

}

