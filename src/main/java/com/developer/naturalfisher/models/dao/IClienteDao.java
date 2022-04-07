package com.developer.naturalfisher.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.developer.naturalfisher.models.entity.Cliente;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 10/06/2021
 */

public interface IClienteDao extends JpaRepository<Cliente, Long> {
	
	@Query(nativeQuery = false,value = " SELECT v FROM Cliente v WHERE estado = ?1")
	List<Cliente> findAll_Estado(String estado);

}
