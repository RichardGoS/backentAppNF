package com.developer.naturalfisher.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.developer.naturalfisher.models.entity.Inventario;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 02/02/2022
 */

public interface IInventarioDao extends JpaRepository<Inventario, Long> {
	
	//SELECT * FROM inventario WHERE id = ( select max(id) FROM inventario where producto_id = 1)
	@Query(nativeQuery = false,value = " SELECT i FROM Inventario i WHERE id = ( SELECT MAX(id) FROM Inventario WHERE producto_id = ?1)")
	Inventario seleccionarUtimoInventarioProducto(Long id);

}
