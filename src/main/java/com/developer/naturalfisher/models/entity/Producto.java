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
@JsonIgnoreProperties({"items", "items_promocion", "items_promocion_venta", "inventarios", "estadisticas_mes"})
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
	
	@OneToMany(mappedBy = "producto")//, cascade = CascadeType.ALL, orphanRemoval = true
	@JsonIgnoreProperties({"producto", "hibernateLazyInitializer","handler"})
	private List<ItemPromocionVenta> items_promocion_venta;
	
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
	@Column(nullable = false, length = 10)
	private String estado;
	
	/**
	 * Estado S , N 
	 */
	@Column(nullable = false, length = 2)
	private String realiza_inventario;
	
	/**
     * Fase 4 Tarea 1
     * @author RagooS
     * @fecha 01/08/2022
     * @descripcion nuevas variables
     */
	@OneToMany(mappedBy = "producto")//, cascade = CascadeType.ALL, orphanRemoval = true
	@JsonIgnoreProperties({"producto", "hibernateLazyInitializer","handler"})
	private List<EstadisticasMesProducto> estadisticas_mes;
	
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

	public List<ItemPromocion> getItems_promocion() {
		return items_promocion;
	}

	public void setItems_promocion(List<ItemPromocion> items_promocion) {
		this.items_promocion = items_promocion;
	}

	public List<ItemPromocionVenta> getItems_promocion_venta() {
		return items_promocion_venta;
	}

	public void setItems_promocion_venta(List<ItemPromocionVenta> items_promocion_venta) {
		this.items_promocion_venta = items_promocion_venta;
	}
	
	/**
     * Fase 4 Tarea 1
     * @author RagooS
     * @fecha 01/08/2022
     * @descripcion get y sets para las nuevas variables
     */
	public List<EstadisticasMesProducto> getEstadisticas_mes() {
		return estadisticas_mes;
	}

	public void setEstadisticas_mes(List<EstadisticasMesProducto> estadisticas_mes) {
		this.estadisticas_mes = estadisticas_mes;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
