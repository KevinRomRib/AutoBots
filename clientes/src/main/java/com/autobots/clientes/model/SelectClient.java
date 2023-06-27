package com.autobots.clientes.model;

import java.util.List;

import com.autobots.clientes.entity.Client;
import org.springframework.stereotype.Component;


@Component
public class SelectClient {
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