package com.autobots.clientes.model;


import com.autobots.clientes.controller.ClientController;
import com.autobots.clientes.entity.Client;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddLinkCliente implements AddLink<Client> {

    @Override
    public void adicionarLink(List<Client> lista) {
        for (Client client : lista) {
            long id = client.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(ClientController.class)
                            .obterCli(id))
                    .withSelfRel();
            client.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(Client objeto) {
        Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(ClientController.class)
                        .obterCli())
                .withRel("clientes");
        objeto.add(linkProprio);
    }
}