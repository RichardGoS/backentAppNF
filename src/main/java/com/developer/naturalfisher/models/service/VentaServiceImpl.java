package com.developer.naturalfisher.models.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.naturalfisher.models.dao.IVentaDao;
import com.developer.naturalfisher.models.entity.Bodega;
import com.developer.naturalfisher.models.entity.Inventario;
import com.developer.naturalfisher.models.entity.Inversion;
import com.developer.naturalfisher.models.entity.ItemInversion;
import com.developer.naturalfisher.models.entity.ItemPromocionVenta;
import com.developer.naturalfisher.models.entity.ItemVenta;
import com.developer.naturalfisher.models.entity.Promocion;
import com.developer.naturalfisher.models.entity.PromocionVenta;
import com.developer.naturalfisher.models.entity.Venta;
import com.developer.naturalfisher.models.transporte.DetalleVentas;
import com.developer.naturalfisher.utilidades.Utilidades;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 22/06/2021
 */

@Service
public class VentaServiceImpl implements IVentaService {
	
	/**
	 * --------------================ VARIABLES =================--------------------------------
	 */
	
	@Autowired
	IVentaDao ventaDao;
	
	@Autowired
	IItemVentaService itemVenta;
	
	@Autowired
	IBodegaService bodegaService;
	
	@Autowired
	IInventarioService inventarioService;
	
	@Autowired
	IInversionService inversionService;
	
	@Autowired
	IItemInversionService itemInversionService;
	
	@Autowired
	IPromocionVentaService promocionVentaService;
	
	@Autowired
	IItemPromocionVentaService itemPromocionVentaService;
	
	/**
     * --------------================ METODOS =================--------------------------------
     */
	
	/**
     * -------------- METODOS INTERFACE IVentaService --------------------------------
     */

	/**
	 * 
	 */
	@Override
	public Venta save(Venta ventaNew) {
		// TODO Auto-generated method stub
		
		Venta ventaFinal = null;
		boolean exito = false;
		
		if(ventaNew != null) {
			ventaNew.setFecha(new Date());
			System.out.println("#### ANTES DE REGISTRAR LA VENTA ####");
			Venta ventaRegistrada = ventaDao.save(ventaNew);
			System.out.println("#### DESPUES DE REGISTRAR LA VENTA ####");
			ventaFinal = ventaRegistrada;
			
			if(ventaRegistrada != null && (ventaRegistrada.getItems() != null && !ventaRegistrada.getItems().isEmpty())) {
				for(ItemVenta item : ventaRegistrada.getItems()) {
					
					System.out.println("#### INICIA PROCESO REGISTRO DE ITEMS VENTA ####");
					
					Venta vVacia = new Venta();
					vVacia.setId(ventaRegistrada.getId());
					item.setVenta(vVacia);
					
					if(item.getProducto() != null ) {
						System.out.println("#### PRODUCTO ####");
						
						System.out.println("#### ANTES DE ALMACENAR EL ITEMVENTA PARA VENTA PRODUCTO ####");
						ItemVenta itemSave = itemVenta.save(item);
						System.out.println("#### DESPUES DE ALMACENAR EL ITEMVENTA PARA VENTA PRODUCTO ####");
						
						if(itemSave != null) {
							System.out.println("#### ITEMVENTA PARA VENTA PRODUCTO ALMACENADO CON EXITO ####");
							
							System.out.println("#### ANTES DE LLAMAR METODO INVENTARIAR ####");
							exito = inventarioService.inventariar(itemSave.getProducto(), itemSave, null);
							System.out.println("#### DESPUES DE LLAMAR METODO INVENTARIAR ####");
						} else {
							System.out.println("#### ITEMVENTA PARA VENTA PRODUCTO NO ALMACENADO *** ERRROR *** ####");
						}
					} else if(item.getPromocion_venta() != null) {
						System.out.println("#### PROMOCION ####");
						
						PromocionVenta promocionVentaVacia = new PromocionVenta();
						promocionVentaVacia.setPromocion(item.getPromocion_venta().getPromocion());
						promocionVentaVacia.setTotal(item.getPromocion_venta().getTotal());
						promocionVentaVacia.setGanancia(item.getPromocion_venta().getGanancia());
						promocionVentaVacia.setTotalCalculado(item.getPromocion_venta().getTotalCalculado());
						promocionVentaVacia.setPorcentage(item.getPromocion_venta().getPorcentage());
						//promocionVentaVacia.setPromocionVentas(item.getPromocion_venta().getPromocionVentas());
						
						System.out.println("#### ANTES DE ALMACENAR LA PROMOCION VENTA ####");
						PromocionVenta promocionVentaAlmacenada = promocionVentaService.save(promocionVentaVacia);
						System.out.println("#### DESPUES DE ALMACENAR LA PROMOCION VENTA ####");
						
						if(promocionVentaAlmacenada != null) {
							
							System.out.println("#### PROMOCION VENTA ALMACENADA CON EXITO ####");
							
							if(item.getPromocion_venta().getItems_promocion() != null && !item.getPromocion_venta().getItems_promocion().isEmpty()) {
								
								for(ItemPromocionVenta itemPromo:item.getPromocion_venta().getItems_promocion()) {
									
									System.out.println("#### INICIA PROCESO REGISTRO DE ITEMS PROMOCION VENTA ####");
									
									ItemPromocionVenta itemPromocionVenta = new ItemPromocionVenta();
									itemPromocionVenta.setProducto(itemPromo.getProducto());
									itemPromocionVenta.setPromocion_venta(promocionVentaAlmacenada);
									
									System.out.println("#### SE VA A CALCULAR EL PRECIO VENTA REAL DEL ITEM PARA EL PRODUCTO ####");
									
									double porcenProductoEnPromo = 0.0;
									double precioTotalVentaReal = 0.0;
									double precioVentaReal = 0.0;
									double ganancia = 0.0;
									
									System.out.println("#### ANTES DE CALCULAR porcenProductoEnPromo: " + " ####");
									if(promocionVentaAlmacenada.getGanancia() >= 0) {
										porcenProductoEnPromo = itemPromo.getTotal() / promocionVentaAlmacenada.getTotalCalculado();
									} else {
										porcenProductoEnPromo = itemPromo.getTotal() / promocionVentaAlmacenada.getTotal();
									}
									
									precioTotalVentaReal = promocionVentaAlmacenada.getTotal() * porcenProductoEnPromo;
									precioVentaReal = precioTotalVentaReal / itemPromo.getCant_peso();
									
									ganancia = precioTotalVentaReal - itemPromo.getTotal();
									
									System.out.println("#### CALCULADO porcenProductoEnPromo: " + porcenProductoEnPromo + "% ####");
									System.out.println("#### CALCULADO precioTotalVentaReal: $" + precioTotalVentaReal + " ####");
									System.out.println("#### CALCULADO precioVentaReal: $" + precioVentaReal + " ####");
									System.out.println("#### CALCULADO ganancia: $" + ganancia + " ####");
									
									itemPromocionVenta.setPorcentage(porcenProductoEnPromo);
									itemPromocionVenta.setPrecio_venta_calculado(precioVentaReal);
									itemPromocionVenta.setTotal_calculado(itemPromo.getTotal());
									itemPromocionVenta.setGanancia(ganancia);
									
									itemPromocionVenta.setPrecio_venta(itemPromo.getPrecio_venta());
									itemPromocionVenta.setCant_peso(itemPromo.getCant_peso());
									itemPromocionVenta.setTotal(precioTotalVentaReal);
									
									System.out.println("#### ANTES DE ALMACENAR EL ITEM PROMOCION VENTA ####");
									ItemPromocionVenta itemPromocionVentaAlmacenada = itemPromocionVentaService.save(itemPromocionVenta);
									System.out.println("#### DESPUES DE ALMACENAR EL ITEM PROMOCION VENTA ####");
									
									if(itemPromocionVentaAlmacenada!=null) {
										System.out.println("#### ITEM PROMOCION VENTA ALMACENADA CON EXITO ####");
										
										System.out.println("#### ANTES DE LLAMAR A INVENTARIAR ####");
										exito = inventarioService.inventariar(itemPromo.getProducto(), null, itemPromo);
										System.out.println("#### DESPUES DE LLAMAR A INVENTARIAR ####");
										
									} else {
										exito = false;
										System.out.println("#### ERROR ITEM PROMOCION VENTA NO ALMACENADA ####");
									}
								}
								
							}
							
						}else {
							System.out.println("#### ERROR PROMOCION VENTA NO ALMACENADA ####");
							exito = false;
						}
						
						
						if(exito) {
							item.setPromocion_venta(promocionVentaAlmacenada);
							
							System.out.println("#### ANTES DE ALMACENAR EL ITEMVENTA PARA VENTA PROMOCION ####");
							
							ItemVenta itemSave = itemVenta.save(item);
							
							System.out.println("#### DESPUES DE ALMACENAR EL ITEMVENTA PARA VENTA PROMOCION ####");
							
							if(itemSave != null) {
								System.out.println("#### ITEMVENTA PARA VENTA PROMOCION ALMACENADO CON EXITO ####");
							} else {
								System.out.println("#### ITEMVENTA PARA VENTA PROMOCION NO ALMACENADO *** ERORR *** ####");
								exito = false;
							}
						}
						
					}
					
				}
			} else {
				System.out.println("#### ERROR NO EXISTE VENTA O ITEMS_VENTAS ####");
				ventaFinal = null;
				exito = false;
			}
			
		}
		
		if(!exito) {
			ventaFinal = null;
			System.out.println("#### VENTA REALIZADA CON ERRORES ####");
		} else {
			System.out.println("#### VENTA REALIZADA CON EXITO ####");
		}
		
		return ventaFinal;
	}


	/**
	 * 
	 */
	@Override
	public List<Venta> findAll() {
		// TODO Auto-generated method stub
		return ventaDao.findAll();
	}

	/**
	 * 
	 */
	@Override
	public Venta findById(Long id) {
		// TODO Auto-generated method stub
		return ventaDao.findById(id).orElse(null);
	}

	
	/**
	 * @author RagooS
	 * @Descripccion Metodo permite obtener ventas en la fecha por default
	 * @fecha 26/07/21
	 * @param fecha String cadena de texto que contiene la fecha
	 * @return ventas List<Venta> lista de ventas obtenidas en la consulta
	 */
	@Override
	public DetalleVentas ventasRangoFechaDefault() {
		// TODO Auto-generated method stub
		
		List<Date> fechas = Utilidades.restarFechaActual();
		DetalleVentas detalleVentas = new DetalleVentas();
		
		if(fechas != null && !fechas.isEmpty()) {
			detalleVentas.setVentas( ventaDao.consultaRangoFechaDefault(fechas.get(1), fechas.get(0)));
			detalleVentas.setCantVentas(detalleVentas.getVentas().size());
			detalleVentas.setTotal(ventaDao.consultarTotalVentaEnRangoFecha(fechas.get(1), fechas.get(0)));
			detalleVentas.setFecha("" + Utilidades.formatearFechaDateString(fechas.get(1)) + " || " + Utilidades.formatearFechaDateString(fechas.get(0)));
			
			return detalleVentas;
		} else {
			return null;
		}
		
	}

	/**
	 * @author RagooS
	 * @Descripccion Metodo permite obtener ventas en la fecha exacta
	 * @fecha 26/07/21
	 * @param fecha String cadena de texto que contiene la fecha
	 * @return ventas List<Venta> lista de fechas obtenidas en la consulta
	 */
	@Override
	public List<Venta> ventasEnFecha(String fecha) {
		// TODO Auto-generated method stub
				
		List<Venta> ventas = null;
		
		List<Date> fechas = Utilidades.obtenerRangoFechaDia(fecha);
		
		if(fechas != null && !fechas.isEmpty()) {
			ventas = ventaDao.consultaVentasEnFecha(fechas.get(0), fechas.get(1) );
		}
		
		return ventas;
	}

	/**
	 * @author RagooS
	 * @Descripccion Metodo permite obtener ventas en el mes
	 * @fecha 20/01/2022
	 * @param fecha String cadena de texto que contiene la fecha
	 * @return ventas List<Venta> lista de fechas obtenidas en la consulta
	 */
	@Override
	public List<Venta> ventasEnMes(String fecha) {
		// TODO Auto-generated method stub
		
		System.out.println("#### INICIA METODO ventasEnMes() PARA CONSULTAR VENTAS EN MES AÑO EN LA BD  ####");
		
		String mount = "";
		String year = "";
		
		String[] fechArr = Utilidades.estraerMesAño(fecha);
		
		if(fechArr != null && fechArr.length > 1) {
			year = fechArr[0];
			mount = fechArr[1];
			
			if( !mount.equals("") && !year.equals("")) {
				
				System.out.println("#### SE VA A LLAMAR AL METODO DE CONSULTA CON LOS DATOS  --> mes: "  +  mount + "  año: " + year + "  ####");
				
				return ventaDao.consultaVentasEnMesAño(Integer.parseInt(mount), Integer.parseInt(year));
			}
			
		} else {
			System.out.println("#### ERROR: NO FUE POSIBLE CAPTURAR EL MES Y AÑO PARA LA CONSULTA  ####");
		}
		
		return null;
	}

	/**
	 * @author RagooS
	 * @Descripccion Metodo permite obtener total $$ ventas en el mes
	 * @fecha 20/01/2022
	 * @param fecha String cadena de texto que contiene la fecha
	 * @return Double Valor en pesos de las ventas en la fecha
	 */
	@Override
	public Double consultarTotalVentaMesAño(String fecha) {
		// TODO Auto-generated method stub
		
		String mount = "";
		String year = "";
		
		if(fecha.contains("/")) {
			String[] fechArr = fecha.split("/"); 
			year = fechArr[0];
			mount = fechArr[1];
			
			if( !mount.equals("") && !year.equals("")) {
				return ventaDao.consultarTotalVentaMesAño(Integer.parseInt(mount), Integer.parseInt(year));
			}
			
		}
		
		return null;
	}
	
	
	/**
     * -------------- METODOS PROPIOS --------------------------------
     */
	
	/**
	 * @author RagooS
	 * @fecha 03/02/2022
	 * @descripcion Metodo permite agregar la cantidad vendida para el inventario
	 * @param ventasSinInventario objeto de la clase Venta el cual se estrae la informacion
	 * @param inventario objeto de la clase Inventario el cual se agrega la informacion
	 * @return
	 */
	
	private Inventario agregarVentaSinInventario(List<ItemVenta> itemsVenta, Inventario inventario) {
		// TODO Auto-generated method stub
		Double cantidad = 0.0;
		
		if( inventario == null ) {
			inventario = new Inventario();
		}
		
		for(ItemVenta item: itemsVenta) {
			cantidad += item.getCant_peso();
		}
		
		//cantidad += inventario.getCant_vendido();
		inventario.setCant_vendido(cantidad);
		
		return inventario;
	}

	/**
	 * @author RagooS
	 * @fecha 03/02/2022
	 * @descripcion Metodo permite agregar la cantidad comprada para el inventario
	 * @param inversiones objeto de la clase Inversion el cual se estrae la informacion
	 * @param inventario objeto de la clase Inventario el cual se agrega la informacion
	 * @return
	 */
	private Inventario agregarInversionProducto(List<ItemInversion> inversiones) {
		// TODO Auto-generated method stub
		Inventario inventar = new Inventario();
		
		Double cantidad = 0.0;
		
		for(ItemInversion inversion: inversiones) {
			cantidad += inversion.getCant_comprado();
		}
		
		//cantidad += inventario.getCant_comprado();
		inventar.setCant_comprado(cantidad);
		
		return inventar;
	}
	
	/**
	 * @author RagooS
	 * @Descripccion Metodo permite obtener la informacion completa de la inversion
	 * @fecha 26/07/21
	 * @param inversiones
	 * @return
	 */
	private List<Inversion> obtenerItemsInversion(List<Inversion> inversiones) {
		// TODO Auto-generated method stub
		List<Inversion> inversionesReturn = new ArrayList<>();
		
		if(inversiones != null && !inversiones.isEmpty()) {
			for(Inversion inversion: inversiones) {
				Inversion inver = inversionService.findById(inversion.getId());
				
				if(inver != null) {
					inversionesReturn.add(inver);
				}
				
			}
		}
		
		return inversionesReturn;
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


	/**
	 * @author RagooS
	 * @Descripccion Metodo permite consultar las ventas despues de la fecha
	 * @fecha 09/02/22 antes
	 * @param fecha
	 * @return
	 */
	@Override
	public List<Venta> consultarVentasDespuesFecha(Date fecha) {
		// TODO Auto-generated method stub
		return ventaDao.consultarVentasDespuesFecha(fecha);
	}


	/**
	 * @author RagooS
	 * @Descripccion Metodo permite consultar las ventas en el mes
	 * @fecha 10/03/22
	 * @param fecha
	 * @return
	 */
	@Override
	public DetalleVentas ventasDetalleEnMes(String fecha) {
		// TODO Auto-generated method stub
		
		List<Venta> ventas = ventasEnMes(fecha);
		DetalleVentas detalleVentas = new DetalleVentas();
		
		if(ventas != null && !ventas.isEmpty()) {
			
			detalleVentas.setFecha("Este Mes");
			detalleVentas.setVentas(ventas);
			detalleVentas.setTotal(consultarTotalVentaMesAño(fecha));
			detalleVentas.setCantVentas(ventas.size());
		} else {
			detalleVentas = null;
		}
		
		
		
		return detalleVentas;
	}



}
