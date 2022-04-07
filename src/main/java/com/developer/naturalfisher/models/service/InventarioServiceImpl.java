package com.developer.naturalfisher.models.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.naturalfisher.models.dao.IInventarioDao;
import com.developer.naturalfisher.models.entity.Bodega;
import com.developer.naturalfisher.models.entity.Inventario;
import com.developer.naturalfisher.models.entity.ItemInversion;
import com.developer.naturalfisher.models.entity.ItemVenta;
import com.developer.naturalfisher.models.entity.Producto;
import com.developer.naturalfisher.models.entity.Venta;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 03/02/2022
 */

@Service
public class InventarioServiceImpl implements IInventarioService {
	
	@Autowired
	IInventarioDao inventarioDao;
	
	@Autowired
	IProductoService productoService;
	
	@Autowired
	IInversionService inversionService;
	
	@Autowired
	IBodegaService bodegaService;
	
	@Autowired
	IItemInversionService itemInversionService;
	
	@Autowired
	IVentaService ventaService;
	
	@Autowired
	IItemVentaService itemVentaService;
	
	/**
     * --------------================ METODOS =================--------------------------------
     */
	
	/**
     * -------------- METODOS INTERFACE IInventarioService --------------------------------
     */

	/**
     * @Autor RagooS
     * @Descripccion Metodo permite obtener seleccionar el ultimo inventario de el producto
     * @Fecha 18/02/2022 antes
     */
	@Override
	public Inventario seleccionarUtimoInventarioProducto(Long id) {
		// TODO Auto-generated method stub
		return inventarioDao.seleccionarUtimoInventarioProducto(id);
	}

	@Override
	public Inventario save(Inventario inventario) {
		// TODO Auto-generated method stub
		Inventario inventar = null;
		
		try {
			inventar = inventarioDao.save(inventario);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			inventar = null;
		}
		
		return inventar;
	}

	/**
     * @Autor RagooS
     * @Descripccion Metodo permite realizar el inventario de todos los productos activos
     * @Fecha 18/02/2022
     */
	@Override
	public Boolean realizarInventarioGeneral() {
		// TODO Auto-generated method stub
		Boolean exito = false;
		List<Producto> productosActivos = productoService.findAll_Activo();
		
		if(productosActivos != null && !productosActivos.isEmpty()) {
			for(Producto producto:productosActivos) {
				if(inventariar(producto, null)) {
					exito = true;
				} else {
					exito = false;
				}
			}
		}
		
		return exito;
	}

	/**
	 * @Autor RagooS
     * @Descripccion Metodo permite realizar el inventario de un producto y una venta
     * @Fecha 18/02/2022
	 * @param producto
	 * @param venta
	 * @return
	 */
	@Override
	public boolean inventariar(Producto producto, ItemVenta itemVenta) {
		// TODO Auto-generated method stub
		
		boolean exito = false;
		
		if(producto.getRealiza_inventario().equals("S")) {
			
			Bodega bodegaConsulta = bodegaService.findByIdProducto(producto.getId());
			Inventario inventario = seleccionarUtimoInventarioProducto(producto.getId());
			
			if(inventario != null && bodegaConsulta != null) {
				
				Inventario nuevoInventario = new Inventario();
				
				List<ItemInversion> itemsInversiones = itemInversionService.obtenerItemsInversionesPorProductoMayorAFechaInventario(producto.getId(), inventario.getFecha());
				
				if(itemsInversiones != null && !itemsInversiones.isEmpty()) {
					nuevoInventario.setCant_comprado(agregarInversionProducto(itemsInversiones));
				}else {
					nuevoInventario.setCant_comprado(0.0);
				}
				
				if(itemVenta != null) {
					
					List<ItemVenta> itemsVenta = new ArrayList<>();
					//ItemVenta itemVenta = buscarItemProductoEnVenta(venta, producto);
					
					/*if(itemVenta != null) {
						itemsVenta.add(itemVenta);
					}*/
					itemsVenta.add(itemVenta);
					
					nuevoInventario.setCant_vendido(agregarVentaSinInventario(itemsVenta));
					
					nuevoInventario.setFecha(new Date());
					nuevoInventario.setCant_diferencia((bodegaConsulta.getCant_disponible() + nuevoInventario.getCant_comprado()) - nuevoInventario.getCant_vendido());
					nuevoInventario.setProducto(inventario.getProducto());
					
					nuevoInventario = inventarioDao.save(nuevoInventario);
					
				} else {
					List<ItemVenta> listItemsVenta = itemVentaService.findItemsVentasMayorFechaPorProducto(producto.getId(), inventario.getFecha()); 
					
					if(listItemsVenta != null && !listItemsVenta.isEmpty()) {
						nuevoInventario.setCant_vendido(agregarVentaSinInventario(listItemsVenta));
					} else {
						nuevoInventario.setCant_vendido(0.0);
					}
					
					nuevoInventario.setFecha(new Date());
					nuevoInventario.setCant_diferencia((bodegaConsulta.getCant_disponible() + nuevoInventario.getCant_comprado()) - nuevoInventario.getCant_vendido());
					nuevoInventario.setProducto(inventario.getProducto());
					nuevoInventario = inventarioDao.save(nuevoInventario);
					
					
				}
				
				if(nuevoInventario != null) {
					
					Bodega bodega = guardarBodega(nuevoInventario, bodegaConsulta);
					if(bodega!=null)
						exito = true;
				}	
				
				
			} else {
				inventario = new Inventario();
				
				List<ItemInversion> inversiones = inversionService.findItemsByIdProducto(producto.getId());
				
				if(inversiones != null && !inversiones.isEmpty()) {
					
					inventario.setCant_comprado(agregarInversionProducto(inversiones));
					
					List<ItemVenta> itemsVenta = itemVentaService.findItemsVentasPorProducto(producto.getId());
					
					if(itemsVenta != null && !itemsVenta.isEmpty()) {
						inventario.setCant_vendido(agregarVentaSinInventario(itemsVenta));
					} else {
						inventario.setCant_vendido(0.0);
					}
					
					inventario.setFecha(new Date());
					inventario.setCant_diferencia(inventario.getCant_comprado() - inventario.getCant_vendido());
					inventario.setProducto(producto);
					
					Inventario invent = save(inventario);
					
					if(invent != null) {
						Bodega bodega = guardarBodega(invent, bodegaConsulta);
						
						if(bodega!=null)
							exito = true;
					}
					
				} else {
					exito = true;
				}
			}
			
		} else {
			exito = true;
		}
		
		return exito;
	}
	
	/**
     * -------------- METODOS PROPIOS --------------------------------
     */
	
	/**
	 * @author RagooS
	 * @fecha 03/02/2022
	 * @descripcion Metodo permite extraer el iten de la venta perteneciente al producto
	 * @param venta
	 * @return
	 */
	private ItemVenta buscarItemProductoEnVenta(Venta venta, Producto producto) {
		// TODO Auto-generated method stub
		ItemVenta itemVenta = null;
		for(ItemVenta item: venta.getItems()) {
			if(item.getProducto().getId() == producto.getId()) {
				itemVenta = item;
			}
		}
		
		return itemVenta;
	}

	/**
	 * @author RagooS
	 * @fecha 03/02/2022
	 * @descripcion Metodo permite agregar la cantidad comprada para el inventario
	 * @param inversiones objeto de la clase Inversion el cual se estrae la informacion
	 * @param inventario objeto de la clase Inventario el cual se agrega la informacion
	 * @return
	 */
	private Double agregarInversionProducto(List<ItemInversion> inversiones) {
		// TODO Auto-generated method stub
		
		Double cantidad = 0.0;
		
		for(ItemInversion inversion: inversiones) {
			cantidad += inversion.getCant_comprado();
		}
		
		
		return cantidad;
	}
	
	/**
	 * @author RagooS
	 * @fecha 03/02/2022
	 * @descripcion Metodo permite agregar la cantidad vendida para el inventario
	 * @param ventasSinInventario objeto de la clase Venta el cual se estrae la informacion
	 * @param inventario objeto de la clase Inventario el cual se agrega la informacion
	 * @return
	 */
	
	private Double agregarVentaSinInventario(List<ItemVenta> itemsVenta) {
		// TODO Auto-generated method stub
		Double cantidad = 0.0;
		
		for(ItemVenta item: itemsVenta) {
			cantidad += item.getCant_peso();
		}
		
		
		return cantidad;
	}
	
	/**
	 * @author RagooS
	 * @Descripccion Metodo permite almacenar informacion en la bodega
	 * @fecha 09/02/22 antes
	 * @param nuevoInventario
	 * @return
	 */
	private Bodega guardarBodega(Inventario nuevoInventario, Bodega bodega) {
		// TODO Auto-generated method stub
		
		//Bodega bodega = bodegaService.findByIdProducto(nuevoInventario.getProducto().getId());
		if(bodega != null) {
			//bodega = 
			//Double disponible = bodega.getCant_disponible() - itemSave.getCant_peso();
			bodega.setCant_disponible(nuevoInventario.getCant_diferencia());
		} else {
			bodega = new Bodega();
			
			bodega.setProducto(nuevoInventario.getProducto());
			bodega.setCant_disponible(nuevoInventario.getCant_diferencia());
		}
		
		bodega = bodegaService.save(bodega);
		
		return bodega;
	}

}
