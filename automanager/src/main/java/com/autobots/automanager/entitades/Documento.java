package com.autobots.automanager.entitades;

import java.util.Date;


import com.autobots.automanager.enumeracoes.TipoDocumento;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Documento {
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