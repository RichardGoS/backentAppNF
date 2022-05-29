package com.developer.naturalfisher.models.transporte;

import java.io.Serializable;
import java.util.List;

import com.developer.naturalfisher.models.entity.Producto;
import com.developer.naturalfisher.models.entity.Promocion;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 06/05/2022
 */

public class ProductosTransporte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Producto> productos;
	private List<Promocion> promocion;
	
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	public List<Promocion> getPromocion() {
		return promocion;
	}
	public void setPromocion(List<Promocion> promocion) {
		this.promocion = promocion;
	}

}
