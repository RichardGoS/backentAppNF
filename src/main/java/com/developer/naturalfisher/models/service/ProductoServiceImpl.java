package com.developer.naturalfisher.models.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.naturalfisher.models.dao.IProductoDao;
import com.developer.naturalfisher.models.entity.Bodega;
import com.developer.naturalfisher.models.entity.Producto;
import com.developer.naturalfisher.models.transporte.ProductosTransporte;
import com.developer.naturalfisher.utilidades.EnumParametros;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 11/06/2021
 */

@Service
public class ProductoServiceImpl implements IProductoService {
	
	@Autowired
	IProductoDao productoDao;
	
	@Autowired
	IBodegaService bodegaService;
	
	@Autowired
	IPromocionService promocionService;

	
	/**
     * --------------================ METODOS =================--------------------------------
     */

    /**
     * -------------- METODOS INTERFACE IProductoService --------------------------------
     */
	
	/**
     * @Autor RagooS
     * @Descripccion Metodo permite listar un producto por su id
     * @Fecha 07/01/2022 antes
     */
	@Override
	public Producto findById(Long id){
		// TODO Auto-generated method stub
		return productoDao.findById(id).orElse(null);
	}

	/**
     * @Autor RagooS
     * @Descripccion Metodo permite guardar o actualizar un producto
     * @Fecha 07/01/2022 antes
     */
	@Override
	public Producto save(Producto productoNew) {
		// TODO Auto-generated method stub
		
		String codigo = "";
		if(productoNew.getId() == null) {
			codigo = generarCodigoProducto(productoNew);
		} else {
			codigo = productoNew.getCodigo();
		}
		
		
		productoNew.setCodigo(codigo);
		productoNew.setEstado(EnumParametros.ESTADO_ACTIVO.getValor());
		
		return productoDao.save(productoNew);
	}

	/**
     * @Autor RagooS
     * @Descripccion Metodo permite listar los productos activos
     * @Fecha 07/01/2022 antes
     */
	@Override
	public List<Producto> findAll_Activo() {
		// TODO Auto-generated method stub
		return productoDao.findAll_Estado(EnumParametros.ESTADO_ACTIVO.getValor());
	}
	
	/**
     * -------------- METODOS PROPIOS --------------------------------
     */
	
	/**
     * @Autor RagooS
     * @Descripccion Metodo permite generar el codigo del producto
     * @Fecha 13/01/2022
     */
	private String generarCodigoProducto(Producto productoNew) {
		// TODO Auto-generated method stub
		String codigo = "";
		
		// Se extrae los caracteres del nombre del producto
		if(productoNew.getNombre().contains(" ")) {
			String[] nomTem = productoNew.getNombre().split(" ");
			for(String t:nomTem) {
				codigo += t.substring(0, 1);
			}
		} else {
			codigo += productoNew.getNombre().substring(0, 1);
		}
		
		// se convierten los caracteres en mayuscula
		codigo = codigo.toUpperCase();
		
		// Se genera numero aleatorio de 2 digitos
		int numero = (int)(Math.random()*99+1);
		
		if(numero < 10) {
			codigo += "0" + numero;
		} else {
			codigo += "" + numero;
		}
		
		// Se genera el codigo de la fecha en formato ddMMyy (130122) -> 13/01/2022
		SimpleDateFormat format = new SimpleDateFormat("ddMMyy");
    	
    	Date date = new Date();
    	
    	codigo += format.format(date);
		
		return codigo;
	}
	
	/**
     * @Autor RagooS
     * @Descripccion Metodo permite listar un producto por su id
     * @Fecha 07/01/2022 antes
     */
	@Override
	public Producto findByCodigo(String codigo) {
		// TODO Auto-generated method stub
		return productoDao.findByCodigo(codigo);
	}

	/**
     * @Autor RagooS
     * @Descripccion Metodo permite eliminar un producto
     * @Fecha 16/01/2022
     */
	@Override
	public boolean delete(Producto producto) {
		// TODO Auto-generated method stub
		boolean eliminado = false;
		producto.setEstado(EnumParametros.ESTADO_INACTIVO.getValor());
		
		Producto productoEliminado = productoDao.save(producto);
		
		if(productoEliminado != null && productoEliminado.getEstado().equals(EnumParametros.ESTADO_INACTIVO.getValor())) {
			eliminado = true;
		} else {
			eliminado = false;
		}
		
		return eliminado;
	}

	
	/**
     * @Autor RagooS
     * @Descripccion Metodo permite listar los productos activos para las ventas
     * @Fecha 16/01/2022
     */
	@Override
	public List<Producto> findAll_ActivoVenta() {
		// TODO Auto-generated method stub
		
		List<Producto> productos = productoDao.findAll_EstadoInventario("N");
		
		if(productos == null) {
			productos = new ArrayList<>();
		}
		
		List<Bodega> bodegas = bodegaService.findAll();
		
		if(bodegas!=null && !bodegas.isEmpty()) {
			for(Bodega bodega: bodegas) {
				if(bodega.getProducto().getEstado().equals(EnumParametros.ESTADO_ACTIVO.getValor()) && bodega.getCant_disponible()>0)
					productos.add(bodega.getProducto());
			}
		}
		
		for(int i=0; i<productos.size(); i++) {
			if(productos.get(i).getEstado().equals(EnumParametros.ESTADO_INACTIVO.getValor())) {
				productos.remove(i);
			}
		}
		
		
		return productos;
	}

	/**
     * @Autor RagooS
     * @Descripccion Metodo permite listar los todos los productos que se tienen
     * @Fecha 06/05/2022
     */
	@Override
	public ProductosTransporte findProductosGeneralActivos() {
		// TODO Auto-generated method stub
		
		ProductosTransporte productosTransporte = new ProductosTransporte();
		
		productosTransporte.setProductos(findAll_ActivoVenta());
		productosTransporte.setPromocion(promocionService.findAll_estadoActivo());
		
		return productosTransporte;
	}

	/**
	 * Fase 4 Tarea 1
     * @Autor RagooS
     * @Descripccion Metodo permite obtener la lista de todos los productos
     * @Fecha 01/08/2022
     */
	@Override
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		return productoDao.findAll();
	}

	/**
	 * Fase 4 Tarea 3
     * @Autor RagooS
     * @Descripccion Metodo permite obtener la lista de todos los productos activos para la inversion
     * @Fecha 01/08/2022
     */
	@Override
	public ProductosTransporte findAll_ActivoInversion() {
		System.out.println("#### INICIA EL METODO findAll_ActivoInversion()  ####");
		ProductosTransporte productosTransporte = new ProductosTransporte();
		
		System.out.println("#### ANTES DE LLAMAR AL METODO findAll_Estado_ManejaInventario()  ####");
		List<Producto> productos = productoDao.findAll_Estado_ManejaInventario("S", EnumParametros.ESTADO_ACTIVO.getValor());
		System.out.println("#### DESPUES DE LLAMAR AL METODO findAll_Estado_ManejaInventario()  ####");
		
		if(productos != null && !productos.isEmpty()) {
			System.out.println("#### SE ENCONTRARON " + productos.size() + " PRODUCTOS  ####");
			productosTransporte.setProductos(productos);
		} else {
			System.out.println("#### NO SE ENCONTRARON PRODUCTOS  ####");
			productosTransporte.setProductos(null);
		}
		return productosTransporte;
	}

}
