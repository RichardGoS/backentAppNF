package com.developer.naturalfisher.models.service;

import java.util.List;

import com.developer.naturalfisher.models.entity.ItemPromocionVenta;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 10/04/2022
 */

public interface IItemPromocionVentaService  {
	
	public ItemPromocionVenta save(ItemPromocionVenta itemPromocionVentaNew);
	
	public List<ItemPromocionVenta> obtenerItemsPromocionVentasPorProducto(Long idProducto); 

}
