package com.developer.naturalfisher.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 11/06/2021
 */

@Entity
@Table(name = "cliente")
@JsonIgnoreProperties({"ventas"})
public class Cliente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String nombre;
	
	@Column(nullable = false, length = 300)
	private String direccion;
	
	@Column(nullable = false)
	private String telefono;
	
	@OneToMany(mappedBy = "cliente")
	@JsonIgnoreProperties({"cliente", "hibernateLazyInitializer","handler"})
	private List<Venta> ventas;
	
	/**
	 * Estado ACTIVO , INACTIVO 
	 */
	@Column(nullable = false, length = 10)
	private String estado;
	
	/**
     * Fase 4 Tarea 2
     * @author RagooS
     * @fecha 30/07/2022
     * @descripcion nuevas variables
     */
	@Column(nullable = true)
	private String telefono_respaldo;
	
	@Column(nullable = true, length = 300)
	private String direccion_respaldo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	/**
     * Fase 4 Tarea 2
     * @author RagooS
     * @fecha 30/07/2022
     * @descripcion metodos get y  set de las variables creadas
     */
	public String getDireccion_respaldo() {
		return direccion_respaldo;
	}

	public void setDireccion_respaldo(String direccion_respaldo) {
		this.direccion_respaldo = direccion_respaldo;
	}

	public String getTelefono_respaldo() {
		return telefono_respaldo;
	}

	public void setTelefono_respaldo(String telefono_respaldo) {
		this.telefono_respaldo = telefono_respaldo;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
