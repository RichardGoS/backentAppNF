package com.developer.naturalfisher.models.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.naturalfisher.models.dao.IEstadisticasMesProductoDao;
import com.developer.naturalfisher.models.entity.EstadisticasMesProducto;
import com.developer.naturalfisher.models.entity.ItemPromocionVenta;
import com.developer.naturalfisher.models.entity.ItemVenta;
import com.developer.naturalfisher.models.entity.Producto;
import com.developer.naturalfisher.models.entity.Promocion;
import com.developer.naturalfisher.models.transporte.DetalleEstadistica;
import com.developer.naturalfisher.utilidades.Utilidades;

/**
 * Fase 4 Tarea 1
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 01/08/2022
 */

@Service
public class EstadisticaServiceImpl implements IEstadisticasService{
	
	@Autowired
	IEstadisticasMesProductoDao estadisticasMesProductoDao;
	
	@Autowired
	IPromocionService promocionService;
	
	@Autowired
	IProductoService productoService;
	
	@Autowired
	IItemVentaService itemVentaService;

	/**
     * --------------================ METODOS =================--------------------------------
     */
	
	/**
     * -------------- METODOS INTERFACE IEstadisticasService --------------------------------
     */

	/**
     * @Autor RagooS
     * @Descripccion Metodo permite obtener las estadisticas por mes del producto
     * @Fecha 01/08/2022
     */
	@Override
	public DetalleEstadistica findByMes(String fecha) {
		// TODO Auto-generated method stub
		System.out.println("#### INICIA METODO findByMes() PARA CONSULTAR ESTADISTICAS EN LA BD  ####");
		
		List<EstadisticasMesProducto> estadisticasMesProducto = null;
		DetalleEstadistica detalle = new DetalleEstadistica();
		String mount = "";
		String year = "";
		
		String[] fechArr = Utilidades.estraerMesAño(fecha);
		
		if(fechArr != null && fechArr.length > 1) {
			year = fechArr[0];
			mount = fechArr[1];
			
			if( !mount.equals("") && !year.equals("")) {
				estadisticasMesProducto = estadisticasMesProductoDao.findByMes(Integer.parseInt(mount), Integer.parseInt(year));
				
				if(estadisticasMesProducto != null && !estadisticasMesProducto.isEmpty()) {
					System.out.println("#### SE ENCONTRARON ESTADISTICAS EN EL MES EN BD  ####");
					
					detalle.setEstadisticas(estadisticasMesProducto);
					detalle.setCantEstadisticas(estadisticasMesProducto.size());
					detalle.setTotal(calcularTotalGeneralEstadisticas(estadisticasMesProducto));
					detalle.setFecha(fecha);
					
					return detalle;
				} else {
					System.out.println("#### NO SE ENCONTRARON ESTADISTICAS EN EL MES EN BD  ####");
					System.out.println("#### SE PROCEDE A LLAMAR PROCEDIMIENTO PARA GENERARLAS  ####");
					return generarEstadisticaMesProducto(fecha);
				}
			}
		}  else {
			System.out.println("#### ERROR: NO FUE POSIBLE CAPTURAR EL MES Y AÑO PARA LA CONSULTA  ####");
		}
		
		return detalle;
		
	}

	/**
     * @Autor RagooS
     * @Descripccion Metodo permite calcular el total de las estadisticas
     * @Fecha 30/09/2022
     */
	private Double calcularTotalGeneralEstadisticas(List<EstadisticasMesProducto> estadisticasMesProducto) {
		// TODO Auto-generated method stub
		Double total = 0.0;
		
		System.out.println("#### INICIA METODO calcularTotalGeneralEstadisticas  ####");
		
		if(estadisticasMesProducto != null && !estadisticasMesProducto.isEmpty()) {
			
			for(EstadisticasMesProducto estadistica:estadisticasMesProducto ) {
				total += estadistica.getTotal();
			}
		}
		
		System.out.println("#### TOTAL CALCULADO: " + total + "  ####");
		
		return total;
	}

	/**
     * @Autor RagooS
     * @Descripccion Metodo permite generar las estadisticas por mes del producto
     * @Fecha 01/08/2022
     */
	@Override
	public DetalleEstadistica generarEstadisticaMesProducto(String fecha) {
		System.out.println("#### INICIA METODO PARA CALCULAR ESTADISTICA EN EL MES  ####");
		
		DetalleEstadistica detalle = new DetalleEstadistica();
		detalle.setFecha(fecha);
		List<EstadisticasMesProducto> listEstadisticasMesProductos = new ArrayList<>();
		
		System.out.println("#### SE PROCEDE A CONSULTAR LOS PRODUCTOS DISPONIBLES  ####");
		List<Producto> productos = productoService.findAll_Activo();
		System.out.println("#### DESPUES DE CONSULTAR LOS PRODUCTOS DISPONIBLES  ####");
		//List<Promocion> promociones = promocionService.findAll();
		
		System.out.println("#### ANTES DE CONSULTAR LOS ITEMS DE LAS VENTAS CON PROMOCION EN EL MES  ####");
		List<ItemVenta> itemsVentaPromocion = itemVentaService.obtenerItemsVentasConPromociones(fecha);
		System.out.println("#### DESPUES DE CONSULTAR LOS ITEMS DE LAS VENTAS CON PROMOCION EN EL MES  ####");
		
		Date fechaMes = null;
		
		if(productos != null && !productos.isEmpty()) {
			
			System.out.println("#### SE ENCONTRARON PRODUCTOS DISPONIBLES  ####");
			
			for(Producto produc:productos) {
				
				EstadisticasMesProducto estadisticasMesProductoTemp = new EstadisticasMesProducto();
				EstadisticasMesProducto estadisticasMesProducto = new EstadisticasMesProducto();
				estadisticasMesProducto.setProducto(produc);
				estadisticasMesProducto.setCant_peso(0D);
				estadisticasMesProductoTemp.setCant_peso(0D);
				
				System.out.println("#### ANTES DE LLAMAR AL METODO obtenerItemsVentasPorProductoEnMesAno() CON LOS DATOS --> fecha: " + fecha + " producto_id: " + produc.getId() + "  ####");
				
				List<ItemVenta> itemsVenta = itemVentaService.obtenerItemsVentasPorProductoEnMesAno(fecha, produc);
				
				if(itemsVenta != null && !itemsVenta.isEmpty()) {
					System.out.println("#### SE ENCONTRARON ITEMS VENTAS DE EL PRODUCTO  ####");
					System.out.println("#### ANTES DE LLAMAR AL MEDOTO calcularEstadisticaProducto()  ####");
					estadisticasMesProductoTemp = calcularEstadisticaProducto(itemsVenta);
					
					System.out.println("#### DESPUES DE LLAMAR AL MEDOTO calcularEstadisticaProducto()  ####");
				
				}
				
				System.out.println("#### ANTES DE LLAMAR AL MEDOTO buscarEstadisticaItemPromocionVenta()  ####");
				estadisticasMesProductoTemp = buscarEstadisticaItemPromocionVenta(estadisticasMesProductoTemp, produc, itemsVentaPromocion);
				System.out.println("#### DESPUES DE LLAMAR AL MEDOTO buscarEstadisticaItemPromocionVenta()  ####");
				
				System.out.println("#### ANTES DE LLAMAR AL MEDOTO findByMesProducto()  ####");
				EstadisticasMesProducto estadisticasMesProductoConsultada = findByMesProducto(fecha, produc);
				System.out.println("#### DESPUES DE LLAMAR AL MEDOTO findByMesProducto()  ####");
				
				if(estadisticasMesProductoConsultada != null) {
					System.out.println("#### SE ENCONTRO ESTADISTICA DEL PRODUCTO EN LA FECHA  ####");
					fechaMes = estadisticasMesProductoConsultada.getFecha();
					estadisticasMesProducto.setId(estadisticasMesProductoConsultada.getId());
				} else {
					System.out.println("#### NO SE ENCONTRO ESTADISTICA DEL PRODUCTO EN LA FECHA  ####");
					fechaMes = Utilidades.obtenerFechaEnMes(fecha);
				}
				
				estadisticasMesProducto.setCant_peso(estadisticasMesProductoTemp.getCant_peso());
				estadisticasMesProducto.setTotal(estadisticasMesProductoTemp.getTotal());
				
				if(fechaMes != null) {
					estadisticasMesProducto.setFecha(fechaMes);
					estadisticasMesProducto.setFecha_ultima_ejecucion(new Date());
					
					if(estadisticasMesProducto != null && estadisticasMesProducto.getCant_peso() > 0) {
						System.out.println("#### ANTES DE LLAMAR AL MEDOTO save()  ####");
						EstadisticasMesProducto estadisticaNew = save(estadisticasMesProducto);
						System.out.println("#### DESPUES DE LLAMAR AL MEDOTO save()  ####");
						
						if(estadisticaNew != null) {
							System.out.println("#### SE ALMACENO CON EXITO LA ESTADISTICA  ####");
							listEstadisticasMesProductos.add(estadisticaNew);
						} else {
							System.out.println("#### ERROR: NO SE ALMACENO CON LA ESTADISTICA  ####");
						}
					}
				} else {
					System.out.println("#### ERROR: NO FUE POSIBLE OBTENER LA FECHA EN MES  ####");
				}
				
			}
		} else {
			System.out.println("#### NO SE ENCONTRARON PRODUCTOS DISPONIBLES  ####");
		}
		
		detalle.setEstadisticas(listEstadisticasMesProductos);
		detalle.setCantEstadisticas(listEstadisticasMesProductos.size());
		detalle.setTotal(calcularTotalGeneralEstadisticas(listEstadisticasMesProductos));
		
		return detalle;
	}
	
	private EstadisticasMesProducto buscarEstadisticaItemPromocionVenta(EstadisticasMesProducto estadisticasMesProducto, Producto produc, List<ItemVenta> itemsVentaPromocion) {
		
		System.out.println("#### INICIA METODO PARA CALCULAR ESTADISTICA EN EL MES PARA PROMOCIONES  ####");
		
		List<ItemPromocionVenta> itemPromocionVentas = buscarItemPromocionVentaPorProducto(itemsVentaPromocion, produc);
		
		if(itemPromocionVentas != null && !itemPromocionVentas.isEmpty()) {
			System.out.println("#### SE ENCONTRARON ITEMS VENTAS PROMOCIONES DEL PRODUCTO CON ID: " + produc.getId() + "  ####");
			Double cant = 0D;
			Double total = 0D;
			for( ItemPromocionVenta itemPromo : itemPromocionVentas) {
				cant += itemPromo.getCant_peso();
				total += itemPromo.getTotal();
			}
			
			System.out.println("#### CANTIDAD PESO: " + cant + "  ####");
			System.out.println("#### TOTAL: " + total + "  ####");
			
			cant += estadisticasMesProducto.getCant_peso();
			total += estadisticasMesProducto.getTotal();
			
			estadisticasMesProducto.setCant_peso(cant);
			estadisticasMesProducto.setTotal(total);
			
			System.out.println("#### CANTIDAD PESO TOTAL: " + estadisticasMesProducto.getCant_peso() + "  ####");
			System.out.println("#### $$ TOTAL: " + estadisticasMesProducto.getTotal() + "  ####");
		}
		
		return estadisticasMesProducto;
	}

	private List<ItemPromocionVenta> buscarItemPromocionVentaPorProducto(List<ItemVenta> itemsVentaPromocion,
			Producto produc) {
		
		System.out.println("#### INICIA METODO PARA CALCULAR ESTADISTICA EN EL MES PARA BUSCAR PROMOCIONES DE LOS ITEMS VENTAS  ####");
		
		List<ItemPromocionVenta> itemsPromociones = new ArrayList<>();
		
		for(ItemVenta item: itemsVentaPromocion) {
			
			if(item.getPromocion_venta() != null && item.getPromocion_venta().getItems_promocion() != null && !item.getPromocion_venta().getItems_promocion().isEmpty()) {
				for(int i=0; i<item.getPromocion_venta().getItems_promocion().size(); i++) {
					if(item.getPromocion_venta().getItems_promocion().get(i).getProducto().getId() == produc.getId()) {
						System.out.println("#### SE ENCONTRO ITEM VENTA PROMOCION PARA EL PRODUCTO CON ID: " + produc.getId() + "  ####");
						itemsPromociones.add(item.getPromocion_venta().getItems_promocion().get(i));
						item.getPromocion_venta().getItems_promocion().remove(i);
						System.out.println("#### SE REMUEVE EL ITEM VENTA PROMOCION  ####");
					}
				}
			}
			
		}
		
		return itemsPromociones;
	}

	/**
     * @Autor RagooS
     * @Descripccion Metodo permite almacenar la estadistica por mes
     * @Fecha 06/08/2022
     */
	@Override
	public EstadisticasMesProducto save(EstadisticasMesProducto estadisticasMesProducto) {
		System.out.println("#### INICIA MEDOTO save() ALMACENAR ESTADISTICA ####");
		return estadisticasMesProductoDao.save(estadisticasMesProducto);
	}
	
	/**
     * @Autor RagooS
     * @Descripccion Metodo permite consultar la estadistica del mes por producto
     * @Fecha 07/08/2022
     */
	@Override
	public EstadisticasMesProducto findByMesProducto(String fecha, Producto producto) {
		System.out.println("#### INICIA MEDOTO findByMesProducto() CONSULTAR ESTADISTICA DEL PRODUCTO ####");
		
		EstadisticasMesProducto estadisticasMesProducto = null;
		String[] arrFecha = Utilidades.estraerMesAño(fecha);
		
		if(arrFecha != null &&  arrFecha.length > 1) {
			String mes = arrFecha[1];
			String ano = arrFecha[0];
			System.out.println("#### SE VA A CONSULTAR ESTADISTICA DEL PRODUCTO CON LOS DATOS --> mes:" + mes + ", año: " + ano + ", producto_id: " + producto.getId() + " ####");
			estadisticasMesProducto = estadisticasMesProductoDao.findByMesProducto(Integer.parseInt(mes), Integer.parseInt(ano), producto.getId());
		}
		
		return estadisticasMesProducto;
	}

	/**
     * -------------- METODOS PROPIOS --------------------------------
     */
	
	/**
     * @Autor RagooS
     * @Descripccion Metodo permite calcular las estadisticas del producto segun una lista de itemsVenta
     * @Fecha 06/08/2022
     */
	private EstadisticasMesProducto calcularEstadisticaProducto(List<ItemVenta> itemsVenta) {
		System.out.println("#### INICIA MEDOTO calcularEstadisticaProducto()  ####");
		
		EstadisticasMesProducto estadisticasMesProducto = new EstadisticasMesProducto();
		Double cant_peso = 0D;
		Double precioTotal = 0D;
		
		if(itemsVenta != null && !itemsVenta.isEmpty()) {
			for(ItemVenta item:itemsVenta) {
				cant_peso += item.getCant_peso();
				precioTotal += item.getTotal();
			}
			System.out.println("#### SE CALCULO --> cant_peso: " + cant_peso + " ####");
			System.out.println("#### SE CALCULO --> total: " + precioTotal + " ####");
			estadisticasMesProducto.setCant_peso(cant_peso);
			estadisticasMesProducto.setTotal(precioTotal);
		}
		
		return estadisticasMesProducto;
	}

	

	

}
