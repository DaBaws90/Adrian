package com.example.Adrian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="participaciones")
public class Participacion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="id_coche", updatable = false)
	@NotNull
	private int idCoche;
	
	@Column(name="id_carrera", updatable = false)
	@NotNull
	private int idCarrera;
	
	@NotNull
	private int posicion;
	
	
	private Coche coche;
	
	
	private Carrera carrera;
	

}
