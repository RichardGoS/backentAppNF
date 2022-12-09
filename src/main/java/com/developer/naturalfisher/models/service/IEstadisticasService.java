package com.developer.naturalfisher.models.service;

import java.util.List;

import com.developer.naturalfisher.models.entity.EstadisticasMesProducto;
import com.developer.naturalfisher.models.entity.Producto;
import com.developer.naturalfisher.models.transporte.DetalleEstadistica;

/**
 * Fase 4 Tarea 1
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 01/08/2022
 */
public interface IEstadisticasService {
	
	public DetalleEstadistica findByMes(String mes);
	public DetalleEstadistica generarEstadisticaMesProducto(String mes);
	public EstadisticasMesProducto save(EstadisticasMesProducto estadisticasMesProducto);
	public EstadisticasMesProducto findByMesProducto(String fecha, Producto producto);

}
