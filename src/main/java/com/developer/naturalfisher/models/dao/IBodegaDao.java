package com.developer.naturalfisher.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.developer.naturalfisher.models.entity.Bodega;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 30/01/2022
 */

public interface IBodegaDao extends JpaRepository<Bodega, Long> {
	
	//SELECT * FROM bodega WHERE producto_id = 1
	@Query(nativeQuery = false,value = " SELECT v FROM Bodega v WHERE producto_id = ?1")
	Bodega findByIdProducto(Long id);

}
