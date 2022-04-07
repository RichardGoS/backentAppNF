package com.developer.naturalfisher.models.service;

import java.util.Date;
import java.util.List;

import com.developer.naturalfisher.models.entity.Inversion;
import com.developer.naturalfisher.models.entity.ItemInversion;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 30/01/2022
 */

public interface IInversionService {
	
	public List<Inversion> findAll();
	public List<Inversion> buscarUltimaFechaInventariada(Date fecha);
	public List<ItemInversion> findItemsByIdProducto(Long id);
	public Inversion save(Inversion inversion);
	public Inversion findById(Long id);

}
