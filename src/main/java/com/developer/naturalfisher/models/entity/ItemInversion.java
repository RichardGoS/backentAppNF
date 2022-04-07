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
 * Fecha: 08/02/2022
 */

@Entity
@Table(name = "item_inversion")
public class ItemInversion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne()
	@JoinColumn(name = "inversion_id")
	@JsonIgnoreProperties({"items", "hibernateLazyInitializer","handler"})
	private Inversion inversion;
	
	@ManyToOne()
	@JoinColumn(name = "producto_id")
	@JsonIgnoreProperties({"items", "hibernateLazyInitializer","handler"})
	private Producto producto;
	
	@Column(nullable = false)
	private Double precio_unitario;
	
	@Column(nullable = false)
	private Double cant_comprado;
	
	@Column(nullable = false)
	private Double precio_total;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Inversion getInversion() {
		return inversion;
	}

	public void setInversion(Inversion inversion) {
		this.inversion = inversion;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Double getPrecio_unitario() {
		return precio_unitario;
	}

	public void setPrecio_unitario(Double precio_unitario) {
		this.precio_unitario = precio_unitario;
	}

	public Double getCant_comprado() {
		return cant_comprado;
	}

	public void setCant_comprado(Double cant_comprado) {
		this.cant_comprado = cant_comprado;
	}

	public Double getPrecio_total() {
		return precio_total;
	}

	public void setPrecio_total(Double precio_total) {
		this.precio_total = precio_total;
	}

}
