package com.example.Adrian.converter;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.example.Adrian.entity.Carrera;
import com.example.Adrian.model.CarreraModel;

@Component("carreraConverter")
public class CarreraConverter {
	
	private DozerBeanMapper dozer = new DozerBeanMapper();
	
	public CarreraModel entidadModelo(Carrera carrera) {
		return dozer.map(carrera, CarreraModel.class);
	}
	
	public Carrera modeloEntidad(CarreraModel carreraModel) {
		return dozer.map(carreraModel, Carrera.class);
	}

}
