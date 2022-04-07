package com.developer.naturalfisher.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developer.naturalfisher.models.entity.Promocion;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 01/04/2022
 */

public interface IPromocionDao extends JpaRepository<Promocion, Long> {

}
