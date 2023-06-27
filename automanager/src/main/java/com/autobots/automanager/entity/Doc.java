package com.autobots.automanager.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.hateoas.RepresentationModel;

import com.autobots.automanager.enums.TipoDocumento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Doc extends RepresentationModel<Doc> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private TipoDocumento tipo;
	@Column(nullable = false)
	private Date dataEmissao;
	@Column(unique = true, nullable = false)
	private String numero;
}