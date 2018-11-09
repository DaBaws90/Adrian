package com.example.Adrian.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Adrian.entity.Carrera;

@Repository("carreraJpaRepository")
public interface CarreraJpaRepository extends JpaRepository<Carrera, Serializable>{
	public abstract Carrera findById(int id);

}
