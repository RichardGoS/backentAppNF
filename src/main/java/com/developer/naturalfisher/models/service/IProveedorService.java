package com.developer.naturalfisher.models.service;

import java.util.List;

import com.developer.naturalfisher.models.entity.Proveedor;

/**
 * Fase 4 Tarea 3
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 12/09/2022
 */
public interface IProveedorService {
	
	public List<Proveedor> findAll();
	public List<Proveedor> findAll_estadoActivo();
	public Proveedor save(Proveedor proveedor);
	public boolean delete(Proveedor proveedor);

}
