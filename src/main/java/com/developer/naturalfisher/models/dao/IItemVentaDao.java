package com.developer.naturalfisher.models.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.developer.naturalfisher.models.entity.ItemVenta;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 22/06/2021
 */

public interface IItemVentaDao extends JpaRepository<ItemVenta, Long>{
	
	@Procedure(name = "obtenerItemsVentasPorProducto")
	String obtenerItemsVentasPorProducto(@Param("product_id") Long product_id);
	
	@Procedure(name = "obtenerItemsVentasPorProductoMayorAFecha")
	String obtenerItemsVentasPorProductoMayorAFecha(@Param("product_id") Long product_id, @Param("fecha_inventario") Date fecha_inventario);
	
	@Procedure(name = "obtenerItemsVentasMayorAFechaConPromocionPorProducto")
	String obtenerItemsVentasMayorAFechaConPromocionPorProducto(@Param("fecha_parametro") Date fecha_parametro, @Param("product_id") Long id_producto);

}
