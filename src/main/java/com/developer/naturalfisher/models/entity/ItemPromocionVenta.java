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
 * Fecha: 10/04/2022
 */

@Entity
@JsonIgnoreProperties({"promocion_venta"})
@Table(name = "item_promocion_venta")
public class ItemPromocionVenta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne()
	@JoinColumn(name = "promocion_venta_id")
	@JsonIgnoreProperties({"items_promocion", "hibernateLazyInitializer","handler"})
	private PromocionVenta promocion_venta;
	
	@ManyToOne()
	@JoinColumn(name = "producto_id")
	@JsonIgnoreProperties({"items_promocion_venta", "hibernateLazyInitializer","handler"})
	private Producto producto;
	
	@Column(nullable = false)
	private Double precio_venta;
	
	@Column(nullable = false)
	private Double cant_peso;
	
	@Column(nullable = false)
	private Double total;
	
	@Column(nullable = false)
	private Double precio_venta_calculado;
	
	@Column(nullable = false)
	private Double total_calculado;
	
	@Column(nullable = false)
	private Double porcentage;
	
	@Column(nullable = false)
	private Double ganancia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PromocionVenta getPromocion_venta() {
		return promocion_venta;
	}

	public void setPromocion_venta(PromocionVenta promocion_venta) {
		this.promocion_venta = promocion_venta;
	}

	public Double getPrecio_venta() {
		return precio_venta;
	}

	public void setPrecio_venta(Double precio_venta) {
		this.precio_venta = precio_venta;
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

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Double getPrecio_venta_calculado() {
		return precio_venta_calculado;
	}

	public void setPrecio_venta_calculado(Double precio_venta_calculado) {
		this.precio_venta_calculado = precio_venta_calculado;
	}

	public Double getTotal_calculado() {
		return total_calculado;
	}

	public void setTotal_calculado(Double total_calculado) {
		this.total_calculado = total_calculado;
	}

	public Double getPorcentage() {
		return porcentage;
	}

	public void setPorcentage(Double porcentage) {
		this.porcentage = porcentage;
	}

	public Double getGanancia() {
		return ganancia;
	}

	public void setGanancia(Double ganancia) {
		this.ganancia = ganancia;
	}
}
