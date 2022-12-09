package com.developer.naturalfisher.models.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.naturalfisher.models.dao.IItemVentaDao;
import com.developer.naturalfisher.models.entity.ItemPromocionVenta;
import com.developer.naturalfisher.models.entity.ItemVenta;
import com.developer.naturalfisher.models.entity.Producto;
import com.developer.naturalfisher.models.entity.PromocionVenta;
import com.developer.naturalfisher.models.entity.Venta;
import com.developer.naturalfisher.utilidades.Utilidades;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 22/06/2021
 */

@Service
public class ItemVentaServiceImpl implements IItemVentaService {
	
	@Autowired
	IItemVentaDao itemDao;

	@Override
	public ItemVenta save(ItemVenta itemNew) {
		// TODO Auto-generated method stub
		return itemDao.save(itemNew);
	}

	
	/**
     * --------------================ METODOS =================--------------------------------
     */
	
	/**
     * -------------- METODOS INTERFACE IVentaService --------------------------------
     */
	
	/**
	 * 
	 * @author RagooS
	 * @Descripccion Metodo permite obtener los items registrados de las ventas por producto
	 * @fecha 18/02/2022
	 */
	@Override
	public List<ItemVenta> findItemsVentasPorProducto(Long id) {
		// TODO Auto-generated method stub
		List<ItemVenta> items = new ArrayList<>();
		String ventas = itemDao.obtenerItemsVentasPorProducto(id);
		
		if(ventas!= null && !ventas.equals("")) {
			if(ventas.contains(",")) {
				String[] dataVentas = ventas.split(",");
				for(String datos: dataVentas) {
					String[] data = datos.split(";");
					
					ItemVenta item = new ItemVenta();
					
					item.setId(Long.parseLong(data[0]));
					item.setCant_peso(Double.parseDouble(data[1]));
					item.setTotal(Double.parseDouble(data[2]));
					
					Venta venta = new Venta();
					venta.setId(Long.parseLong(data[3]));
					
					item.setVenta(venta);
					
					items.add(item);
					
				}
			}
		}
		
		
		return items;
	}

	
	/**
	 * 
	 * @author RagooS
	 * @Descripccion Metodo permite obtener los items registrados de las ventas mayores a la fecha por producto
	 * @fecha 18/02/2022
	 */
	@Override
	public List<ItemVenta> findItemsVentasMayorFechaPorProducto(Long id, Date fecha) {
		// TODO Auto-generated method stub
		
		String strItems = itemDao.obtenerItemsVentasPorProductoMayorAFecha(id, fecha);
		List<ItemVenta> items = new ArrayList<>();
		
		if(strItems!= null && !strItems.equals("")) {
			if(strItems.contains(",")) {
				String[] dataStrItems = strItems.split(",");
				for(String datos: dataStrItems) {
					String[] data = datos.split(";");
					
					ItemVenta item = new ItemVenta();
					
					item.setId(Long.parseLong(data[0]));
					item.setCant_peso(Double.parseDouble(data[1]));
					item.setTotal(Double.parseDouble(data[2]));
					
					Venta venta = new Venta();
					venta.setId(Long.parseLong(data[3]));
					
					item.setVenta(venta);
					
					items.add(item);
				}
			}
		}
		
		return items;
	}


	/**
	 * 
	 * @author RagooS
	 * @Descripccion Metodo permite obtener los items registrados de las ventas mayores a la fecha y que promocion venta sea diferente de null
	 * @fecha 21/05/2022
	 */
	@Override
	public List<ItemPromocionVenta> obtenerItemsVentasMayorAFechaConPromocionPorProducto(Date fecha, Long producto_id) {
		// TODO Auto-generated method stub
		
		String strItems = itemDao.obtenerItemsVentasMayorAFechaConPromocionPorProducto(fecha, producto_id);
		List<ItemPromocionVenta> itemsPromo = new ArrayList<>();
		
		if(strItems!= null && !strItems.equals("")) {
			if(strItems.contains(",")) {
				String[] dataStrItemsVenta = strItems.split(",");
				for(String datos: dataStrItemsVenta) {
					if(datos.contains("|")) {
						String[] dataStrItems = strItems.split("|");
						for(String datosItem: dataStrItems) {
							if(datosItem.contains(";")) {
								String[] data = datos.split(";");
								
								ItemPromocionVenta itemPromocionVenta = new ItemPromocionVenta();
								PromocionVenta promocionVenta = new PromocionVenta();
								
								promocionVenta.setId(Long.parseLong(data[0]));
								itemPromocionVenta.setId(Long.parseLong(data[1]));
								itemPromocionVenta.setCant_peso(Double.parseDouble(data[2]));
								itemPromocionVenta.setTotal(Double.parseDouble(data[3]));
								
								itemsPromo.add(itemPromocionVenta);
								
							}
						}
					}
				}
				
			}
		}
		
		return itemsPromo;
	}


	/**
	 * Fase 4 Tarea 1
	 * @author RagooS
	 * @Descripccion Metodo permite obtener los items registrados de las ventas por producto en un mes del año
	 * @fecha 21/05/2022
	 */
	@Override
	public List<ItemVenta> obtenerItemsVentasPorProductoEnMesAno(String fecha, Producto producto) {
		System.out.println("#### INICIA METODO obtenerItemsVentasPorProductoEnMesAno() PARA CONSULTAR ITEM_VENTA POR PRODUCTO EN MES AÑO EN LA BD  ####");
		
		String[] arrFecha = Utilidades.estraerMesAño(fecha);
		String mount = "";
		String year = "";
		List<ItemVenta> items = new ArrayList<ItemVenta>();
		
		if(arrFecha != null && arrFecha.length > 1) {
			year = arrFecha[0];
			mount = arrFecha[1];
			
			if( !mount.equals("") && !year.equals("")) {
				
				System.out.println("#### SE VA A LLAMAR AL METODO DE CONSULTA CON LOS DATOS  --> mes: "  +  mount + "  año: " + year + "  ####");
				
				String strItems = itemDao.obtenerItemsVentasPorProductoEnMesAno(producto.getId(), Integer.parseInt(mount), Integer.parseInt(year));
				System.out.println("#### RESPUESTA DEL PROCEDIMIENTO DATOS  -->  "  +  strItems + "  ####");
				if(strItems != null && !strItems.equals("")) {
					if(strItems.contains(",")) {
						String[] arrItems = strItems.split(",");
						
						if( arrItems != null && arrItems.length > 0) {
							for(String strItem:arrItems) {
								if(strItem.contains(";")) {
									String[] arrItemTemp  = strItem.split(";");
									if(arrItemTemp != null && arrItemTemp.length > 1) {
										ItemVenta itemVenta = new ItemVenta();
										itemVenta.setCant_peso(Double.parseDouble(arrItemTemp[0]));
										itemVenta.setTotal(Double.parseDouble(arrItemTemp[1]));
										
										items.add(itemVenta);
									}
								}
							}
						}
					}
				} else {
					System.out.println("#### NO SE ENCONTRARON DATOS EN LA CONSULTA  ####");
				}
			}
		} else {
			System.out.println("#### ERROR: NO FUE POSIBLE CAPTURAR EL MES Y AÑO PARA LA CONSULTA  ####");
		}
		
		return items;
	}


	@Override
	public List<ItemVenta> obtenerItemsVentasConPromociones(String fecha) {
		System.out.println("#### INICIA METODO obtenerItemsVentasPorProductoEnMesAno() PARA CONSULTAR ITEM_VENTA POR PRODUCTO EN MES AÑO EN LA BD  ####");
		
		String[] arrFecha = Utilidades.estraerMesAño(fecha);
		
		String mount = "";
		String year = "";
		List<ItemVenta> items = new ArrayList<ItemVenta>();
		
		if(arrFecha != null && arrFecha.length > 1) {
			year = arrFecha[0];
			mount = arrFecha[1];
			
			if( !mount.equals("") && !year.equals("")) {
				System.out.println("#### SE VA A LLAMAR AL METODO DE CONSULTA CON LOS DATOS  --> mes: "  +  mount + "  año: " + year + "  ####");
				
				String strItems = itemDao.obtenerItemsVentaConPromocionEnMesAno(Integer.parseInt(mount), Integer.parseInt(year));
				
				System.out.println("#### RESPUESTA DEL PROCEDIMIENTO DATOS  -->  "  +  strItems + "  ####");
				
				if(strItems != null && !strItems.equals("")) {
					if(strItems.contains(",")) {
						String[] arrIdItems = strItems.split(",");
						
						if(arrIdItems.length > 0) {
							for(int i=0; i<arrIdItems.length; i++) {
								ItemVenta item = itemDao.findById(Long.parseLong(arrIdItems[i])).orElse(null);
								
								if(item != null) {
									items.add(item);
								}
							}
						}
						
						
					}
				} else {
					System.out.println("#### NO SE ENCONTRARON DATOS EN LA CONSULTA  ####");
				}
			}
			
			
		}
		
		
		return items;
	}	

}
