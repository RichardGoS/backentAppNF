package com.developer.naturalfisher.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.developer.naturalfisher.models.entity.PromocionVenta;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 10/04/2022
 */

public interface IPromocionVentaDao extends JpaRepository<PromocionVenta, Long> {
	
	@Query(nativeQuery = false,value = " SELECT p FROM PromocionVenta p WHERE promocion_id = ?1")
	List<PromocionVenta> buscarPromocionVentaIdPromocion(Long idPromocion);

}
