package com.example.Adrian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="participaciones")
public class Participacion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="posicion")
	private int posicion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idcoche")
	private Coche coche;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idcarrera")//nombre en minusculas para evitar cambiar "naming strategy" en application.properties
	private Carrera carrera;

	public Participacion() {
		super();
	}

	public Participacion(int id, int posicion, Coche coche, Carrera carrera) {
		super();
		this.id = id;
		this.posicion = posicion;
		this.coche = coche;
		this.carrera = carrera;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	@Override
	public String toString() {
		return "Participacion [id=" + id + ", posicion=" + posicion + ", coche=" + coche + ", carrera=" + carrera + "]";
	}
	

}
