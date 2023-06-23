package com.autobots.automanager.model;

import com.autobots.automanager.controller.CliControle;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import com.autobots.automanager.entity.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkCliente implements AdicionadorLink<Cliente> {

    @Override
    public void adicionarLink(List<Cliente> lista) {
        for (Cliente cliente : lista) {
            long id = cliente.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(CliControle.class)
                            .obterCli(id))
                    .withSelfRel();
            cliente.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(Cliente objeto) {
        Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(CliControle.class)
                        .obterCli())
                .withRel("clientes");
        objeto.add(linkProprio);
    }
}