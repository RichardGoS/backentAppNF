package com.developer.naturalfisher.models.service;

import java.util.Date;
import java.util.List;

import com.developer.naturalfisher.models.entity.ItemInversion;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 10/02/2022
 */

public interface IItemInversionService {
	
	public ItemInversion save(ItemInversion item);
	public List<ItemInversion> obtenerItemsInversionesPorProductoMayorAFechaInventario(Long producto_id, Date fecha_inventario);

}
