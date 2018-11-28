package com.example.Adrian.model;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CarreraModel {
	
	private int id;
	
	@NotNull
	@NotEmpty
	@Size(min=1, max=50)
	private String nombre;
	
	@NotNull
	//@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fecha;

	@NotNull
	@NotEmpty
	@Size(min=1, max=50)
	private String ciudad;
	
	private List<ParticipacionModel> participaciones;

	public CarreraModel() {
		super();
	}

	public CarreraModel(int id, String nombre, Date fecha,
			String ciudad, List<ParticipacionModel> participaciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha = fecha;
		this.ciudad = ciudad;
		this.participaciones = participaciones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public List<ParticipacionModel> getParticipaciones() {
		return participaciones;
	}

	public void setParticipaciones(List<ParticipacionModel> participaciones) {
		this.participaciones = participaciones;
	}

	@Override
	public String toString() {
		return "CarreraModel [id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + ", ciudad=" + ciudad + "]";
	}


}
