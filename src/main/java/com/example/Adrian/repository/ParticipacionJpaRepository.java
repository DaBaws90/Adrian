package com.example.Adrian.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Adrian.entity.Participacion;

@Repository("participacionJpaRepository")
public interface ParticipacionJpaRepository extends JpaRepository<Participacion, Serializable>{
	public abstract Participacion findById(int id);
}
