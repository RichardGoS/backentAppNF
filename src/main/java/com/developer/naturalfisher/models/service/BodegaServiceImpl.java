package com.developer.naturalfisher.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.naturalfisher.models.dao.IBodegaDao;
import com.developer.naturalfisher.models.entity.Bodega;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 30/01/2022
 */

@Service
public class BodegaServiceImpl implements IBodegaService {
	
	@Autowired
	private IBodegaDao bodegaDao;

	/**
     * --------------================ METODOS =================--------------------------------
     */

    /**
     * -------------- METODOS INTERFACE IBodegaService --------------------------------
     */
	
	/**
     * @Autor RagooS
     * @Descripccion Metodo permite listar las bodegas
     * @Fecha 30/01/2022
     */
	
	@Override
	public List<Bodega> findAll() {
		// TODO Auto-generated method stub
		return bodegaDao.findAll();
	}

	
	/**
     * @Autor RagooS
     * @Descripccion Metodo permite almacenar o actualizar la bodega
     * @Fecha 30/01/2022
     */
	@Override
	public Bodega save(Bodega bodega) {
		// TODO Auto-generated method stub
		return bodegaDao.save(bodega);
	}

	/**
     * @Autor RagooS
     * @Descripccion Metodo permite listar la bodega por su id
     * @Fecha 30/01/2022
     */
	@Override
	public Bodega findById(Long id) {
		// TODO Auto-generated method stub
		return bodegaDao.findById(id).orElse(null);
	}


	/**
     * @Autor RagooS
     * @Descripccion Metodo permite listar la bodega por el id_producto
     * @Fecha 30/01/2022
     */
	@Override
	public Bodega findByIdProducto(Long id) {
		// TODO Auto-generated method stub
		return bodegaDao.findByIdProducto(id);
	}
	
	

}
