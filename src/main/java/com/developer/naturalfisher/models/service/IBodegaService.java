package com.developer.naturalfisher.models.service;

import java.util.List;

import com.developer.naturalfisher.models.entity.Bodega;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 30/01/2022
 */

public interface IBodegaService {
	
	public List<Bodega> findAll();
	
	public Bodega save(Bodega bodega);
	
	public Bodega findById(Long id);
	
	public Bodega findByIdProducto(Long id);

}
