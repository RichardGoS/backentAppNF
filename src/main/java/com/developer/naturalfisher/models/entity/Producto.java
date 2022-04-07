package com.developer.naturalfisher.models.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 11/06/2021
 */

@Entity
@Table(name = "producto")
@JsonIgnoreProperties({"items"})
public class Producto implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true, length = 20)
	private String codigo;
	
	@Column(nullable = false, length = 50)
	private String nombre;
	
	@Column(nullable = false, length = 20)
	private String unidad;
	
	@Column(nullable = false)
	private Double precio;
	
	@OneToMany(mappedBy = "producto")//, cascade = CascadeType.ALL, orphanRemoval = true
	@JsonIgnoreProperties({"producto", "hibernateLazyInitializer","handler"})
	private List<ItemVenta> items;
	
	@OneToMany(mappedBy = "producto")//, cascade = CascadeType.ALL, orphanRemoval = true
	@JsonIgnoreProperties({"producto", "hibernateLazyInitializer","handler"})
	private List<ItemPromocion> items_promocion;
	
	@OneToOne(mappedBy = "producto")
	@JsonIgnoreProperties({"producto", "hibernateLazyInitializer","handler"})
	private Bodega bodega;
	/*
	@ManyToMany(mappedBy = "producto")
	Set<Inversion> inversion;*/
	
	@OneToMany(mappedBy = "producto")
	@JsonIgnoreProperties({"producto", "hibernateLazyInitializer","handler"})
	private List<Inventario> inventarios;
	
	/**
	 * Estado ACTIVO , INACTIVO 
	 */
	@Column(nullable = false, length = 50)
	private String estado;
	
	/**
	 * Estado S , N 
	 */
	@Column(nullable = false, length = 2)
	private String realiza_inventario;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = true, length = 200)
	private String descripcion_unidad;
	
	@Column(nullable = false)
	private Double variacion;

	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	
	public List<ItemVenta> getItems() {
		return items;
	}

	public void setItems(List<ItemVenta> items) {
		this.items = items;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getDescripcion_unidad() {
		return descripcion_unidad;
	}

	public void setDescripcion_unidad(String descripcion_unidad) {
		this.descripcion_unidad = descripcion_unidad;
	}

	public Double getVariacion() {
		return variacion;
	}

	public void setVariacion(Double variacion) {
		this.variacion = variacion;
	}

	public Bodega getBodega() {
		return bodega;
	}

	public void setBodega(Bodega bodega) {
		this.bodega = bodega;
	}

	public List<Inventario> getInventarios() {
		return inventarios;
	}

	public void setInventarios(List<Inventario> inventarios) {
		this.inventarios = inventarios;
	}

	public String getRealiza_inventario() {
		return realiza_inventario;
	}

	public void setRealiza_inventario(String realiza_inventario) {
		this.realiza_inventario = realiza_inventario;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
