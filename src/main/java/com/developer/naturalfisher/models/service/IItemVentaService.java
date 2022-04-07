package com.developer.naturalfisher.models.service;

import java.util.Date;
import java.util.List;

import com.developer.naturalfisher.models.entity.ItemVenta;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 22/06/2021
 */

public interface IItemVentaService {
	
	public ItemVenta save(ItemVenta itemNew);
	public List<ItemVenta> findItemsVentasPorProducto(Long id);
	public List<ItemVenta> findItemsVentasMayorFechaPorProducto(Long id, Date fecha);
}
