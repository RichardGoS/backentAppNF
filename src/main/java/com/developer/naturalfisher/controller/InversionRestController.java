package com.developer.naturalfisher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.naturalfisher.models.entity.Inversion;
import com.developer.naturalfisher.models.service.IInversionService;
import com.developer.naturalfisher.models.service.IItemInversionService;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 30/01/2022
 */

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/appi/inversion")
public class InversionRestController {
	
	@Autowired
	IInversionService inversionService;
	
	@Autowired
	IItemInversionService itemInversionService;
	
	@GetMapping("/inversiones")
	public List<Inversion> findAll(){
		return inversionService.findAll();
	}
	
	@PostMapping(value = "/save")
	public Inversion save(@RequestBody Inversion inversion) {
		return inversionService.save(inversion);
	}

}
