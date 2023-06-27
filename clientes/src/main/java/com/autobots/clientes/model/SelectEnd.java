package com.autobots.clientes.model;

import com.autobots.clientes.entity.End;
import org.springframework.stereotype.Component;

import java.util.List;

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
