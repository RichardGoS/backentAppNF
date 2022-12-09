package com.developer.naturalfisher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.developer.naturalfisher.models.entity.Inversion;
import com.developer.naturalfisher.models.service.IInversionService;
import com.developer.naturalfisher.models.service.IItemInversionService;
import com.developer.naturalfisher.models.transporte.DetalleInversiones;

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
	
	@GetMapping("/todasInversiones")
	public DetalleInversiones findAll(){
		System.out.println("#### INICIA LLAMADO A RUTA /appi/inversion/inversiones  ####");
		
		return inversionService.findAll();
	}
	
	@PostMapping(value = "/save")
	public Inversion save(@RequestBody Inversion inversion) {
		System.out.println("#### INICIA LLAMADO A RUTA /appi/inversion/save  ####");
		
		return inversionService.save(inversion);
	}
	
	
	/**
	 * Fase 4 Tarea 3
	 * @author RagooS
	 * @Descripccion Metodo obtencion inversiones por mes
	 * @ddate 20/01/2022
	 * @param fecha
	 * @return
	 */
	@GetMapping("/inversionDetalleEnMes")
	public DetalleInversiones findByMes(@RequestParam("fecha") String fecha){
		System.out.println("#### INICIA LLAMADO A RUTA /appi/inversion/inversionDetalleEnMes  ####");
		
		return inversionService.detalleInversionesEnMes(fecha);
	}
	
	/**
	 * Fase 4 Tarea 3
	 * @author RagooS
	 * @Descripccion Metodo obtencion inversiones por fecha
	 * @ddate 05/11/2022
	 * @param fecha
	 * @return
	 */
	@GetMapping("/inversionesEnFecha")
	public List<Inversion> findByFecha(@RequestParam("fecha") String fecha){
		System.out.println("#### INICIA LLAMADO A RUTA /appi/inversion/inversionesEnFecha  ####");
		
		return inversionService.consultarInversionesEnFecha(fecha);
		
	}

}
