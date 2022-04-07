package com.developer.naturalfisher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.naturalfisher.models.service.IInventarioService;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 18/02/2022
 */

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/appi/inventario")
public class InventarioRestController {
	
	@Autowired
	IInventarioService inventarioService;
	
	@GetMapping("/realizarInventario")
	public Boolean realizarInventario() {
		return inventarioService.realizarInventarioGeneral();
	}

}
