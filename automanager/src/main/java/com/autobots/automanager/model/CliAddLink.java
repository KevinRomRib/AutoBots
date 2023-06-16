package com.autobots.automanager.model;

import com.autobots.automanager.controller.CliControle;
import com.autobots.automanager.entity.Client;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CliAddLink implements AddLink<Client> {

    @Override
    public void addLink(List<Client> lista) {
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
    public void addLink(Client objeto) {
        Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(CliControle.class)
                        .obterCli())
                .withRel("clientes");
        objeto.add(linkProprio);
    }
}