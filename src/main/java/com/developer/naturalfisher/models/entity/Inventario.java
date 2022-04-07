package com.developer.naturalfisher.models.entity;

import java.io.Serializable;
import java.util.Date;

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
 * Fecha: 02/02/2022
 */

@Entity
@Table(name = "inventario")
public class Inventario implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne()
	@JoinColumn(name = "producto_id")
	@JsonIgnoreProperties({"inventarios", "hibernateLazyInitializer","handler"})
	private Producto producto;
	
	@Column(nullable = false)
	private Date fecha;
	
	@Column(nullable = false)
	private Double cant_comprado;
	
	@Column(nullable = false)
	private Double cant_vendido;
	
	@Column(nullable = false)
	private Double cant_diferencia;

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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getCant_comprado() {
		return cant_comprado;
	}

	public void setCant_comprado(Double cant_comprado) {
		this.cant_comprado = cant_comprado;
	}

	public Double getCant_vendido() {
		return cant_vendido;
	}

	public void setCant_vendido(Double cant_vendido) {
		this.cant_vendido = cant_vendido;
	}

	public Double getCant_diferencia() {
		return cant_diferencia;
	}

	public void setCant_diferencia(Double cant_diferencia) {
		this.cant_diferencia = cant_diferencia;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
