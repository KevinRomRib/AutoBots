package com.autobots.automanager.model;

import com.autobots.automanager.controller.CliControle;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import com.autobots.automanager.entity.Client;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddLinkCli implements AddLink<Client> {

    @Override
    public void adicionarLink(List<Client> lista) {
        for (Client client : lista) {
            long id = client.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(CliControle.class)
                            .obterCli(id))
                    .withSelfRel();
            client.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(Client objeto) {
        Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(CliControle.class)
                        .obterCli())
                .withRel("clientes");
        objeto.add(linkProprio);
    }
}