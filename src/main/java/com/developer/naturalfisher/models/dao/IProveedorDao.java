package com.developer.naturalfisher.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.developer.naturalfisher.models.entity.Proveedor;

/**
 * Fase 4 Tarea 3
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 12/09/2022
 */
public interface IProveedorDao extends JpaRepository<Proveedor, Long> {
	
	@Query(nativeQuery = false, value = " SELECT p FROM Proveedor p WHERE estado = ?1 ORDER BY nombre")
	List<Proveedor> findAll_Estado(String estado);

}
