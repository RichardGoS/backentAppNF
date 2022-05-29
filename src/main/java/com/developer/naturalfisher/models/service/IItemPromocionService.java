package com.developer.naturalfisher.models.service;

import com.developer.naturalfisher.models.entity.ItemPromocion;

/**
* de RagooS
* Autor: Richard Gomez O.
* Para: EmpresaDevelopers.Backend.NaturalFisher
* Fecha: 08/04/2022
*/
public interface IItemPromocionService {
	
	public ItemPromocion save(ItemPromocion itemNuevo);
	public boolean delete(ItemPromocion itemDelete);

}
