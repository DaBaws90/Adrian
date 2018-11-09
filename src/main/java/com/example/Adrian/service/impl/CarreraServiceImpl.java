package com.example.Adrian.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.Adrian.converter.CarreraConverter;
import com.example.Adrian.entity.Carrera;
import com.example.Adrian.model.CarreraModel;
import com.example.Adrian.repository.CarreraJpaRepository;
import com.example.Adrian.service.CarreraService;

@Service("carreraServiceImpl")
public class CarreraServiceImpl implements CarreraService{
	
	@Autowired
	@Qualifier("carreraJpaRepository")
	private CarreraJpaRepository carreraJpaRepository;
	
	@Autowired
	@Qualifier("carreraConverter")
	private CarreraConverter carreraConverter;

	@Override
	public List<CarreraModel> listAllCarreras() {
		List<CarreraModel> carreraModel = new ArrayList<CarreraModel>();
		carreraJpaRepository.findAll().forEach(c ->{
			carreraModel.add(carreraConverter.entidadModelo(c));
		});
		return carreraModel;
	}

	@Override
	public Carrera addCarrera(CarreraModel carreraModel) {
		return carreraJpaRepository.save(carreraConverter.modeloEntidad(carreraModel));
	}

	@Override
	public Carrera updateCarrera(CarreraModel carreraModel) {
		return carreraJpaRepository.save(carreraConverter.modeloEntidad(carreraModel));
	}

	@Override
	public boolean deleteCarrera(int id) {
		if(carreraJpaRepository.findById(id) != null) {
			carreraJpaRepository.deleteById(id);
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public CarreraModel findById(int id) {
		return carreraConverter.entidadModelo(carreraJpaRepository.findById(id));
	}

}
