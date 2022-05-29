package com.developer.naturalfisher.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 27/03/2022
 */

@Entity
@Table(name = "item_promocion")
public class ItemPromocion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne()
	@JoinColumn(name = "promocion_id")
	@JsonIgnoreProperties({"items_promocion", "hibernateLazyInitializer","handler"})
	private Promocion promocion;
	
	@ManyToOne()
	@JoinColumn(name = "producto_id")
	@JsonIgnoreProperties({"items_promocion", "hibernateLazyInitializer","handler"})
	private Producto producto;
	
	@Column(nullable = false)
	private Double precio_venta;
	
	@Column(nullable = false)
	private Double cant_peso;
	
	@Column(nullable = false)
	private Double total;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Promocion getPromocion() {
		return promocion;
	}

	public void setPromocion(Promocion promocion) {
		this.promocion = promocion;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Double getCant_peso() {
		return cant_peso;
	}

	public void setCant_peso(Double cant_peso) {
		this.cant_peso = cant_peso;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getPrecio_venta() {
		return precio_venta;
	}

	public void setPrecio_venta(Double precio_venta) {
		this.precio_venta = precio_venta;
	}
}
