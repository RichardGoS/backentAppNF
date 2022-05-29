package com.developer.naturalfisher.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.naturalfisher.models.dao.IItemPromocionDao;
import com.developer.naturalfisher.models.entity.ItemPromocion;

/**
* de RagooS
* Autor: Richard Gomez O.
* Para: EmpresaDevelopers.Backend.NaturalFisher
* Fecha: 08/04/2022
*/
@Service
public class ItemPromocionServiceImpl implements IItemPromocionService {
	
	@Autowired
	IItemPromocionDao itemPromocionDao;
	
	/**
     * --------------================ METODOS =================--------------------------------
     */
	
	/**
     * -------------- METODOS INTERFACE IItemPromocionService --------------------------------
     */

	/**
	 * 
	 * @author RagooS
	 * @Descripccion Metodo permite almacenar los items de las promociones
	 * @fecha 08/04/2022
	 */
	@Override
	public ItemPromocion save(ItemPromocion itemNuevo) {
		// TODO Auto-generated method stub
		return itemPromocionDao.save(itemNuevo);
	}

	/**
	 * 
	 * @author RagooS
	 * @Descripccion Metodo permite eliminar el item de la promocion
	 * @fecha 08/04/2022
	 */
	@Override
	public boolean delete(ItemPromocion itemDelete) {
		// TODO Auto-generated method stub
		boolean eliminado = false;
		
		System.out.println("#### SE VA A ELIMINAR EL ITEM PROMOCION CON ID " + itemDelete.getId() + "####");
		itemPromocionDao.deleteById(itemDelete.getId());
		
		if(itemPromocionDao.getById(itemDelete.getId()) != null) {
			eliminado = true;
			System.out.println("#### SE A ELIMINADO EL ITEM PROMOCION CON EXITO ####");
		} else {
			System.out.println("#### ERROR AL ELIMINAR EL ITEM PROMOCION ####");
		}
		
		return eliminado;
	}

}
