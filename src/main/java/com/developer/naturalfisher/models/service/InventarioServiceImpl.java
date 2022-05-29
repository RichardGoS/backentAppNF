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
import com.developer.naturalfisher.models.entity.ItemPromocionVenta;
import com.developer.naturalfisher.models.entity.ItemVenta;
import com.developer.naturalfisher.models.entity.Producto;
import com.developer.naturalfisher.models.entity.Promocion;
import com.developer.naturalfisher.models.entity.PromocionVenta;
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
	
	@Autowired
	IPromocionService promocionService;
	
	@Autowired
	IPromocionVentaService promocionVentaService;
	
	@Autowired
	IItemPromocionVentaService iItemPromocionVentaService;
	
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
		System.out.println("#### INICIA EL METODO save() PARA ALMACENAR EL INVENTARIO  ####");
		Inventario inventar = null;
		
		try {
			System.out.println("#### ALMACENANDO INVENTARIO  ####");
			inventar = inventarioDao.save(inventario);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("#### ERROR AL ALMACENAR EL INVENTARIO  ####");
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
		List<Producto> productosActivos = null;
		List<Promocion> promocionesActivas = null;
		
		System.out.println("#### INICIA EL PROCESO INVENTARIO GENERAL ####");

		System.out.println("#### INICIA PROCESO INVENTARIO PRODUCTOS ####");
		
		System.out.println("#### ANTES DE CONSULTAR LOS PRODUCTOS ACTIVOS ####");
		productosActivos = productoService.findAll_Activo();
		System.out.println("#### DESPUES DE CONSULTAR LOS PRODUCTOS ACTIVOS ####");
		
		if(productosActivos != null && !productosActivos.isEmpty()) {
			System.out.println("#### SE ENCONTRARON #" + productosActivos.size() + " PRODUCTOS ACTIVOS ####");
			for(Producto producto:productosActivos) {
				System.out.println("#### ANTES DE LLAMAR EL METODO INVENTARIAR ####");
				exito = inventariar(producto, null, null);
				System.out.println("#### DESPUES DE LLAMAR EL METODO INVENTARIAR ####");
			}
		} else {
			System.out.println("#### NO SE ENCONTRARON PRODUCTOS ACTIVOS ####");
		}
		
		System.out.println("#### FINALIZA PROCESO INVENTARIO GENERAL ####");
		
		System.out.println("#### EXITO: " + exito + " ####");
		
		/*
		// ----------------------------- PROMOCIONES --------------------------------------
		
		System.out.println("#### INICIA PROCESO INVENTARIO PROMOCIONES ####");
		
		System.out.println("#### ANTES DE CONSULTAR LAS PROMOCIONES ACTIVAS ####");
		promocionesActivas = promocionService.findAll_estadoActivo();
		System.out.println("#### DESPUES DE CONSULTAR LAS PROMOCIONES ACTIVAS ####");
		
		if(promocionesActivas != null && !promocionesActivas.isEmpty()) {
			System.out.println("#### SE ENCONTRARON #" + promocionesActivas.size() + " PROMOCIONES ACTIVAS ####");
			for(Promocion promocion: promocionesActivas) {
				
				System.out.println("#### ANTES DE CONSULTAR LAS VENTAS REALIZADAS DE LA PROMOCION ####");
				List<PromocionVenta> promocionVentas = promocionVentaService.findByIdPromocion(promocion.getId());
				System.out.println("#### DESPUES DE CONSULTAR LAS VENTAS REALIZADAS DE LA PROMOCION ####");
				
				if(promocionVentas != null && !promocionVentas.isEmpty()) {
					System.out.println("#### SE ENCONTRARON #" + promocionVentas.size() + " VENTAS DE PROMOCIONES REALIZADAS DE LA PROMOCION ####");
					
					for(PromocionVenta promocionVenta: promocionVentas) {
						if(promocionVenta.getItems_promocion() != null && !promocionVenta.getItems_promocion().isEmpty()) {
							for(ItemPromocionVenta itemPromocionVenta : promocionVenta.getItems_promocion()) {
								System.out.println("#### ANTES DE LLAMAR EL PROCESO INVENTARIAR PROMOCION ####");
								exito = inventariarPromocion(itemPromocionVenta);
								System.out.println("#### DESPUES DE LLAMAR EL PROCESO INVENTARIAR PROMOCION ####");
							}
						}
					}
					
				}
			}
		} else {
			System.out.println("#### NO SE ENCONTRARON PROMOCIONES ACTIVAS ####");
		}
		*/
		
		
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
	public boolean inventariar(Producto producto, ItemVenta itemVenta, ItemPromocionVenta itemPromocionVenta) {
		// TODO Auto-generated method stub
		System.out.println("#### INICIA EL METODO INVENTARIAR(inventariar()) ####");
		boolean exito = false;
		
		if(producto.getRealiza_inventario().equals("S")) {
			
			System.out.println("#### ANTES DE CONSULTAR EN BODEGA EL PRODUCTO CON ID:" + producto.getId() + " ####");
			Bodega bodegaConsulta = bodegaService.findByIdProducto(producto.getId());
			System.out.println("#### DESPUES DE CONSULTAR EN BODEGA EL PRODUCTO  ####");
			
			System.out.println("#### ANTES DE CONSULTAR EN INVENTARIO EL PRODUCTO CON ID:" + producto.getId() + " ####");
			Inventario inventario = seleccionarUtimoInventarioProducto(producto.getId());
			System.out.println("#### DESPUES DE CONSULTAR EN INVENTARIO EL PRODUCTO  ####");
			
			if(inventario != null && bodegaConsulta != null) {
				
				System.out.println("#### SE ENCONTRO BODEGA E INVENTARIO DEL PRODUCTO  ####");
				
				Inventario nuevoInventario = new Inventario();
				
				System.out.println("#### ANTES DE CONSULTAR LAS INVERCIONES DEL PRODUCTO DESPUES DE FECHA INVENTARIO  ####");
				List<ItemInversion> itemsInversiones = itemInversionService.obtenerItemsInversionesPorProductoMayorAFechaInventario(producto.getId(), inventario.getFecha());
				System.out.println("#### DESPUES DE CONSULTAR LAS INVERCIONES DEL PRODUCTO DESPUES DE FECHA INVENTARIO  ####");
				
				Double cantidadComprado = 0.0;
				Double cantidadVendido = 0.0;
				
				if(itemsInversiones != null && !itemsInversiones.isEmpty()) {
					System.out.println("#### SE ENCONTRARON INVERSIONES  ####");
					cantidadComprado = agregarInversionProducto(itemsInversiones);
					//nuevoInventario.setCant_comprado(agregarInversionProducto(itemsInversiones));
				}
				
				if(cantidadComprado > 0) {
					nuevoInventario.setCant_comprado(cantidadComprado);
				} else {
					nuevoInventario.setCant_comprado(0.0);
				}
				
				if(itemVenta != null) {
					System.out.println("#### SE ENCONTRO ITEM_VENTA  ####");
					
					List<ItemVenta> itemsVenta = new ArrayList<>();
					itemsVenta.add(itemVenta);
					
					if(itemVenta.getProducto() != null) {
						cantidadVendido = agregarVentaSinInventario(itemsVenta);
					}

					
				} else if( itemPromocionVenta != null){
					System.out.println("#### SE ENCONTRO ITEM_PROMOCION_VENTA  ####");
					
					List<ItemPromocionVenta> itemsPromo = new ArrayList<>();
					
					itemsPromo.add(itemPromocionVenta);
					
					cantidadVendido = agregarVentaSinInventarioPromocion(itemsPromo);
					
					
				} else {
					
					System.out.println("#### NO SE ENCONTRO ITEM_VENTA NI ITEM_PROMOCION_VENTA  ####");
					
					System.out.println("#### ANTES DE LLAMAR AL METODO BUSCAR VENTAS MAYOR A FECHA POR PRODUCTO  ####");
					List<ItemVenta> listItemsVenta = itemVentaService.findItemsVentasMayorFechaPorProducto(producto.getId(), inventario.getFecha()); 
					System.out.println("#### DESPUES DE LLAMAR AL METODO BUSCAR VENTAS MAYOR A FECHA POR PRODUCTO  ####");
					
					
					if(listItemsVenta != null && !listItemsVenta.isEmpty()) {
						System.out.println("#### SE ENCONTRARON VENTAS  ####");
						cantidadVendido = agregarVentaSinInventario(listItemsVenta);
					} else {
						System.out.println("#### NO SE ENCONTRARON VENTAS  ####");
					}
					
					System.out.println("#### ANTES DE LLAMAR AL METODO BUSCAR VENTAS CON PROMOCION MAYOR A FECHA POR PRODUCTO  ####");
					List<ItemPromocionVenta> itemsPromoVenta = itemVentaService.obtenerItemsVentasMayorAFechaConPromocionPorProducto(inventario.getFecha(), producto.getId());
					System.out.println("#### DESPUES DE LLAMAR AL METODO BUSCAR VENTAS CON PROMOCION MAYOR A FECHA POR PRODUCTO  ####");
					
					if(itemsPromoVenta != null && !itemsPromoVenta.isEmpty()) {
						System.out.println("#### SE ENCONTRARON VENTAS CON PROMOCION  ####");
						cantidadVendido += agregarVentaSinInventarioPromocion(itemsPromoVenta);
					} else {
						System.out.println("#### NO SE ENCONTRARON VENTAS CON PROMOCION  ####");
					}
					
				}
				
				
				if(cantidadVendido > 0) {
					nuevoInventario.setCant_vendido(cantidadVendido);
				} else {
					nuevoInventario.setCant_vendido(0.0);
				}
				
				nuevoInventario.setFecha(new Date());
				nuevoInventario.setCant_diferencia((bodegaConsulta.getCant_disponible() + nuevoInventario.getCant_comprado()) - nuevoInventario.getCant_vendido());
				nuevoInventario.setProducto(inventario.getProducto());
				
				System.out.println("#### ANTES DE ALMACENAR EL INVENTARIO  ####");
				nuevoInventario = inventarioDao.save(nuevoInventario);
				System.out.println("#### DESPUES DE ALMACENAR EL INVENTARIO  ####");
				
				
				if(nuevoInventario != null) {
					System.out.println("#### SE ALMACENO EL INVENTARIO CON EXITO  ####");
					
					System.out.println("#### ANTES DE ALMACENAR LA BODEGA  ####");
					Bodega bodega = guardarBodega(nuevoInventario, bodegaConsulta);
					System.out.println("#### DESPUES DE ALMACENAR LA BODEGA  ####");
					
					if(bodega!=null) {
						System.out.println("#### SE ALMACENO LA BODEGA CON EXITO  ####");
						exito = true;
					} else {
						System.out.println("#### ERROR AL ALMACENAR LA BODEGA  ####");
					}
						
				} else {
					System.out.println("#### ERROR AL ALMACENAR EL INVENTARIO  ####");
				}	
				
				
			} else {
				
				Double cantidadVendido = 0.0;
				Double cantidadComprado = 0.0;
				
				System.out.println("#### NO SE ENCONTRO BODEGA NI INVENTARIO DEL PRODUCTO  ####");
				
				inventario = new Inventario();
				
				System.out.println("#### ANTES DE LLAMAR AL METODO BUSCAR INVERSIONES POR PRODUCTO  ####");
				List<ItemInversion> inversiones = inversionService.findItemsByIdProducto(producto.getId());
				System.out.println("#### DESPUES DE LLAMAR AL METODO BUSCAR INVERSIONES POR PRODUCTO  ####");
				
				if(inversiones != null && !inversiones.isEmpty()) {
					
					System.out.println("#### SE ENCONTRARON INVERSIONES DEL PRODUCTO  ####");
					
					
					cantidadComprado = agregarInversionProducto(inversiones);
					
					if(cantidadComprado > 0) {
						inventario.setCant_comprado(cantidadComprado);
					} else {
						inventario.setCant_comprado(0.0);
					}
					
					System.out.println("#### ANTES DE LLAMAR AL METODO VENTAS REALIZADAS POR PRODUCTO  ####");
					List<ItemVenta> itemsVenta = itemVentaService.findItemsVentasPorProducto(producto.getId());
					System.out.println("#### DESPUES DE LLAMAR AL METODO VENTAS REALIZADAS POR PRODUCTO  ####");
					
					if(itemsVenta != null && !itemsVenta.isEmpty()) {
						System.out.println("#### SE ENCONTRARON VENTAS REALIZADAS AL PRODUCTO  ####");
						cantidadVendido += agregarVentaSinInventario(itemsVenta);
					}
					
					System.out.println("#### ANTES DE LLAMAR AL METODO VENTAS PROMOCIONES REALIZADAS POR PRODUCTO  ####");
					List<ItemPromocionVenta> itemsPromociones = iItemPromocionVentaService.obtenerItemsPromocionVentasPorProducto(producto.getId());
					System.out.println("#### DESPUES DE LLAMAR AL METODO VENTAS PROMOCIONES REALIZADAS POR PRODUCTO  ####");
					
					if(itemsPromociones != null && !itemsPromociones.isEmpty()) {
						System.out.println("#### SE ENCONTRARON VENTAS PROMOCIONES REALIZADAS AL PRODUCTO  ####");
						cantidadVendido += agregarVentaSinInventarioPromocion(itemsPromociones);
					}
					
					
					if(cantidadVendido > 0) {
						inventario.setCant_vendido(cantidadVendido);
					} else {
						inventario.setCant_vendido(0.0);
					}
					
					inventario.setFecha(new Date());
					inventario.setCant_diferencia(inventario.getCant_comprado() - inventario.getCant_vendido());
					inventario.setProducto(producto);
					
					System.out.println("#### ANTES DE LLAMAR EL METODO ALMACENAR INVENTARIO  ####");
					Inventario invent = save(inventario);
					System.out.println("#### DESPUES DE LLAMAR EL METODO ALMACENAR INVENTARIO  ####");
					
					if(invent != null) {
						System.out.println("#### EL INVENTARIO SE ALMACENO CON EXITO  ####");
						
						System.out.println("#### ANTES DE LLAMAR EL METODO ALMACENAR BODEGA  ####");
						Bodega bodega = guardarBodega(invent, bodegaConsulta);
						System.out.println("#### DESPUES DE LLAMAR EL METODO ALMACENAR BODEGA  ####");
						
						if(bodega!=null) {
							System.out.println("#### LA BODEGA SE HA ALMACENADO CON EXITO  ####");
							exito = true;
						} else {
							System.out.println("#### ERROR AL ALMACENAR LA BODEGA  ####");
						}
							
					} else {
						System.out.println("#### ERROR AL ALMACENAR EL INVENTARIO  ####");
					}
					
				} else {
					System.out.println("#### NO SE ENCONTRARON INVERSIONES DEL PRODUCTO  ####");
					exito = false;
				}
			}
			
		} else {
			exito = true;
			System.out.println("#### EL PRODUCTO NO REALIZA INVENTARIO ####");
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
		
		System.out.println("#### INICIA EL METODO ALMACENAR BODEGA  ####");
		
		if(bodega != null) {
			//bodega = 
			//Double disponible = bodega.getCant_disponible() - itemSave.getCant_peso();
			bodega.setCant_disponible(nuevoInventario.getCant_diferencia());
		} else {
			bodega = new Bodega();
			
			bodega.setProducto(nuevoInventario.getProducto());
			bodega.setCant_disponible(nuevoInventario.getCant_diferencia());
		}
		
		System.out.println("#### ALMACENANDO BODEGA  ####");
		bodega = bodegaService.save(bodega);
		
		return bodega;
	}

	
	/**
	 * @author RagooS
	 * @fecha 18/05/2022
	 * @descripcion Metodo permite sumar la cantidad vendida para el inventario
	 * @param items Lista de objetos de la clase ItemPromocionVenta el cual se estrae la informacion
	 * @return
	 */
	
	private Double agregarVentaSinInventarioPromocion(List<ItemPromocionVenta> items) {
		// TODO Auto-generated method stub
		Double cantidad = 0.0;
		
		for(ItemPromocionVenta item: items) {
			cantidad += item.getCant_peso();
		}
		
		return cantidad;
	}
	
	
	/**
	 * @Autor RagooS
     * @Descripccion Metodo permite realizar el inventario de un producto y una venta promocion
     * @Fecha 17/05/2022
	 * @param producto 
	 * @param itemVenta
	 * @return true si fue exitoso de lo contrario false
	 */
	@Override
	public boolean inventariarPromocion(ItemPromocionVenta itemVentaPromocion) {
		// TODO Auto-generated method stub
		
		boolean exito = false;
		Producto producto = itemVentaPromocion.getProducto();
		
		if(producto != null) {
			
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
					
				} else {
					
					inventario = new Inventario();
					
					List<ItemInversion> inversiones = inversionService.findItemsByIdProducto(producto.getId());
					
					if(inversiones != null && !inversiones.isEmpty()) {
						
						inventario.setCant_comprado(agregarInversionProducto(inversiones));
						
						List<ItemPromocionVenta> itemsPromo = iItemPromocionVentaService.obtenerItemsPromocionVentasPorProducto(producto.getId());
						
						if(itemsPromo != null && !itemsPromo.isEmpty()) {
							inventario.setCant_vendido(agregarVentaSinInventarioPromocion(itemsPromo));
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
						
					} 
					
				}
				
			} 
			
		}
		
		return exito;
	}

}
