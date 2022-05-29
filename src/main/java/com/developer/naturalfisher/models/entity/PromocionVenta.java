package com.developer.naturalfisher.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 10/04/2022
 */

@Entity
@Table(name = "promocion_venta")
@JsonIgnoreProperties({"item_venta"})
public class PromocionVenta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Double totalCalculado;
	
	@Column(nullable = false)
	private Double porcentage;
	
	@Column(nullable = false)
	private Double ganancia;
	
	@ManyToOne()
	@JoinColumn(name = "promocion_id")
	@JsonIgnoreProperties({"ventas_promocion", "hibernateLazyInitializer","handler"})
	private Promocion promocion;
	
	@OneToMany(mappedBy = "promocion_venta", orphanRemoval = true)
	@JsonIgnoreProperties({"promocion_venta", "hibernateLazyInitializer","handler"})
	private List<ItemPromocionVenta> items_promocion;
	
	/*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_venta_id", referencedColumnName = "id")*/
	@OneToOne(mappedBy = "promocion_venta")
	private ItemVenta item_venta;
	
	@Column(nullable = false)
	private Double total;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Promocion getPromocion() {
		return promocion;
	}

	public void setPromocion(Promocion promocion) {
		this.promocion = promocion;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<ItemPromocionVenta> getItems_promocion() {
		return items_promocion;
	}

	public void setItems_promocion(List<ItemPromocionVenta> items_promocion) {
		this.items_promocion = items_promocion;
	}

	public ItemVenta getItem_venta() {
		return item_venta;
	}

	public void setItem_venta(ItemVenta item_venta) {
		this.item_venta = item_venta;
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
	
}
