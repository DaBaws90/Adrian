package com.example.Adrian.converter;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.Adrian.entity.Participacion;
import com.example.Adrian.model.ParticipacionModel;

@Component("participacionConverter")
public class ParticipacionConverter {
	
	private DozerBeanMapper dozer = new DozerBeanMapper();
	@Autowired
	@Qualifier("cocheConverter")
	private CocheConverter cocheConverter;
	
	@Autowired
	@Qualifier("carreraConverter")
	private CarreraConverter carreraConverter;
	
	public ParticipacionModel entidadModelo(Participacion participacion) {
		//convertir los coches y las carreras, no basta con pasar de entidad participacion a modelo
		return dozer.map(participacion, ParticipacionModel.class);
		/*ParticipacionModel participacionModel = new ParticipacionModel();
		participacionModel.setId(participacion.getId());
		participacionModel.setPosicion(participacion.getPosicion());
		participacionModel.setCoche(cocheConverter.entidadModelo(participacion.getCoche()));
		return null;*/
	}
	
	public Participacion modeloEntidad(ParticipacionModel participacionModel) {
		return dozer.map(participacionModel, Participacion.class);
	}
	

}
