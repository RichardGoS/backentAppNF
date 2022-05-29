package com.developer.naturalfisher.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.developer.naturalfisher.models.entity.Promocion;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 01/04/2022
 */

public interface IPromocionDao extends JpaRepository<Promocion, Long> {
	
	@Query(nativeQuery = false, value = " SELECT v FROM Promocion v WHERE estado = ?1 ORDER BY nombre")
	List<Promocion> findAll_estado(String estado);

}
