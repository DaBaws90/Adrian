package com.example.Adrian.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ParticipacionModel {

	private int id;
	
	@NotNull
	private CocheModel cocheModel;
	
	@NotNull
	private CarreraModel carreraModel;

	@NotNull
	@Min(1)
	private Integer posicion;

	public ParticipacionModel() {
		super();
	}

	public ParticipacionModel(int id, CocheModel coche, CarreraModel carrera, int posicion) {
		super();
		this.id = id;
		this.cocheModel = coche;
		this.carreraModel = carrera;
		this.posicion = posicion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CocheModel getCoche() {
		return cocheModel;
	}

	public void setCoche(CocheModel cocheModel) {
		this.cocheModel = cocheModel;
	}

	public CarreraModel getCarrera() {
		return carreraModel;
	}

	public void setCarrera(CarreraModel carreraModel) {
		this.carreraModel = carreraModel;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	@Override
	public String toString() {
		return "ParticipacionModel [id=" + id + ", cocheModel=" + cocheModel + ", carreraModel=" + carreraModel + ", posicion=" + posicion
				+ "]";
	}
}
