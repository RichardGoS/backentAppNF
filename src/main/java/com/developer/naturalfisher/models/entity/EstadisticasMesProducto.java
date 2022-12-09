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
 * Fase 4 Tarea 1
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 01/08/2022
 */

@Entity
@Table(name = "estadisticas_mes_producto")
public class EstadisticasMesProducto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Date fecha;
	
	@Column(nullable = false)
	private Date fecha_ultima_ejecucion;
	
	@Column(nullable = false)
	private Double cant_peso;
	
	@Column(nullable = false)
	private Double total;
	
	@ManyToOne()
	@JoinColumn(name = "producto_id")
	@JsonIgnoreProperties({"estadisticas_mes", "hibernateLazyInitializer","handler"})
	private Producto producto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFecha_ultima_ejecucion() {
		return fecha_ultima_ejecucion;
	}

	public void setFecha_ultima_ejecucion(Date fecha_ultima_ejecucion) {
		this.fecha_ultima_ejecucion = fecha_ultima_ejecucion;
	}

	public Double getCant_peso() {
		return cant_peso;
	}

	public void setCant_peso(Double cant_peso) {
		this.cant_peso = cant_peso;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
