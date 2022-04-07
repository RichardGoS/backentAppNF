package com.developer.naturalfisher.models.service;

import java.util.List;

import com.developer.naturalfisher.models.entity.Cliente;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 10/06/2021
 */

public interface IClienteService {

	public Cliente save(Cliente clienteNew);
	
	public Cliente findById(Long id);
	
	public List<Cliente> findAll();
	
	public Boolean eliminar(Cliente cliente);
	
	public List<Cliente> findAll_Activos();
	
}
