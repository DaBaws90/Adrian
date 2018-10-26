package com.example.Adrian.converter;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.example.Adrian.entity.Coche;
import com.example.Adrian.model.CocheModel;

@Component("cocheConverter")
public class CocheConverter {
	
	private DozerBeanMapper dozer = new DozerBeanMapper();
	
	public CocheModel entidadModelo(Coche coche) {
		return dozer.map(coche, CocheModel.class);
	}
	
	public Coche modeloEntidad(CocheModel cocheModel) {
		return dozer.map(cocheModel, Coche.class);
	}
}
