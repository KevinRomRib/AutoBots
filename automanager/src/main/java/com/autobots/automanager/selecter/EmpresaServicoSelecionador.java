package com.autobots.automanager.selecter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.entity.Service;

@Component
public class EmpresaServicoSelecionador {
	public Emp selecionar(List<Emp> emps, Service service) {
		Emp selecionado = null;
		for (Emp emp : emps) {
			for (Service ser : emp.getServices()) {
				if (ser.getId() ==  service.getId()) {
					selecionado = emp;
				}
			}
		}
		return selecionado;
	}
}
