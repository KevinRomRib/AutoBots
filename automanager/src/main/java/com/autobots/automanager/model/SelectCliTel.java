package com.autobots.automanager.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entity.Client;
import com.autobots.automanager.entity.Tel;

@Component
public class SelectCliTel {
	public Client selecionar(List<Client> clients, Tel telefone) {
		Client selecionado = null;
		for (Client client : clients) {
			for (Tel tel : client.getTels()) {
				if (tel.getId() == telefone.getId()) {
					selecionado = client;
				}
			}
		}
		return selecionado;
	}

}

	
