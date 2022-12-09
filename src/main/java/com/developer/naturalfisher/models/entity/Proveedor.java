package com.developer.naturalfisher.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Fase 4 Tarea 3
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 12/09/2022
 */

@Entity
@Table(name = "proveedor")
@JsonIgnoreProperties({"inversiones"})
public class Proveedor implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 20)
	private String nit;
	
	@Column(nullable = false, length = 100)
	private String nombre;
	
	@Column(nullable = false, length = 300)
	private String direccion;
	
	@Column(nullable = false)
	private String telefono;
	
	@Column(nullable = true)
	private String telefono_respaldo;
	
	@Column(nullable = true, length = 300)
	private String descripcion;
	
	/**
	 * Estado ACTIVO , INACTIVO 
	 */
	@Column(nullable = false, length = 10)
	private String estado;
	
	@Column(nullable = false, length = 100)
	private String ciudad;
	
	@OneToMany(mappedBy = "proveedor")
	@JsonIgnoreProperties({"proveedor", "hibernateLazyInitializer","handler"})
	private List<Inversion> inversiones;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public List<Inversion> getInversiones() {
		return inversiones;
	}

	public void setInversiones(List<Inversion> inversiones) {
		this.inversiones = inversiones;
	}

	public String getTelefono_respaldo() {
		return telefono_respaldo;
	}

	public void setTelefono_respaldo(String telefono_respaldo) {
		this.telefono_respaldo = telefono_respaldo;
	}

}
