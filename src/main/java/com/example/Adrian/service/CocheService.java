package com.example.Adrian.service;

import java.util.List;

import com.example.Adrian.entity.Coche;
import com.example.Adrian.model.CocheModel;

public interface CocheService {
	
	public abstract List<CocheModel> listAllCoches();
	public abstract Coche addCoche(CocheModel cocheModel);
	public abstract Coche updateCoche(CocheModel cocheModel);
	public abstract boolean deleteCoche(String matricula);
	public abstract CocheModel findByMatricula(String mat);
}
