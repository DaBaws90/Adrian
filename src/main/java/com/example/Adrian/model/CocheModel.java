package com.example.Adrian.model;

public class CocheModel {
	
	private int id;
	private String matricula;
	private String marca;
	private String modelo;
	private String color;
	private int potencia;
	private String foto;
	
	public CocheModel() {
		
	}

	public CocheModel(String matricula, String marca, String modelo, String color, int potencia, String foto) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.potencia = potencia;
		this.foto = foto;
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

	@Override
	public String toString() {
		return "CocheModel [matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", color=" + color
				+ ", potencia=" + potencia + ", foto=" + foto + "]";
	}
	
	
	

}
