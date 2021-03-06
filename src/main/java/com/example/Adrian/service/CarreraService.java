package com.example.Adrian.service;

import java.util.List;

import com.example.Adrian.entity.Carrera;
import com.example.Adrian.model.CarreraModel;

public interface CarreraService {
	public abstract List<CarreraModel> listAllCarreras();
	public abstract Carrera addCarrera(CarreraModel carreraModel);
	public abstract Carrera updateCarrera(CarreraModel carreraModel);
	public abstract boolean deleteCarrera(int id);
	public abstract CarreraModel findById(int id);

}
