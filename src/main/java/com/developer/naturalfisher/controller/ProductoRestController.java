package com.developer.naturalfisher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.developer.naturalfisher.models.entity.Producto;
import com.developer.naturalfisher.models.service.IProductoService;
import com.developer.naturalfisher.models.transporte.ProductosTransporte;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 11/06/2021
 */

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/appi/producto")
public class ProductoRestController {
	
	@Autowired
	IProductoService productoService;
	
	@GetMapping("/producto/{id}")
	public Producto findById(@PathVariable Long id) {
		System.out.println("#### INICIA LLAMADO A RUTA /appi/producto/producto id: " + id + " ####");
		return productoService.findById(id);
	}
	
	@GetMapping("/productos")
	public ProductosTransporte findAll(){
		System.out.println("#### INICIA LLAMADO A RUTA /appi/producto/productos  ####");
		return productoService.findProductosGeneralActivos();
	}
	
	@GetMapping("/producto/codigo/{codigo}")
	public Producto findByCodigo(@PathVariable String codigo) {
		System.out.println("#### INICIA LLAMADO A RUTA /appi/producto/codigo  codigo:" + codigo + "  ####");
		return productoService.findByCodigo(codigo);
	}
	
	@PostMapping(value = "/save")
	public Producto save(@RequestBody Producto productoNew) {
		System.out.println("#### INICIA LLAMADO A RUTA /appi/producto/save  ####");
		return productoService.save(productoNew);
	}
	
	@PostMapping(value = "/eliminar")
	public Boolean delete(@RequestBody Producto producto) {
		System.out.println("#### INICIA LLAMADO A RUTA /appi/producto/eliminar  ####");
		return productoService.delete(producto);
	}
	
	@GetMapping("/productosVenta")
	public ProductosTransporte findAll_ActivoVenta(){
		System.out.println("#### INICIA LLAMADO A RUTA /appi/producto/productosVenta  ####");
		return productoService.findProductosGeneralActivos();
	}
	
	@GetMapping("/productosPromoVenta")
	public List<Producto> findProductosGeneral() {
		System.out.println("#### INICIA LLAMADO A RUTA /appi/producto/productosPromoVenta  ####");
		return productoService.findAll_ActivoVenta();
	}
	
	@GetMapping("/productosActivosInversion")
	public ProductosTransporte findProductosActivosInversion() {
		System.out.println("#### INICIA LLAMADO A RUTA /appi/producto/productosActivosInversion  ####");
		return productoService.findAll_ActivoInversion();
	}
	
}
