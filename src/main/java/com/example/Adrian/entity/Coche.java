package com.example.Adrian.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="coches")
public class Coche {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="matricula")
	private String matricula;
	
	@Column(name="marca")
	private String marca;
	
	@Column(name="modelo")
	private String modelo;
	
	@Column(name="color")
	private String color;
	
	@Column(name="potencia")
	//@Digits(integer=4, fraction=2)
	private int potencia;
	
	@Column(name="foto")
	private String foto;
	
	@OneToMany(mappedBy="coche", cascade = CascadeType.REMOVE)
	private List<Participacion> participaciones = new ArrayList<Participacion>();
	
	public Coche() {
		
	}

	public Coche(int id, String matricula, String marca, String modelo, String color, int potencia, String foto,
			List<Participacion> participaciones) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.potencia = potencia;
		//this.foto = *relativeRoute*+foto;
		this.foto = foto;
		this.participaciones = participaciones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public String getFoto() {
		//return *relativeRoute*+foto; (Puede ser innecesario si ya contiene la ruta en el momento de asignarle el valor al atributo)
		return foto;
	}

	public void setFoto(String foto) {
		//this.foto = *relativeRoute*+foto;
		this.foto = foto;
	}
	
	public List<Participacion> getParticipaciones(){
		return participaciones;
	}
	
	public void setParticipaciones(List<Participacion> participaciones) {
		this.participaciones = participaciones;
	}
	
	/*public void addParticipacion(Participacion participacion) {
		this.participaciones.add(participacion);
	}*/

	@Override
	public String toString() {
		return "Coche [id=" + id + ", matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", color="
				+ color + ", potencia=" + potencia + ", foto=" + foto + "]";
	}
	
}
