package com.autobots.automanager.model;

import com.autobots.automanager.controller.EndControle;
import com.autobots.automanager.entity.Endereco;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkEndereco implements AdicionadorLink<Endereco> {
    @Override
    public void adicionarLink(List<Endereco> lista) {
        for (Endereco endereco : lista) {
            long id = endereco.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(EndControle.class)
                            .buscarEndPorId(id))
                    .withSelfRel();
            endereco.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(Endereco objeto) {
        Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(EndControle.class)
                        .buscarEnd())
                .withRel("enderecos");
        objeto.add(linkProprio);
    }
}
