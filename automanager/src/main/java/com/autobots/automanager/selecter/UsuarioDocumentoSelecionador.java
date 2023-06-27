package com.autobots.automanager.selecter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entity.User;
import com.autobots.automanager.entity.Doc;

@Component
public class UsuarioDocumentoSelecionador {
	public User selecionar(List<User> users, Doc documento) {
		User selecionado = null;
		for (User user : users) {
			for (Doc doc : user.getDocs()) {
				if (doc.getId() == documento.getId()) {
					selecionado = user;
				}
			}
		}
		return selecionado;
	}
}
