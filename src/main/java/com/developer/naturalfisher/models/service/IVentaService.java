package com.developer.naturalfisher.models.service;

import java.util.Date;
import java.util.List;

import com.developer.naturalfisher.models.entity.Venta;
import com.developer.naturalfisher.models.obj.DetalleVentas;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 22/06/2021
 */

public interface IVentaService {
	
	public Venta save(Venta venta);
	
	public List<Venta> findAll();
	
	public Venta findById(Long id);
	
	public DetalleVentas ventasRangoFechaDefault();
	
	public DetalleVentas ventasDetalleEnMes(String fecha);
	
	public List<Venta> ventasEnFecha(String fecha);
	
	public List<Venta> ventasEnMes(String fecha);
	
	public Double consultarTotalVentaMesAño(String fecha);
	
	public List<Venta> consultarVentasDespuesFecha(Date fecha);

}
