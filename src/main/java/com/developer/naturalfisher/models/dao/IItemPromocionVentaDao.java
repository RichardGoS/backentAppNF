package com.developer.naturalfisher.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.developer.naturalfisher.models.entity.ItemPromocionVenta;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 10/04/2022
 */

public interface IItemPromocionVentaDao extends JpaRepository<ItemPromocionVenta, Long> {
	
	@Procedure(name = "obtenerItemsPromocionVentasPorProducto")
	String obtenerItemsPromocionVentasPorProducto(@Param("product_id") Long product_id);

}
