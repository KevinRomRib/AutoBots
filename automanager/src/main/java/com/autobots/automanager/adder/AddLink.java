package com.autobots.automanager.adder;

import java.util.List;

import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.entity.Mercadoria;
import com.autobots.automanager.entity.User;


public interface AddLink<T> {
	public void adicionarLink(List<T> lista);
	public void adicionarLink(T objeto);
	public void adicionarLink(T objeto, User user);
	public void adicionarLink(T objeto, Emp emp);
	public void adicionarLink(T objeto, Mercadoria mercadoria);
	
}