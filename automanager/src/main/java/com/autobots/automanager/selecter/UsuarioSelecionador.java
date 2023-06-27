package com.autobots.automanager.selecter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entity.User;

@Component
public class UsuarioSelecionador {
	public User selecionar(List<User> users, long id) {
		User selecionado = null;
		for (User user : users) {
			if (user.getId() == id) {
				selecionado = user;
			}
		}
		return selecionado;
	}
}