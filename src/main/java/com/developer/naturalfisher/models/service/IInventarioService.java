package com.developer.naturalfisher.models.service;

import com.developer.naturalfisher.models.entity.Inventario;
import com.developer.naturalfisher.models.entity.ItemVenta;
import com.developer.naturalfisher.models.entity.Producto;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 03/02/2022
 */

public interface IInventarioService {
	
	public Inventario seleccionarUtimoInventarioProducto(Long id);
	public Inventario save(Inventario inventario);
	public Boolean realizarInventarioGeneral();
	boolean inventariar(Producto producto, ItemVenta itemVenta);

}
