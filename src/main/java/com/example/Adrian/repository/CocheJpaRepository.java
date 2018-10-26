package com.example.Adrian.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Adrian.entity.Coche;

@Repository("cocheJpaRepository")
public interface CocheJpaRepository extends JpaRepository<Coche, Serializable>{
	public abstract Coche findByMatricula(String mat);

}
