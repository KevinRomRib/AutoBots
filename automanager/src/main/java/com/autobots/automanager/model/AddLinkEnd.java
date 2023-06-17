package com.autobots.automanager.model;

import com.autobots.automanager.controller.EndControle;
import com.autobots.automanager.entity.End;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddLinkEnd implements AddLink<End> {
    @Override
    public void adicionarLink(List<End> lista) {
        for (End end : lista) {
            long id = end.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(EndControle.class)
                            .buscarEndPorId(id))
                    .withSelfRel();
            end.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(End objeto) {
        Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(EndControle.class)
                        .buscarEnd())
                .withRel("enderecos");
        objeto.add(linkProprio);
    }
}
