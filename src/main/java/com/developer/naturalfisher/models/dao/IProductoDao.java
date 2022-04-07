package com.developer.naturalfisher.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.developer.naturalfisher.models.entity.Producto;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 11/06/2021
 */

public interface IProductoDao extends JpaRepository<Producto, Long> {
	
	@Query(nativeQuery = false, value = " SELECT v FROM Producto v WHERE estado = ?1 ORDER BY nombre")
	List<Producto> findAll_Estado(String estado);
	
	@Query(nativeQuery = false, value = " SELECT v FROM Producto v WHERE codigo = ?1 ")
	Producto findByCodigo(String estado);
	
	@Query(nativeQuery = false, value = " SELECT v FROM Producto v WHERE realiza_inventario = ?1")
	List<Producto> findAll_EstadoInventario(String estado);

}
