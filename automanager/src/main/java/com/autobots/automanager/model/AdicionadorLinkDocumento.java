package com.autobots.automanager.model;

import com.autobots.automanager.controller.DocControle;
import com.autobots.automanager.entity.Documento;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkDocumento implements AdicionadorLink<Documento> {
    @Override
    public void adicionarLink(List<Documento> lista) {
        for (Documento documento : lista) {
            long id = documento.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(DocControle.class)
                            .buscarDocPorId(id))
                    .withSelfRel();
            documento.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(Documento objeto) {
        Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(DocControle.class)
                        .buscarDoc())
                .withRel("documentos");
        objeto.add(linkProprio);
    }
}
