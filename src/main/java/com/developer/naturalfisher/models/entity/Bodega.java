package com.developer.naturalfisher.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 30/01/2022
 */

@Entity
@Table(name = "bodega")
public class Bodega implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "producto_id", referencedColumnName = "id")
	@JsonIgnoreProperties({"bodega", "inventarios", "hibernateLazyInitializer","handler"})
	private Producto producto;
	
	@Column(nullable = false)
	private Double cant_disponible;

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

	public Double getCant_disponible() {
		return cant_disponible;
	}

	public void setCant_disponible(Double cant_disponible) {
		this.cant_disponible = cant_disponible;
	}

}
