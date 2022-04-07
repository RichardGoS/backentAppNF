package com.developer.naturalfisher.models.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.developer.naturalfisher.models.entity.ItemInversion;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 10/02/2022
 */

public interface IItemInversionDao extends JpaRepository<ItemInversion, Long> {
	
	@Procedure(name = "obtenerItemsInversionesPorProductoMayorAFechaInventario")
	String obtenerItemsInversionesPorProductoMayorAFechaInventario(@Param("product_id") Long product_id, @Param("fecha_inventario") Date fecha_inventario);

}
