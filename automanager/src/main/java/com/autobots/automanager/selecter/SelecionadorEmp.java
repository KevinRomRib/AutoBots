package com.autobots.automanager.selecter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entity.Emp;

@Component
public class SelecionadorEmp {
	public Emp selecionar(List<Emp> emps, long id) {
		Emp selecionado = null;
		for (Emp emp : emps) {
			if (emp.getId() == id) {
				selecionado = emp;
			}
		}
		return selecionado;
	}
}