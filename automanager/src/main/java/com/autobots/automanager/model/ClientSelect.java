package com.autobots.automanager.model;

import java.util.List;

import com.autobots.automanager.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientSelect {
	public Client selecionar(List<Client> clients, long id) {
		Client selecionado = null;
		for (Client client : clients) {
			if (client.getId() == id) {
				selecionado = client;
			}
		}
		return selecionado;
	}
}