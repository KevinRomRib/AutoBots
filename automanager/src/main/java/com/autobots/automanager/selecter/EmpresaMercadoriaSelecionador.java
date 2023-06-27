package com.autobots.automanager.selecter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.entity.Mercadoria;

@Component
public class EmpresaMercadoriaSelecionador {
	public Emp selecionar(List<Emp> emps, Mercadoria mercadoria) {
		Emp selecionado = null;
		for (Emp emp : emps) {
			for (Mercadoria mer : emp.getMercadorias()) {
				if (mer.getId() ==  mercadoria.getId()) {
					selecionado = emp;
				}
			}
		}
		return selecionado;
	}
}
