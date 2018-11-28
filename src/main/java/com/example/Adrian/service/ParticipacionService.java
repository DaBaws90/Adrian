package com.example.Adrian.service;

import java.util.List;

import com.example.Adrian.entity.Participacion;
import com.example.Adrian.model.ParticipacionModel;

public interface ParticipacionService {
	
	public abstract List<ParticipacionModel> listAllParticipaciones();
	public abstract Participacion addParticipacion(ParticipacionModel participacionModel);
	public abstract Participacion updateParticipacion(ParticipacionModel participacionModel);
	public abstract boolean deleteParticipacion(int id);
	public abstract ParticipacionModel findById(int id);
	public abstract ParticipacionModel checkCoincidences(ParticipacionModel participacionModel);
	public abstract boolean editable(ParticipacionModel participacionModel);
}
