package com.developer.naturalfisher.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.naturalfisher.models.dao.IProveedorDao;
import com.developer.naturalfisher.models.entity.Proveedor;
import com.developer.naturalfisher.utilidades.EnumParametros;

/**
 * Fase 4 Tarea 3
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 12/09/2022
 */
@Service
public class ProveedorServiceImpl implements IProveedorService {
	
	@Autowired
	IProveedorDao proveedorDao;
	
	/**
     * --------------================ METODOS =================--------------------------------
     */
	
	/**
     * -------------- METODOS INTERFACE IProveedorService --------------------------------
     */

	/**
     * @Autor RagooS
     * @Descripccion Metodo permite obtener todas los proveedores registrados
     * @Fecha 12/09/2022
     */
	@Override
	public List<Proveedor> findAll() {
		// TODO Auto-generated method stub
		System.out.println("#### INICIA METODO LISTAR TODOS LOS PROVEEDORES ####");
		return proveedorDao.findAll();
	}

	/**
     * @Autor RagooS
     * @Descripccion Metodo permite obtener todas los proveedores con estado activo(ACTIVO) registrados
     * @Fecha 12/09/2022
     */
	@Override
	public List<Proveedor> findAll_estadoActivo() {
		// TODO Auto-generated method stub
		System.out.println("#### INICIA METODO LISTAR TODOS LOS PROVEEDORES ACTIVOS ####");
		
		List<Proveedor> proveedores = null;
		
		System.out.println("#### ANTES DE LLAMAR AL METODO PARA LISTAR LOS PROVEEDORES ACTIVOS ####");
		proveedores = proveedorDao.findAll_Estado(EnumParametros.ESTADO_ACTIVO.getValor());
		
		if(proveedores == null || proveedores.isEmpty()) {
			System.out.println("#### NO SE ENCONTRARON PROVEEDORES ACTIVOS ####");
		}
		
		return proveedores;
	}

	/**
     * @Autor RagooS
     * @Descripccion Metodo permite almacenar un proveedor en la BD
     * @Fecha 12/09/2022
     */
	@Override
	public Proveedor save(Proveedor proveedor) {
		// TODO Auto-generated method stub
		System.out.println("#### INICIA METODO ALMACENAR PROVEEDOR ####");
		
		Proveedor proveedorNew = null;
		
		if(proveedor != null) {
			proveedor.setEstado(EnumParametros.ESTADO_ACTIVO.getValor());
			proveedorNew = proveedorDao.save(proveedor);
			
			System.out.println("#### SE PROCEDE A VALIDAR SI SE ALMACENO EL PROVEEDOR ####");
			if(proveedorNew != null) {
				System.out.println("#### SE ALMACENO EL PROVEEDOR CON ID: " + proveedorNew.getId() + " ####");
			} else {
				System.out.println("#### ERROR: NO SE ALMACENO EL PROVVEDOR ####");
			}
		} else {
			System.out.println("#### ERROR: PROVEEDOR A ALMACENAR NO PUEDE SER NULL ####");
		}
		
		return proveedorNew;
	}

	/**
     * @Autor RagooS
     * @Descripccion Metodo permite eliminar un proveedor cambiando su estado a (INACTIVO) en la BD
     * @Fecha 12/09/2022
     */
	@Override
	public boolean delete(Proveedor proveedor) {
		// TODO Auto-generated method stub
		System.out.println("#### INICIA METODO ELIMINAR PROVEEDOR ####");
		
		Proveedor proveedorDelete = null;
		boolean eliminado = false;
		
		if(proveedor != null) {
			System.out.println("#### SE VA A CAMBIAR EL ESTADO DEL PROVEEDOR A INACTIVO ####");
			proveedor.setEstado(EnumParametros.ESTADO_INACTIVO.getValor());
			
			System.out.println("#### ANTES DE LLAMAR AL METODO QUE MODIFICA EL ESTADO ####");
			proveedorDelete = proveedorDao.save(proveedor);
			
			if(proveedorDelete != null){
				System.out.println("#### EL PROVEEDOR SE ELIMINO CON EXITO ####");
				eliminado = true;
			} else {
				System.out.println("#### ERROR: EL PROVEEDOR NO SE ELIMINO ####");
				eliminado = false;
			}
		} else {
			System.out.println("#### ERROR: EL PROVEEDOR A ELIMINAR NO PUEDE SER NULL ####");
			eliminado = false;
		}
		
		return eliminado;
	}

}
