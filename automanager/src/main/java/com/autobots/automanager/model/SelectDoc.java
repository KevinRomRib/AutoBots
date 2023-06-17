package com.autobots.automanager.model;

import com.autobots.automanager.entity.Doc;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SelectDoc {

    public Doc selecionar(List<Doc> docs, long id) {
        Doc selecionado = null;
        for (Doc doc : docs) {
            if (doc.getId() == id) {
                selecionado = doc;
            }
        }
        return selecionado;
    }
}
