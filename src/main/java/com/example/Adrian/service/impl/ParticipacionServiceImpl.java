package com.example.Adrian.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.Adrian.converter.ParticipacionConverter;
import com.example.Adrian.entity.Participacion;
import com.example.Adrian.model.ParticipacionModel;
import com.example.Adrian.repository.ParticipacionJpaRepository;
import com.example.Adrian.service.ParticipacionService;

@Service("participacionServiceImpl")
public class ParticipacionServiceImpl implements ParticipacionService{
	
	@Autowired
	@Qualifier("participacionJpaRepository")
	private ParticipacionJpaRepository participacionJpaRepository;
	
	@Autowired
	@Qualifier("participacionConverter")
	private ParticipacionConverter participacionConverter;
	
	public List<ParticipacionModel> listAllParticipaciones(){
		List<ParticipacionModel> participacionesModel = participacionJpaRepository.findAll().stream().map(p -> participacionConverter.entidadModelo(p)).collect(Collectors.toList());
		return participacionesModel;
	}
	
	public Participacion addParticipacion(ParticipacionModel participacionModel) {
		return participacionJpaRepository.save(participacionConverter.modeloEntidad(participacionModel));
	}
	
	public Participacion updateParticipacion(ParticipacionModel participacionModel) {
		return participacionJpaRepository.save(participacionConverter.modeloEntidad(participacionModel));
	}
	
	public boolean deleteParticipacion(int id) {
		if(participacionJpaRepository.findById(id) != null) {
			participacionJpaRepository.deleteById(id);
			return true;
		}
		else {
			return false;
		}
	}
	
	public ParticipacionModel findById(int id) {
		return participacionConverter.entidadModelo(participacionJpaRepository.findById(id));
	}
	
	public ParticipacionModel checkCoincidences(ParticipacionModel participacionModel) {
//		if(participacionModel)
//		ParticipacionModel par = listAllParticipaciones().stream().map(p -> checkCoincidences(p));
		for(ParticipacionModel par : listAllParticipaciones()) {
			if(participacionModel.getCoche().getId() == par.getCoche().getId() && participacionModel.getCarrera().getId() == par.getCarrera().getId()) {
				return par;
			}
		}
		return null;
//		if(participacionModel.getCoche().equals(obj))
//		Estaba intentando hacerlo recursivamente pero de aquí a que lo consiga, nos dan las uvas probablemente jaja
	}
	
	public boolean editable(ParticipacionModel participacionModel) {
		ParticipacionModel temp = checkCoincidences(participacionModel);
		if(temp == null) {
			return true;
		}
		
		return temp.getId() == participacionModel.getId();
	}
}
