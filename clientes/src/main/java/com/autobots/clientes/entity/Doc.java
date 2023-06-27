package com.autobots.clientes.entity;


import com.autobots.clientes.enums.TipoDocumento;
import jakarta.persistence.*;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Data
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