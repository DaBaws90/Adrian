package com.example.Adrian.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Adrian.converter.CocheConverter;
import com.example.Adrian.entity.Coche;
import com.example.Adrian.model.CocheModel;
import com.example.Adrian.repository.CocheJpaRepository;
import com.example.Adrian.service.CocheService;

@Service("cocheServiceImpl")
public class CocheServiceImpl implements CocheService{
	
	@Autowired
	@Qualifier("cocheJpaRepository")
	private CocheJpaRepository cocheJpaRepository;
	
	@Autowired
	@Qualifier("cocheConverter")
	private CocheConverter cocheConverter;
	
	@Override
	public List<CocheModel> listAllCoches() {
		List<CocheModel> cocheModel = cocheJpaRepository.findAll().stream().map(c -> cocheConverter.entidadModelo(c)).collect(Collectors.toList());
		return cocheModel;
	}

	@Override
	public Coche addCoche(CocheModel cocheModel) {
		if(cocheJpaRepository.findByMatricula(cocheModel.getMatricula()) != null) {
			return null;
		}
		else {
			return cocheJpaRepository.save(cocheConverter.modeloEntidad(cocheModel));
		}
		//return cocheJpaRepository.save(cocheConverter.modeloEntidad(cocheModel));
	}

	@Override
	public Coche updateCoche(CocheModel cocheModel) {
		Coche coche = new Coche();
		coche = cocheJpaRepository.findByMatricula(cocheModel.getMatricula());
		if(coche != null) {
			coche.setMarca(cocheModel.getMarca());
			coche.setModelo(cocheModel.getModelo());
			coche.setColor(cocheModel.getColor());
			coche.setPotencia(cocheModel.getPotencia());
			coche.setFoto(cocheModel.getFoto());
			return cocheJpaRepository.save(coche);
		}
		else {
			return null;
		}
	}

	@Override
	public boolean deleteCoche(String matricula) {
		if(cocheJpaRepository.findByMatricula(matricula) != null) {
			cocheJpaRepository.delete(cocheJpaRepository.findByMatricula(matricula));
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public CocheModel findByMatricula(String matricula) {
		if(cocheJpaRepository.findByMatricula(matricula) == null) {
			return null;
		}
		else {
			return cocheConverter.entidadModelo(cocheJpaRepository.findByMatricula(matricula));
		}
	}
	
	@Override
	public String saveFile(MultipartFile img) throws IOException{
		if(!img.isEmpty()) {
			byte[] bytes = img.getBytes();
			String location = ".//src//main//resources//static//images//";
			Path path = Paths.get(location + img.getOriginalFilename());
			Files.write(path, bytes);
			//return location + img.getOriginalFilename();
			return img.getOriginalFilename();
		}
		return "";
	}
	
	@Override
	public void deleteFile(String foto) throws IOException{
		if(!foto.isEmpty()) {
			String location = ".//src//main//resources//static//images//";
			Path path = Paths.get(location + foto);
			Files.deleteIfExists(path);
		}
	}
}
