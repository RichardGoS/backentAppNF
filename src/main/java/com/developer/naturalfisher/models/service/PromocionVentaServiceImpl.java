package com.developer.naturalfisher.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.naturalfisher.models.dao.IPromocionVentaDao;
import com.developer.naturalfisher.models.entity.PromocionVenta;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 10/04/2022
 */

@Service
public class PromocionVentaServiceImpl implements IPromocionVentaService {
	
	/**
	 * --------------================ VARIABLES =================--------------------------------
	 */
	
	@Autowired
	IPromocionVentaDao promocionVentaDao;
	
	
	/**
     * --------------================ METODOS =================--------------------------------
     */
	
	/**
     * -------------- METODOS INTERFACE IPromocionVentaService --------------------------------
     */

	/**
     * @Autor RagooS
     * @Descripccion Metodo almacenar la promocion venta
     * @Fecha 10/04/2022
     */
	@Override
	public PromocionVenta save(PromocionVenta promocionVentaNew) {
		// TODO Auto-generated method stub
		return promocionVentaDao.save(promocionVentaNew);
	}


	@Override
	public List<PromocionVenta> findByIdPromocion(Long idPromocion) {
		// TODO Auto-generated method stub
		return null;
	}

}
