package com.example.Adrian.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CocheModel {
	
	@NotEmpty
	@Pattern(regexp="^[a-zA-Z]{3}[0-9]{4}", message="Formato permitido: 3 letras y 4 dígitos")
	//@Size(min=7, max=7)
	private String matricula;
	
	@NotNull
	@NotEmpty
	@Size(min=1, max=20)
	private String marca;
	
	@NotNull
	@NotEmpty
	@Size(min=1, max=20)
	private String modelo;
	
	@NotNull
	@NotEmpty
	@Size(min=1, max=20)
	private String color;
	
	private int potencia;
	
	private String foto;
	
	private List<ParticipacionModel> participaciones;
	
	public CocheModel() {
		
	}

	public CocheModel(String matricula, String marca, String modelo, String color, int potencia, String foto,
			List<ParticipacionModel> participaciones) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.potencia = potencia;
		this.foto = foto;
		this.participaciones = participaciones;
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
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<ParticipacionModel> getParticipaciones() {
		return participaciones;
	}

	public void setParticipaciones(List<ParticipacionModel> participaciones) {
		this.participaciones = participaciones;
	}

	@Override
	public String toString() {
		return "CocheModel [matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", color=" + color
				+ ", potencia=" + potencia + ", foto=" + foto + "]";
	}
	

}
