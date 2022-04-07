package com.developer.naturalfisher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.developer.naturalfisher.models.entity.Venta;
import com.developer.naturalfisher.models.obj.DetalleVentas;
import com.developer.naturalfisher.models.service.IItemVentaService;
import com.developer.naturalfisher.models.service.IVentaService;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 22/06/2021
 */

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/appi/venta")
public class VentaRestController {
	
	@Autowired
	IItemVentaService itemVentaService;
	
	@Autowired
	IVentaService ventaService;
	
	@GetMapping("/venta/{id}")
	public Venta findById(@PathVariable Long id) {
		return ventaService.findById(id);
	}
	
	@GetMapping("/ventas")
	public List<Venta> findAll(){
		return ventaService.findAll();
	}
	
	@GetMapping("/ventasRangoDefault")
	public DetalleVentas findRangoFechaDefault(){

		return ventaService.ventasRangoFechaDefault();
	}
	
	@GetMapping("/ventasEnFecha")
	public List<Venta> findByFecha(@RequestParam("fecha") String fecha) {
		return ventaService.ventasEnFecha(fecha);
	}
	
	/**
	 * @author RagooS
	 * @Descripccion Metodo obtencion ventas por mes
	 * @ddate 20/01/2022
	 * @param fecha
	 * @return
	 */
	@GetMapping("/ventasDetalleEnMes")
	public DetalleVentas findByMes(@RequestParam("fecha") String fecha) {
		
		DetalleVentas detalleVentas = ventaService.ventasDetalleEnMes(fecha);
		
		return detalleVentas;
	}
	
	@PostMapping(value = "/save")
	public Venta save(@RequestBody Venta venta) {
		return ventaService.save(venta);
	}

}
