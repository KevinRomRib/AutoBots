package com.autobots.automanager.entitades;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
public class Telefone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String ddd;
	@Column(nullable = false)
	private String numero;
}