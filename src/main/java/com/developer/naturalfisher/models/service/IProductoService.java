package com.developer.naturalfisher.models.service;

import java.util.List;

import com.developer.naturalfisher.models.entity.Producto;
import com.developer.naturalfisher.models.transporte.ProductosTransporte;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 11/06/2021
 */

public interface IProductoService {

	
	public Producto findById(Long id);
	public Producto findByCodigo(String codigo);
	public Producto save(Producto productoNew);
	public List<Producto> findAll_Activo();
	public boolean delete(Producto producto);
	public List<Producto> findAll_ActivoVenta();
	public ProductosTransporte findProductosGeneralActivos();
	public ProductosTransporte findAll_ActivoInversion();
	
	public List<Producto> findAll();
	
}
