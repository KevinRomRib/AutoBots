package com.autobots.automanager.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entity.Tel;

@Component
public class SelectTel {
	public Tel selecionar(List<Tel> tels, long id) {
		Tel selecionado = null;
		for (Tel tel : tels) {
			if (tel.getId() == id) {
				selecionado = tel;
			}
		}
		return selecionado;
	}
}