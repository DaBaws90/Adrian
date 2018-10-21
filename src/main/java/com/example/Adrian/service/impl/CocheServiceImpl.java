package com.example.Adrian.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.Adrian.entity.Coche;
import com.example.Adrian.repository.CocheJpaRepository;
import com.example.Adrian.service.CocheService;

@Service("cocheServiceImpl")
public class CocheServiceImpl implements CocheService{
	
	@Autowired
	@Qualifier("cocheJpaRepository")
	private CocheJpaRepository cocheJpaRepository;
	
	@Autowired
	@Qualifier("dozer")
	private DozerBeanMapper dozer;
	
	public List<CocheModel> listAllCoches() {
		List<CocheModel> coches = new ArrayList<Coche>();
		for(Coche coche: cocheJpaRepository.findAll()) {
			//coches.add(dozer.map(coche, cocheModel));
		}
	}

}
