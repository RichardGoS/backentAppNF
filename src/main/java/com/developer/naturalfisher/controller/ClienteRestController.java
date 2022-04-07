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

import com.developer.naturalfisher.models.entity.Cliente;
import com.developer.naturalfisher.models.service.IClienteService;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 11/06/2021
 */

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/appi/cliente")
public class ClienteRestController {
	
	@Autowired
	IClienteService clienteService;
	
	@GetMapping("/cliente/{id}")
	public Cliente findById(@PathVariable Long id) {
		return clienteService.findById(id);
	}
	
	@GetMapping("/clientes")
	public List<Cliente> findAll(){
		return clienteService.findAll_Activos();
	}

	@PostMapping(value = "/save")
	public Cliente save(@RequestBody Cliente clienteNew) {
		return clienteService.save(clienteNew);
	}
	
	@PostMapping(value = "/eliminar")
	public Boolean eliminar(@RequestBody Cliente clienteDelete) {
		return clienteService.eliminar(clienteDelete);
	}
}
