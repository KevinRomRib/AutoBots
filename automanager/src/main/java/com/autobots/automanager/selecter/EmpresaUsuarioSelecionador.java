package com.autobots.automanager.selecter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.entity.User;

@Component
public class EmpresaUsuarioSelecionador {
	public Emp selecionar(List<Emp> emps, User user) {
		Emp selecionado = null;
		for (Emp emp : emps) {
			for (User usu: emp.getUsers()) {
				if (usu.getId() ==  user.getId()) {
					selecionado = emp;
				}
			}
		}
		return selecionado;
	}
}
