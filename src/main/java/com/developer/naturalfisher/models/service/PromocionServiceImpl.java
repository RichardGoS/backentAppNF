package com.developer.naturalfisher.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.naturalfisher.models.dao.IPromocionDao;
import com.developer.naturalfisher.models.entity.Promocion;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 07/04/2022
 */

@Service
public class PromocionServiceImpl implements IPromocionService {
	
	@Autowired
	IPromocionDao promocionDao;

	@Override
	public List<Promocion> findAll() {
		// TODO Auto-generated method stub
		return promocionDao.findAll();
	}

}
