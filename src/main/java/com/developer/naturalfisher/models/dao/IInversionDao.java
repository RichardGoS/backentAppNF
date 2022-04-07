package com.developer.naturalfisher.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.developer.naturalfisher.models.entity.Inversion;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 30/01/2022
 */

public interface IInversionDao extends JpaRepository<Inversion, Long> {
	
	//SELECT * FROM `inversion` WHERE  fecha >= '2021-12-11 00:00:00'
	@Query(nativeQuery = false,value = " SELECT i FROM Inversion i WHERE fecha >= ?1")
	List<Inversion> buscarUltimaFechaInventariada(Date fecha);
	
	@Procedure(name = "obtenerInversionesPorProducto")
	String obtenerInversionesPorProducto(@Param("product_id") Long product_id);
	
	//SELECT * FROM `inversion` WHERE  producto_id = 2

}
