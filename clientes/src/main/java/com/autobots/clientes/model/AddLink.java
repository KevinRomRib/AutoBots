package com.autobots.clientes.model;


import java.util.List;

public interface AddLink<T> {
    public void adicionarLink(List<T> list);
    public void adicionarLink(T objeto);

}
