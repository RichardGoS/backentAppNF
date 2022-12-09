package com.developer.naturalfisher.models.transporte;

import java.io.Serializable;
import java.util.List;

import com.developer.naturalfisher.models.entity.Inversion;

/**
 * Fase 4 Tarea 3
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 22/09/2022
 */
public class DetalleInversiones implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Inversion> inversiones;
	private Double total;
	private String fecha;
	private int cantInversiones;
	public List<Inversion> getInversiones() {
		return inversiones;
	}
	public void setInversiones(List<Inversion> inversiones) {
		this.inversiones = inversiones;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getCantInversiones() {
		return cantInversiones;
	}
	public void setCantInversiones(int cantInversiones) {
		this.cantInversiones = cantInversiones;
	}
}
