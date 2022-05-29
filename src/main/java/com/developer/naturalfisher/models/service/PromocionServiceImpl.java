package com.developer.naturalfisher.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.naturalfisher.models.dao.IPromocionDao;
import com.developer.naturalfisher.models.entity.ItemPromocion;
import com.developer.naturalfisher.models.entity.Promocion;
import com.developer.naturalfisher.utilidades.EnumParametros;

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
	
	@Autowired
	IItemPromocionService itemPromocionService;
	
	/**
     * --------------================ METODOS =================--------------------------------
     */
	
	/**
     * -------------- METODOS INTERFACE IPromocionService --------------------------------
     */
	
	/**
     * @Autor RagooS
     * @Descripccion Metodo permite obtener todas las promociones creadas realizadas
     * @Fecha 08/04/2022
     */
	@Override
	public List<Promocion> findAll() {
		// TODO Auto-generated method stub
		return promocionDao.findAll();
	}

	/**
     * @Autor RagooS
     * @Descripccion Metodo permite almacenar una promocion
     * @Fecha 08/04/2022
     */
	@Override
	public Promocion save(Promocion promocionNew) {
		// TODO Auto-generated method stub
		Promocion promocionAlmacenada = null;;
		
		if(promocionNew != null) {
			promocionNew.setEstado(EnumParametros.ESTADO_ACTIVO.getValor());
			
			if(promocionNew.getId() != null) {
				System.out.println("#### EDITAR PROMOCION ####");
				
				System.out.println("#### ANTES DE ACTUALIZAR LA PROMOCION ####");
				promocionAlmacenada = promocionDao.save(promocionNew);
				System.out.println("#### DESPUES DE ACTUALIZAR LA PROMOCION ####");
				
				System.out.println("#### SE PROCEDE A VERIFICAR ITEMS ELIMINADOS ####");
				if(verificarItemsEliminados(promocionNew.getItems_productos(), promocionAlmacenada.getItems_productos())) {
					System.out.println("#### SE ELIMINAN ITEMS CON EXITO ####");
				}
				System.out.println("#### DESPUES DE VERIFICAR ITEMS ELIMINADOS ####");
			} else {
				System.out.println("#### CREAR PROMOCION ####");
				
				System.out.println("#### ANTES DE ALMACENAR LA PROMOCION ####");
				promocionAlmacenada = promocionDao.save(promocionNew);
				System.out.println("#### DESPUES DE ALMACENAR LA PROMOCION ####");
			}
			
			
			if( promocionAlmacenada != null && (promocionNew.getItems_productos() != null && !promocionNew.getItems_productos().isEmpty())) {
				System.out.println("#### LA PROMOCION SE ALMACENO CON EXITO ####");
				
				for(ItemPromocion item: promocionNew.getItems_productos()) {
					Promocion promocionVacia = new Promocion();
					promocionVacia.setId(promocionAlmacenada.getId());
					item.setPromocion(promocionVacia);
					
					if(item.getId() != null) {
						System.out.println("#### EDITAR ITEM PROMOCION ####");
						System.out.println("#### SE VA A EDITAR ITEM PROMOCION CON ID: " + item.getId() + " ####");
					} else {
						System.out.println("#### CREAR ITEM PROMOCION ####");
					}
					
					System.out.println("#### ANTES DE ALMACENAR EL ITEM DE LA PROMOCION ####");
					ItemPromocion itemPromo = itemPromocionService.save(item);
					System.out.println("#### DESPUES DE ALMACENAR EL ITEM DE LA PROMOCION ####");
					
					if(itemPromo != null) {
						System.out.println("#### EL ITEM DE LA PROMOCION SE ALMACENO CON EXITO ####");
					} else {
						System.out.println("#### ERROR AL ALMACENAR EL ITEM DE LA PROMOCION ####");
					}
				}
			} else {
				System.out.println("#### ERROR AL ALMACENAR LA PROMOCION ####");
			}
			
		}
		
		return promocionAlmacenada;
	}

	private boolean verificarItemsEliminados(List<ItemPromocion> promocionNew,
			List<ItemPromocion> promocionAlmacenada) {
		
		System.out.println("#### INICIA VERIFICACION ITEM PROMOCION ELIMINADOS ####");
		boolean eliminados = false;
		List<ItemPromocion> auxAlmacenados = new ArrayList<ItemPromocion>(promocionAlmacenada);
		
		for(ItemPromocion item: promocionNew) {
			if(item.getId() != null) {
				for( int i = 0; i < auxAlmacenados.size(); i++) {
					
					if(auxAlmacenados.get(i).getId() == item.getId()) {
						auxAlmacenados.remove(i);
						break;
					}
				}
			}
		}
		
		if(auxAlmacenados != null && !auxAlmacenados.isEmpty()) {
			System.out.println("#### SE ENCONTRARON ITEMS PROMOCION A ELIMINAR ####");
			for(ItemPromocion item: auxAlmacenados) {
				System.out.println("#### ANTES DE LLAMAR A ELIMINAR EL ITEM PROMOCION ####");
				if(itemPromocionService.delete(item)) {
					eliminados = true;
				} 
			}
		} else {
			System.out.println("#### NO SE ENCONTRARON ITEMS PROMOCION A ELIMINAR ####");
		}
		
		System.out.println("#### FINALIZA VERIFICACION ITEM PROMOCION ELIMINADOS ####");
		
		return false;
	}

	/**
     * @Autor RagooS
     * @Descripccion Metodo consultar las promociones activas
     * @Fecha 06/05/2022
     */
	@Override
	public List<Promocion> findAll_estadoActivo() {
		// TODO Auto-generated method stub
		return promocionDao.findAll_estado(EnumParametros.ESTADO_ACTIVO.getValor());
	}

	@Override
	public boolean delete(Promocion promocion) {
		// TODO Auto-generated method stub
		
		boolean eliminado = false;
		
		promocion.setEstado(EnumParametros.ESTADO_INACTIVO.getValor());
		
		System.out.println("#### ANTES DE ELIMINAR LA PROMOCION ####");
		Promocion promocionEliminada = promocionDao.save(promocion);
		System.out.println("#### DESPUES DE ELIMINAR LA PROMOCION ####");
		
		if(promocionEliminada != null && promocionEliminada.getEstado().equals(EnumParametros.ESTADO_INACTIVO.getValor())) {
			eliminado = true;
			System.out.println("#### PROMOCION ELIMINADA CON EXITO ####");
		} else {
			System.out.println("#### PROMOCION NO ELIMINADA ####");
			eliminado = false;
		}
		
		
		return eliminado;
	}

}
