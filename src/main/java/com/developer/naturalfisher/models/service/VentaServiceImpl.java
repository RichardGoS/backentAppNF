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
import com.developer.naturalfisher.models.entity.ItemVenta;
import com.developer.naturalfisher.models.entity.Venta;
import com.developer.naturalfisher.models.obj.DetalleVentas;
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
			Venta ventaRegistrada = ventaDao.save(ventaNew);
			ventaFinal = ventaRegistrada;
			
			if(ventaRegistrada != null && (ventaRegistrada.getItems() != null && !ventaRegistrada.getItems().isEmpty())) {
				for(ItemVenta item : ventaRegistrada.getItems()) {
					Venta vVacia = new Venta();
					vVacia.setId(ventaRegistrada.getId());
					item.setVenta(vVacia);
					ItemVenta itemSave = itemVenta.save(item);
					if(itemSave != null) {
						
						exito = inventarioService.inventariar(itemSave.getProducto(), itemSave);
						
						/*Bodega bodegaConsulta = bodegaService.findByIdProducto(item.getProducto().getId());
						
						
						
						Inventario inventario = inventarioService.seleccionarUtimoInventarioProducto(item.getProducto().getId());
						
						
						if(inventario != null && bodegaConsulta != null) {
							
							Double diferencia = bodegaConsulta.getCant_disponible() - item.getCant_peso();
							
							if(diferencia >= 0) {
								
								Inventario nuevoInventario = new Inventario();
								
								List<ItemInversion> itemsInversiones = itemInversionService.obtenerItemsInversionesPorProductoMayorAFechaInventario(item.getProducto().getId(), inventario.getFecha());
								
								if(itemsInversiones != null && !itemsInversiones.isEmpty()) {
									nuevoInventario = agregarInversionProducto(itemsInversiones);
								}else {
									nuevoInventario.setCant_comprado(0.0);
								}
								/*
								List<Venta> ventasSinInventario = ventaDao.consultarVentasDespuesFechaInventarioDiferenteVentaRealizada(inventario.getFecha(), ventaRegistrada.getId());
								
								if(ventasSinInventario != null && !ventasSinInventario.isEmpty()) {
									inventario = agregarVentaSinInventario(ventasSinInventario, inventario);
								}*/
								
								//inventario.setCant_vendido(inventario.getCant_vendido() + item.getCant_peso());
								
								//inventario.setCant_diferencia(inventario.getCant_comprado() - inventario.getCant_vendido());
							
								
								
								//inventario.setId(null);
								//nuevoInventario.setId(null);
								//nuevoInventario.setCant_comprado(nuevoInventario.getCant_comprado());
								
								/*nuevoInventario.setFecha(new Date());
								nuevoInventario.setCant_vendido(item.getCant_peso());
								nuevoInventario.setCant_diferencia((bodegaConsulta.getCant_disponible() + nuevoInventario.getCant_comprado()) - nuevoInventario.getCant_vendido());
								nuevoInventario.setProducto(inventario.getProducto());
								
								/*nuevoInventario = inventarioService.save(nuevoInventario);
								
								if(nuevoInventario != null) {
									
									Bodega bodega = guardarBodega(nuevoInventario, bodegaConsulta);
									if(bodega!=null)
										exito = true;
								}	
								
							}
							
							
						} else {
							
							inventario = new Inventario();
							
							List<ItemInversion> inversiones = inversionService.findItemsByIdProducto(itemSave.getProducto().getId());
							
							//inversiones = obtenerItemsInversion(inversiones);
							
							/*if(inversiones != null && !inversiones.isEmpty()) {
								
								inventario = agregarInversionProducto(inversiones);
								
								List<ItemVenta> itemsVenta = itemVenta.findItemsVentasPorProducto(itemSave.getProducto().getId());
								
								if(itemsVenta != null && !itemsVenta.isEmpty()) {
									inventario = agregarVentaSinInventario(itemsVenta, inventario);
								}
								
								inventario.setFecha(new Date());
								inventario.setCant_diferencia(inventario.getCant_comprado() - inventario.getCant_vendido());
								inventario.setProducto(itemSave.getProducto());
								
								Inventario invent = inventarioService.save(inventario);
								
								if(invent != null) {
									Bodega bodega = guardarBodega(invent, bodegaConsulta);
									
									if(bodega!=null)
										exito = true;
								}
								
							}
						}*/
					}
				}
			} else {
				ventaFinal = null;
				exito = false;
			}
			
		}
		
		if(!exito) {
			ventaFinal = null;
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
		
		String mount = "";
		String year = "";
		
		if(fecha.contains("/")) {
			String[] fechArr = fecha.split("/"); 
			year = fechArr[0];
			mount = fechArr[1];
			
			if( !mount.equals("") && !year.equals("")) {
				return ventaDao.consultaVentasEnMesAño(Integer.parseInt(mount), Integer.parseInt(year));
			}
			
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
