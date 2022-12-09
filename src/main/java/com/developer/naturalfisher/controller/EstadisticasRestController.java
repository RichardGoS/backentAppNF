package com.developer.naturalfisher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.developer.naturalfisher.models.entity.EstadisticasMesProducto;
import com.developer.naturalfisher.models.service.IEstadisticasService;
import com.developer.naturalfisher.models.transporte.DetalleEstadistica;

/**
 * Fase 4 Tarea 1
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 01/08/2022
 */

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/appi/estadisticas")
public class EstadisticasRestController {
	
	@Autowired
	IEstadisticasService estadisticasService;
	
	/**
	 * @author RagooS
	 * @Descripccion Metodo obtencion estadisticas por mes
	 * @ddate 06/08/2022
	 * @param fecha
	 * @return
	 */
	@GetMapping("/estadisticaMesProductos")
	public DetalleEstadistica findByMes(@RequestParam("fecha") String fecha) {
		System.out.println("#### INICIA LLAMADO A RUTA /appi/estadisticas/estadisticaMesProductos  ####");
		
		return estadisticasService.findByMes(fecha);
	}
	
	/**
	 * @author RagooS
	 * @Descripccion Metodo obtener la generacion de las estadisticas en el mes
	 * @ddate 07/08/2022
	 * @param fecha
	 * @return
	 */
	@GetMapping("/generarEstadisticasMesProductos")
	public DetalleEstadistica generarEstadisticaMesProductos(@RequestParam("fecha") String fecha) {
		System.out.println("#### INICIA LLAMADO A RUTA /appi/estadisticas/generarEstadisticasMesProductos  ####");
		return estadisticasService.generarEstadisticaMesProducto(fecha);
	}
	

}
