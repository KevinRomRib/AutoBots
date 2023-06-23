package com.autobots.automanager.model;

import com.autobots.automanager.controller.TelControle;
import com.autobots.automanager.entity.Telefone;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkTelefone implements AdicionadorLink<Telefone>{

    @Override
    public void adicionarLink(List<Telefone> lista) {
        for (Telefone telefone : lista) {
            long id = telefone.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(TelControle.class)
                            .buscarTelPorId(id))
                    .withSelfRel();
            telefone.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(Telefone objeto) {
        Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(TelControle.class)
                        .buscarTel())
                .withRel("telefones");
        objeto.add(linkProprio);
    }

}
