package com.developer.naturalfisher.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.naturalfisher.models.dao.IClienteDao;
import com.developer.naturalfisher.models.entity.Cliente;
import com.developer.naturalfisher.utilidades.EnumParametros;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 10/06/2021
 */

@Service
public class ClienteServiceImpl implements IClienteService {
	
	@Autowired
	IClienteDao clienteDao;

	/**
     * @Autor RagooS
     * @Descripccion Metodo permite guardar o actualizar un cliente
     * @Fecha 06/01/2022 antes
     */
	@Override
	public Cliente save(Cliente clienteNew) {
		// TODO Auto-generated method stub
		return clienteDao.save(clienteNew);
	}

	/**
     * @Autor RagooS
     * @Descripccion Metodo permite listar un cliente por su id
     * @Fecha 06/01/2022 antes
     */
	@Override
	public Cliente findById(Long id) {
		// TODO Auto-generated method stub
		return clienteDao.findById(id).orElse(null);
	}

	/**
     * @Autor RagooS
     * @Descripccion Metodo permite listar los clientes
     * @Fecha 06/01/2022 antes
     */
	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return clienteDao.findAll();
	}

	/**
     * @Autor RagooS
     * @Descripccion Metodo permite eliminar el cliente cambiando su estado de ACTIVO a INACTIVO
     * @Fecha 06/01/2022
     */
	@Override
	public Boolean eliminar(Cliente cliente) {
		// TODO Auto-generated method stub
		boolean eliminado = false;
		cliente.setEstado(EnumParametros.ESTADO_INACTIVO.getValor());
		Cliente clienteEliminado = clienteDao.save(cliente);
		
		if(clienteEliminado != null && clienteEliminado.getEstado().equals(EnumParametros.ESTADO_INACTIVO.getValor())) {
			eliminado = true;
		} else {
			eliminado = false;
		}
		
		return eliminado;
	}
	
	/**
     * @Autor RagooS
     * @Descripccion Metodo permite listar los clientes
     * @Fecha 07/01/2022
     */
	@Override
	public List<Cliente> findAll_Activos() {
		// TODO Auto-generated method stub
		return clienteDao.findAll_Estado(EnumParametros.ESTADO_ACTIVO.getValor());
	}
	
	

}
