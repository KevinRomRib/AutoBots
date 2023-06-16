package com.autobots.automanager.model;

import com.autobots.automanager.controller.TelControle;
import com.autobots.automanager.entity.Tel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TelAddLink implements AddLink<Tel> {

    @Override
    public void addLink(List<Tel> lista) {
        for (Tel tel : lista) {
            long id = tel.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(TelControle.class)
                            .buscarTelPorId(id))
                    .withSelfRel();
            tel.add(linkProprio);
        }
    }

    @Override
    public void addLink(Tel objeto) {
        Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(TelControle.class)
                        .buscarTel())
                .withRel("telefones");
        objeto.add(linkProprio);
    }

}
