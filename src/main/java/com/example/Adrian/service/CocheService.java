package com.example.Adrian.service;

import java.util.List;

import com.example.Adrian.entity.Coche;

public interface CocheService {
	
	public abstract List<Coche> listAllCoches();
	public abstract Coche addCoche(CocheModel cocheModel);
	public abstract Coche updateCoche(CocheModel cocheModel);
	public abstract void deleteCoche(CocheModel cocheModel);
}
