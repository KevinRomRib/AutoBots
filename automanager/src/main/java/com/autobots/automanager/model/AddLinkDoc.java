package com.autobots.automanager.model;

import com.autobots.automanager.controller.DocControle;
import com.autobots.automanager.entity.Doc;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddLinkDoc implements AddLink<Doc> {
    @Override
    public void adicionarLink(List<Doc> lista) {
        for (Doc doc : lista) {
            long id = doc.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(DocControle.class)
                            .buscarDocPorId(id))
                    .withSelfRel();
            doc.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(Doc objeto) {
        Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(DocControle.class)
                        .buscarDoc())
                .withRel("documentos");
        objeto.add(linkProprio);
    }
}
