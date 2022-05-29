package com.developer.naturalfisher.models.service;

import java.util.List;

import com.developer.naturalfisher.models.entity.PromocionVenta;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 10/04/2022
 */

public interface IPromocionVentaService {
	
	public PromocionVenta save(PromocionVenta promocionVentaNew);
	public List<PromocionVenta> findByIdPromocion(Long idPromocion);

}
