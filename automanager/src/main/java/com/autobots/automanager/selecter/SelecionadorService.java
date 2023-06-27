package com.autobots.automanager.selecter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entity.Service;

@Component
public class SelecionadorService {
	public Service selecionar(List<Service> services, long id) {
		Service selecionado = null;
		for (Service service : services) {
			if (service.getId() == id) {
				selecionado = service;
			}
		}
		return selecionado;
	}
}