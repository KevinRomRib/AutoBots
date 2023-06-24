package com.autobots.automanager.adder;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controller.DocControle;
import com.autobots.automanager.entity.Client;
import com.autobots.automanager.entity.Doc;

@Component
public class AddLinkDoc implements AddLink<Doc> {

	@Override
	public void addLink(List<Doc> lista) {
		for (Doc doc : lista) {
			long id = doc.getId();
			Link linkProprioObterDocumento = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(DocControle.class)
							.obterDoc(id))
					.withSelfRel();
			doc.add(linkProprioObterDocumento);
			
			Link linkProprioObterDocumentos = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(DocControle.class)
							.obterDoc())
					.withSelfRel();
			doc.add(linkProprioObterDocumentos);
					
			Link linkProprioAtualizarDocumento = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(DocControle.class)
							.updateDoc(doc))
					.withSelfRel();
			doc.add(linkProprioAtualizarDocumento);
		}
	}

	@Override
	public void addLink(Doc objeto) {
		Link linkProprioObterDocumento = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(DocControle.class)
						.obterDoc(objeto.getId()))
				.withRel("documento");
		objeto.add(linkProprioObterDocumento);
		
		Link linkProprioObterDocumentos = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(DocControle.class)
						.obterDoc())
				.withRel("documentos");
		objeto.add(linkProprioObterDocumentos);
				
		Link linkProprioAtualizarDocumento = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(DocControle.class)
						.updateDoc(objeto))
				.withRel("atualizarDocumento");
		objeto.add(linkProprioAtualizarDocumento);
		
	}
	
	@Override
	public void addLink(Doc objeto, Client client) {
		Link linkProprioExcluirDocumento = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(DocControle.class)
						.deleteDoc(objeto, client.getId()))
				.withRel("excluirDocumento");
		objeto.add(linkProprioExcluirDocumento);
		
	}

}
