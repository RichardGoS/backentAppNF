package com.developer.naturalfisher.models.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.naturalfisher.models.dao.IItemVentaDao;
import com.developer.naturalfisher.models.entity.ItemVenta;
import com.developer.naturalfisher.models.entity.Venta;

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

}
