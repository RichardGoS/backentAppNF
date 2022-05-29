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
@Table(name = "promocion")
@JsonIgnoreProperties({"ventas_promocion"})
public class Promocion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String nombre;
	
	@Column(nullable = false, length = 500)
	private String descripccion;
	
	@Column(nullable = false)
	private Double total;
	
	@Column(nullable = false)
	private Double totalCalculado;
	
	@Column(nullable = false)
	private Double porcentage;
	
	@Column(nullable = false)
	private Double ganancia;
	
	@OneToMany(mappedBy = "promocion")
	@JsonIgnoreProperties({"promocion", "hibernateLazyInitializer","handler"})
	private List<ItemPromocion> items_productos;
	
	@OneToMany(mappedBy = "promocion")
	@JsonIgnoreProperties({"promocion", "hibernateLazyInitializer","handler"})
	private List<PromocionVenta> ventas_promocion;
	
	/**
	 * Estado ACTIVO , INACTIVO 
	 */
	@Column(nullable = false, length = 50)
	private String estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripccion() {
		return descripccion;
	}

	public void setDescripccion(String descripccion) {
		this.descripccion = descripccion;
	}

	public List<ItemPromocion> getItems_productos() {
		return items_productos;
	}

	public void setItems_productos(List<ItemPromocion> items_productos) {
		this.items_productos = items_productos;
	}

	public List<PromocionVenta> getVentas_promocion() {
		return ventas_promocion;
	}

	public void setVentas_promocion(List<PromocionVenta> ventas_promocion) {
		this.ventas_promocion = ventas_promocion;
	}

	public Double getTotalCalculado() {
		return totalCalculado;
	}

	public void setTotalCalculado(Double totalCalculado) {
		this.totalCalculado = totalCalculado;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
