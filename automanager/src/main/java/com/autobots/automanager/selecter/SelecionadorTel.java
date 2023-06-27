package com.autobots.automanager.selecter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entity.Tel;

@Component
public class SelecionadorTel {
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