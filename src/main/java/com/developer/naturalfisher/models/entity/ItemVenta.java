package com.developer.naturalfisher.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 20/06/2021
 */

@Entity
@Table(name = "item_venta")
public class ItemVenta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne()
	@JoinColumn(name = "venta_id")
	@JsonIgnoreProperties({"items", "hibernateLazyInitializer","handler"})
	private Venta venta;
	
	@ManyToOne()
	@JoinColumn(name = "producto_id")
	@JsonIgnoreProperties({"items", "hibernateLazyInitializer","handler"})
	private Producto producto;
	
	/**
	 * Estado S , N 
	 */
	@Column(nullable = false, length = 2)
	private String usa_precio_distinto;
	
	@Column(nullable = true)
	private Double precio_distinto;
	
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

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public String getUsa_precio_distinto() {
		return usa_precio_distinto;
	}

	public void setUsa_precio_distinto(String usa_precio_distinto) {
		this.usa_precio_distinto = usa_precio_distinto;
	}

	public Double getPrecio_distinto() {
		return precio_distinto;
	}

	public void setPrecio_distinto(Double precio_distinto) {
		this.precio_distinto = precio_distinto;
	}

}
