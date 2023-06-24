package com.autobots.automanager.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entity.Client;
import com.autobots.automanager.entity.Doc;

@Component
public class SelectCliDoc {
	public Client selecionar(List<Client> clients, Doc documento) {
		Client selecionado = null;
		for (Client client : clients) {
			for (Doc doc : client.getDocs()) {
				if (doc.getId() == documento.getId()) {
					selecionado = client;
				}
			}
		}
		return selecionado;
	}
}
