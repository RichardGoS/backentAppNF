package com.developer.naturalfisher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.naturalfisher.models.entity.Proveedor;
import com.developer.naturalfisher.models.service.IProveedorService;

/**
 * Fase 4 Tarea 3
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 12/09/2022
 */
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/appi/proveedor")
public class ProveedorRestController {
	
	@Autowired
	IProveedorService proveedorService;
	
	/**
	 * @author RagooS
	 * @Descripccion Servicio almacenar proveedor
	 * @date 12/09/2022
	 */
	@PostMapping(value = "/save")
	public Proveedor save(@RequestBody Proveedor proveedor) {
		System.out.println("#### INGRESA PETICION SERVICIO /appi/proveedor/save ####");
		
		System.out.println("#### ANTES DE LLAMAR AL METODO SAVE ####");
		return proveedorService.save(proveedor);
	}
	
	/**
	 * @author RagooS
	 * @Descripccion Servicio listar proveedores
	 * @date 12/09/2022
	 */
	@GetMapping("/proveedores")
	public List<Proveedor> findAll(){
		System.out.println("#### INGRESA PETICION SERVICIO /appi/proveedor/proveedores ####");
		
		System.out.println("#### ANTES DE LLAMAR AL METODO LISTAR TODOS LOS PROVEEDORES ####");
		
		return proveedorService.findAll();
	}
	
	/**
	 * @author RagooS
	 * @Descripccion Servicio listar proveedores
	 * @date 12/09/2022
	 */
	@GetMapping("/proveedores/activos")
	public List<Proveedor> findAllActivos(){
		System.out.println("#### INGRESA PETICION SERVICIO /appi/proveedor/proveedores/activos ####");
		
		System.out.println("#### ANTES DE LLAMAR AL METODO LISTAR LOS PROVEEDORES ACTIVOS ####");
		
		return proveedorService.findAll_estadoActivo();
	}
	
	/**
	 * @author RagooS
	 * @Descripccion Servicio eliminar proveedor
	 * @date 12/09/2022
	 */
	@PostMapping(value = "/eliminar")
	public Boolean eliminar(@RequestBody Proveedor proveedor) {
		System.out.println("#### INGRESA PETICION SERVICIO /appi/proveedor/eliminar ####");
		
		System.out.println("#### ANTES DE LLAMAR AL METODO ELIMINAR PROVEEDOR ####");
		return proveedorService.delete(proveedor);
	}

}
