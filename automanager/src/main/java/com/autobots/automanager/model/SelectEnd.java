package com.autobots.automanager.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entity.End;

@Component
public class SelectEnd {
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