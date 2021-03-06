package com.developer.naturalfisher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.naturalfisher.models.entity.Promocion;
import com.developer.naturalfisher.models.service.IPromocionService;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 07/04/2022
 */

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/appi/promocion")
public class PromocionRestController {
	
	@Autowired
	IPromocionService promocionService;
	
	@GetMapping("/promociones")
	public List<Promocion> findAll(){
		return promocionService.findAll();
	}
	
	@PostMapping(value = "/save")
	public Promocion save(@RequestBody Promocion promocionNew) {
		System.out.println("#### INICIA EL LLAMADO SERVICE PROMOCION SAVE ####");
		return promocionService.save(promocionNew);
	}
	
	@PostMapping(value = "/delete")
	public Boolean delete(@RequestBody Promocion promocion) {
		System.out.println("#### INICIA EL LLAMADO SERVICE PROMOCION DELETE ####");
		return promocionService.delete(promocion);
	}

}
