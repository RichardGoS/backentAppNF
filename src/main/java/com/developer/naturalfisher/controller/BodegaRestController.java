package com.developer.naturalfisher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.naturalfisher.models.entity.Bodega;
import com.developer.naturalfisher.models.entity.Cliente;
import com.developer.naturalfisher.models.service.IBodegaService;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 30/01/2022
 */

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/appi/bodega")

public class BodegaRestController {
	
	@Autowired
	IBodegaService bodegaService;
	
	@GetMapping("/bodegas")
	public List<Bodega> findAll(){
		return bodegaService.findAll();
	}
	
	@GetMapping("/bodega/{id}")
	public Bodega findById(@PathVariable Long id) {
		return bodegaService.findById(id);
	}
	

}
