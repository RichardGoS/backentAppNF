package com.developer.naturalfisher.models.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.naturalfisher.models.dao.IItemInversionDao;
import com.developer.naturalfisher.models.entity.Inversion;
import com.developer.naturalfisher.models.entity.ItemInversion;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 10/02/2022
 */

@Service
public class ItemInversionServiceImpl implements IItemInversionService {
	
	@Autowired
	IItemInversionDao itemInversionDao;
	
	/**
     * --------------================ METODOS =================--------------------------------
     */
	
	/**
     * -------------- METODOS INTERFACE IItemInversionService --------------------------------
     */

	@Override
	public ItemInversion save(ItemInversion item) {
		// TODO Auto-generated method stub
		return itemInversionDao.save(item);
	}

	@Override
	public List<ItemInversion> obtenerItemsInversionesPorProductoMayorAFechaInventario(Long producto_id, Date fecha_inventario) {
		// TODO Auto-generated method stub
		List<ItemInversion> items = new ArrayList<>();
		
		/*SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		
		String fecha = format.format(fecha_inventario);*/
		
		String inversiones = itemInversionDao.obtenerItemsInversionesPorProductoMayorAFechaInventario(producto_id, fecha_inventario);
		
		if( inversiones != null && !inversiones.equals("") ) {
			if(inversiones.contains(",")) {
				String[] dataInversion = inversiones.split(",");
				for(String datos:dataInversion) {
					String[] data = datos.split(";");
					
					ItemInversion item = new ItemInversion();
					
					item.setId(Long.parseLong(data[0]));
					item.setCant_comprado(Double.parseDouble(data[1]));
					item.setPrecio_total(Double.parseDouble(data[2]));
					item.setPrecio_unitario(Double.parseDouble(data[3]));
					
					Inversion inversion = new Inversion();
					inversion.setId(Long.parseLong(data[4]));
					
					item.setInversion(inversion);
					
					items.add(item);
				}
				
			}
		}
		
		
		return items;
	}

}
