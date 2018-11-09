package com.example.Adrian.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.Adrian.entity.Carrera;
import com.example.Adrian.entity.Coche;

public class ParticipacionModel {

	private int id;
	
	@NotNull
	@NotEmpty
	private Coche coche;
	
	@NotNull
	@NotEmpty
	private Carrera carrera;
	
	@NotNull
	@NotEmpty
	private int posicion;

	public ParticipacionModel() {
		super();
	}

	public ParticipacionModel(int id, Coche coche, Carrera carrera, int posicion) {
		super();
		this.id = id;
		this.coche = coche;
		this.carrera = carrera;
		this.posicion = posicion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	@Override
	public String toString() {
		return "ParticipacionModel [id=" + id + ", coche=" + coche + ", carrera=" + carrera + ", posicion=" + posicion
				+ "]";
	}
}
