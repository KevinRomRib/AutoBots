package com.autobots.automanager.model;

import com.autobots.automanager.entity.Tel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SelectTel {

    public Tel selecionar(List<Tel> tels, long id) {
        Tel selecionado = null;
        for (Tel tel : tels) {
            if (tel.getId() == id) {
                selecionado = tel;
            }
        }
        return selecionado;
    }
}
