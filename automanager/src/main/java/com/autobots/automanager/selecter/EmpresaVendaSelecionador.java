package com.autobots.automanager.selecter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.entity.Venda;

@Component
public class EmpresaVendaSelecionador {
	public Emp selecionar(List<Emp> emps, Venda venda) {
		Emp selecionado = null;
		for (Emp emp : emps) {
			for (Venda ven : emp.getVendas()) {
				if (ven.getId() ==  venda.getId()) {
					selecionado = emp;
				}
			}
		}
		return selecionado;
	}
}
