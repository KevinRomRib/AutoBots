package com.autobots.automanager.selecter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entity.End;

@Component
public class SelecionadorEnd {
	public End selecionar(List<End> ends, long id) {
		End selecionado = null;
		for (End end : ends) {
			if (end.getId() == id) {
				selecionado = end;
			}
		}
		return selecionado;
	}
}