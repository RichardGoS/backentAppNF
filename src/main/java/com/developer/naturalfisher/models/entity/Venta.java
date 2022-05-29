package com.developer.naturalfisher.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 14/06/2021
 */

@Entity
@Table(name = "venta")
public class Venta implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Date fecha;
	
	@Column(nullable = false)
	private Double total;
	
	@ManyToOne()
	@JoinColumn(name = "cliente_id")
	@JsonIgnoreProperties({"ventas", "hibernateLazyInitializer","handler"})
	private Cliente cliente;
	
	@OneToMany(mappedBy = "venta", orphanRemoval = true)
	@JsonIgnoreProperties({"venta", "hibernateLazyInitializer","handler"})
	private List<ItemVenta> items;
	
	/*@ManyToMany(mappedBy = "ventas")
	private Set<Producto> productos;*/
	
	/*@JoinTable(
    name = "productos_ventas",
    joinColumns = @JoinColumn(name = "FK_PRODUCTO", nullable = false),
    inverseJoinColumns = @JoinColumn(name="FK_VENTA", nullable = false)
	)
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Producto> productos;*/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<ItemVenta> getItems() {
		return items;
	}

	public void setItems(List<ItemVenta> items) {
		this.items = items;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
