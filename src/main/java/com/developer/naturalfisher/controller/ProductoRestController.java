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
		return productoService.findById(id);
	}
	
	@GetMapping("/productos")
	public List<Producto> findAll(){
		return productoService.findAll_Activo();
	}
	
	@GetMapping("/producto/codigo/{codigo}")
	public Producto findByCodigo(@PathVariable String codigo) {
		return productoService.findByCodigo(codigo);
	}
	
	@PostMapping(value = "/save")
	public Producto save(@RequestBody Producto productoNew) {
		return productoService.save(productoNew);
	}
	
	@PostMapping(value = "/eliminar")
	public Boolean delete(@RequestBody Producto producto) {
		return productoService.delete(producto);
	}
	
	@GetMapping("/productosVenta")
	public List<Producto> findAll_ActivoVenta(){
		return productoService.findAll_ActivoVenta();
	}

}
