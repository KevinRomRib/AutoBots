package com.autobots.automanager.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entity.Client;
import com.autobots.automanager.entity.End;

@Component
public class SelectCliEnd {
	public Client selecionar(List<Client> clients, End endereco) {
		Client selecionado = null;
		for (Client client : clients) {
			End end = client.getEnd();
			if (end.getId() == endereco.getId()) {
				selecionado = client;
			}
		}
		return selecionado;
	}
}
