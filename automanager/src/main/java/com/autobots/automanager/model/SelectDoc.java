package com.autobots.automanager.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entity.Doc;

@Component
public class SelectDoc {
	public Doc selecionar(List<Doc> docs, long id) {
		Doc selecionado = null;
		for (Doc doc : docs) {
			if (doc.getId() == id) {
				selecionado = doc;
			}
		}
		return selecionado;
	}
}