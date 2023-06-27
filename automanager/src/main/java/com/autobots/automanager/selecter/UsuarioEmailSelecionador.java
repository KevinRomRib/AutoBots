package com.autobots.automanager.selecter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entity.Email;
import com.autobots.automanager.entity.User;

@Component
public class UsuarioEmailSelecionador {
	public User selecionar(List<User> users, Email email) {
		User selecionado = null;
		for (User user : users) {
			for (Email e : user.getEmails()) {
				if (e.getId() == email.getId()) {
					selecionado = user;
				}
			}
		}
		return selecionado;
	}

}

