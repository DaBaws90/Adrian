package com.example.Adrian.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="carreras")
public class Carrera {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	@NotNull
	@Size(max=30)
	private String nombre;
	
	@Column(name="fecha", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="ciudad")
	@NotNull
	@Size(max=25)
	private String ciudad;
	
	@OneToMany(mappedBy="carrera", cascade = CascadeType.ALL)
	private List<Participacion> participaciones = new ArrayList<Participacion>();

	public Carrera() {
		super();
	}

	public Carrera(int id, String nombre, Date fecha, String ciudad, List<Participacion> participaciones) {
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

	public List<Participacion> getParticipaciones() {
		return participaciones;
	}

	public void setParticipaciones(List<Participacion> participaciones) {
		this.participaciones = participaciones;
	}

	@Override
	public String toString() {
		return "Carrera [id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + ", ciudad=" + ciudad
				+ ", participaciones=" + participaciones + "]";
	}

}
