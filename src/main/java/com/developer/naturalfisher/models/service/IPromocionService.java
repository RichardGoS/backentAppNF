package com.developer.naturalfisher.models.service;

import java.util.List;

import com.developer.naturalfisher.models.entity.Promocion;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 07/04/2022
 */

public interface IPromocionService {
	
	public List<Promocion> findAll();
	public List<Promocion> findAll_estadoActivo();
	public Promocion save(Promocion promocionNew);
	public boolean delete(Promocion promocion);

}
