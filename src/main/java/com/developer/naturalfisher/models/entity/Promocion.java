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
	
	@ManyToOne()
	@JoinColumn(name = "venta_id")
	@JsonIgnoreProperties({"promociones", "hibernateLazyInitializer","handler"})
	private Venta venta;
	
	@Column(nullable = false)
	private Double total;
	
	@OneToMany(mappedBy = "promocion", orphanRemoval = true)
	@JsonIgnoreProperties({"promocion", "hibernateLazyInitializer","handler"})
	private List<ItemPromocion> items;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
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

	public List<ItemPromocion> getItems() {
		return items;
	}

	public void setItems(List<ItemPromocion> items) {
		this.items = items;
	}
	

}
