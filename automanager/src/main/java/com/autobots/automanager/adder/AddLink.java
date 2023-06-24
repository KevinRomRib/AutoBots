package com.autobots.automanager.adder;

import java.util.List;

import com.autobots.automanager.entity.Client;


public interface AddLink<T> {
	public void addLink(List<T> lista);
	public void addLink(T objeto);
	public void addLink(T objeto, Client client);
	
}