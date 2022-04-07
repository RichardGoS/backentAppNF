package com.developer.naturalfisher.models.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.naturalfisher.models.dao.IBodegaDao;
import com.developer.naturalfisher.models.dao.IInversionDao;
import com.developer.naturalfisher.models.entity.Inventario;
import com.developer.naturalfisher.models.entity.Inversion;
import com.developer.naturalfisher.models.entity.ItemInversion;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 30/01/2022
 */


@Service
public class InversionServiceImpl implements IInversionService {
	
	@Autowired
	IInversionDao inversionDao;
	
	@Autowired
	IBodegaDao bodegaDao;
	
	@Autowired
	IItemInversionService itemInversionService;
	
	@Autowired
	IInventarioService inventarioService;

	/**
     * --------------================ METODOS =================--------------------------------
     */
	
	/**
     * -------------- METODOS INTERFACE IInversionService --------------------------------
     */
	
	/**
     * @Autor RagooS
     * @Descripccion Metodo permite obtener todas las inversiones realizadas
     * @Fecha 08/02/2022 antes
     */
	@Override
	public List<Inversion> findAll() {
		return inversionDao.findAll();
	}

	/**
     * @Autor RagooS
     * @Descripccion Metodo permite buscar registros de inventarios realizados despues del ultimo inventario
     * @Fecha 08/02/2022  antes
     */
	@Override
	public List<Inversion> buscarUltimaFechaInventariada(Date fecha) {
		
		return inversionDao.buscarUltimaFechaInventariada(fecha);
	}

	
	/**
     * @Autor RagooS
     * @Descripccion Metodo permite obtener las inversiones realizadas al producto
     * @Fecha 08/02/2022 
     */
	@Override
	public List<ItemInversion> findItemsByIdProducto(Long id) {
		
		List<ItemInversion> ListItems = new ArrayList<>();
		
		String inversiones = inversionDao.obtenerInversionesPorProducto(id);
		
		System.out.print(inversiones);
		
		if(inversiones != null && !inversiones.equals("")) {
			
			if(inversiones.contains(",")) {
				String[] dataInversion = inversiones.split(",");
				
				for(String datos: dataInversion) {
					String[] data = datos.split(";");
					ItemInversion item = new ItemInversion();
					
					item.setId(Long.parseLong(data[0]));
					
					/*SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
					
					try {
						inversion.setFecha(format.parse(data[1]));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						inversion.setFecha(null);
					}*/
					
					item.setCant_comprado(Double.parseDouble(data[1]));
					item.setPrecio_total(Double.parseDouble(data[2]));
					item.setPrecio_unitario(Double.parseDouble(data[3]));
					
					Inversion inversion = new Inversion();
					
					inversion.setId(Long.parseLong(data[4]));
					
					item.setInversion(inversion);
					
					ListItems.add(item);
					
				}
				
			}
		} else {
			ListItems = null;
		}
		
		return ListItems;
	}

	/**
     * @Autor RagooS
     * @Descripccion Metodo permite almacenar una inversion
     * @Fecha 08/02/2022 
     */
	@Override
	public Inversion save(Inversion newInversion) {
		// TODO Auto-generated method stub
		
		Inversion inversionFinal = null;
		
		newInversion.setFecha(new Date());
		Inversion inversion = inversionDao.save(newInversion);
		inversionFinal = inversion;
		
		if(inversion != null && (inversion.getItems() != null && !inversion.getItems().isEmpty())) {
			//Bodega bodega = bodegaDao.findByIdProducto();
			Inversion inversionVacia = new Inversion();
			inversionVacia.setId(inversion.getId());
			
			for(ItemInversion item:inversion.getItems()) {
				item.setInversion(inversionVacia);
				ItemInversion itemSave = itemInversionService.save(item);
				
				if(itemSave != null) {
					
					Inventario inventario = inventarioService.seleccionarUtimoInventarioProducto(itemSave.getProducto().getId());
					
					if(inventario != null) {
						System.out.print("hola");
					} 
					
				}
			}
		}
		
		return inversionFinal;
	}

	/**
     * @Autor RagooS
     * @Descripccion Metodo permite almacenar una inversion
     * @Fecha 09/02/2022 
     */
	@Override
	public Inversion findById(Long id) {
		return inversionDao.findById(id).orElse(null);
	}

}
