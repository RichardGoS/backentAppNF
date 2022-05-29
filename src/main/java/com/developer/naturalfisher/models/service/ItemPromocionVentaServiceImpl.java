package com.developer.naturalfisher.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.naturalfisher.models.dao.IItemPromocionDao;
import com.developer.naturalfisher.models.dao.IItemPromocionVentaDao;
import com.developer.naturalfisher.models.entity.ItemPromocion;
import com.developer.naturalfisher.models.entity.ItemPromocionVenta;
import com.developer.naturalfisher.models.entity.ItemVenta;
import com.developer.naturalfisher.models.entity.PromocionVenta;
import com.developer.naturalfisher.models.entity.Venta;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 10/04/2022
 */

@Service
public class ItemPromocionVentaServiceImpl implements IItemPromocionVentaService {
	
	/**
	 * --------------================ VARIABLES =================--------------------------------
	 */
	
	@Autowired
	IItemPromocionVentaDao itemPromocionVentaDao;

	/**
     * --------------================ METODOS =================--------------------------------
     */
	
	/**
     * -------------- METODOS INTERFACE IItemPromocionVentaService --------------------------------
     */
	
	/**
     * @Autor RagooS
     * @Descripccion Metodo almacenar el item promocion
     * @Fecha 10/04/2022
     */
	@Override
	public ItemPromocionVenta save(ItemPromocionVenta itemPromocionVentaNew) {
		// TODO Auto-generated method stub
		return itemPromocionVentaDao.save(itemPromocionVentaNew);
	}

	@Override
	public List<ItemPromocionVenta> obtenerItemsPromocionVentasPorProducto(Long idProducto) {
		// TODO Auto-generated method stub
		
		List<ItemPromocionVenta> itemPromocionVentas = new ArrayList<>();
		String items = itemPromocionVentaDao.obtenerItemsPromocionVentasPorProducto(idProducto);
		
		if(items!= null && !items.equals("")) {
			if(items.contains(",")) {
				String[] dataVentas = items.split(",");
				for(String datos: dataVentas) {
					String[] data = datos.split(";");
					
					ItemPromocionVenta item = new ItemPromocionVenta();
					PromocionVenta promocionVenta = new PromocionVenta();
					
					item.setId(Long.parseLong(data[0]));
					item.setCant_peso(Double.parseDouble(data[1]));
					item.setTotal(Double.parseDouble(data[2]));
					
					promocionVenta.setId(Long.parseLong(data[3]));
					
					item.setPromocion_venta(promocionVenta);
					
					itemPromocionVentas.add(item);
				
				}
			}
		}
		
		return itemPromocionVentas;
	}

	

}
