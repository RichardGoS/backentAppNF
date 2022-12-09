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
import com.developer.naturalfisher.models.transporte.DetalleInversiones;
import com.developer.naturalfisher.utilidades.Utilidades;

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
	
	@Autowired
	

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
	public DetalleInversiones findAll() {
		DetalleInversiones detalle = new DetalleInversiones();
		detalle.setFecha("TODAS");
		
		List<Inversion> inversiones = inversionDao.findAll();
		
		if(inversiones != null && !inversiones.isEmpty()) {
			detalle.setInversiones(inversiones);
			detalle.setCantInversiones(0);
			detalle.setTotal(consultarTotalInversionRango("TODAS"));
		}
		
		return detalle;
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
				
				if(itemSave != null && itemSave.getProducto() != null) {
					
					inventarioService.inventariar(itemSave.getProducto(), null, null);
					
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

	/**
	 * Fase 4 Tarea 3
     * @Autor RagooS
     * @Descripccion Metodo permite obtener el detalle de las inversiones en mes
     * @Fecha 22/09/2022 
     */
	@Override
	public DetalleInversiones detalleInversionesEnMes(String fecha) {
		// TODO Auto-generated method stub
		System.out.println("#### INICIA METODO detalleInversionesEnMes() PARA CONSULTAR INVERSIONES EN MES AÑO  ####");
		
		System.out.println("#### ANTES DE EJECUTAR EL METODO InversionesEnMes() fecha: " + fecha + "  ####");
		List<Inversion> inversiones = InversionesEnMes(fecha);
		DetalleInversiones detalleInversiones = new DetalleInversiones();
		detalleInversiones.setFecha(fecha);
		
		System.out.println("#### DESPUES DE EJECUTAR EL METODO InversionesEnMes()  ####");
		
		if(inversiones != null && !inversiones.isEmpty()) {
			System.out.println("#### SE ENCONTRARON inversiones: " + inversiones.size() + " TOTALES EN LA CONCULTA  ####");
			
			detalleInversiones.setCantInversiones(inversiones.size());
			detalleInversiones.setInversiones(inversiones);
			
			System.out.println("#### ANTES DE EJECUTAR EL METODO consultarTotalInversionMesAño() fecha: " + fecha + "  ####");
			
			detalleInversiones.setTotal(consultarTotalInversionRango(fecha));
		} else {
			System.out.println("#### NO SE ENCONTRARON INVERSIONES  ####");
			
			detalleInversiones = null;
		}
		
		return detalleInversiones;
	}

	/**
	 * Fase 4 Tarea 3
     * @Autor RagooS
     * @Descripccion Metodo permite obtener las inversiones en mes
     * @Fecha 22/09/2022 
     */
	@Override
	public List<Inversion> InversionesEnMes(String fecha) {
		System.out.println("#### INICIA METODO InversionesEnMes() PARA CONSULTAR INVERSIONES EN MES AÑO  ####");
		
		String mount = "";
		String year = "";
		
		if(fecha.contains("/")) {
			String[] fechArr = fecha.split("/"); 
			year = fechArr[0];
			mount = fechArr[1];
			
			if( !mount.equals("") && !year.equals("")) {
				
				try {
					System.out.println("#### SE VA A REALIZAR LA CONSULTA DE LAS INVERSIOJNES EN EL MES: " + mount + " AÑO: " + year + "  ####");
					return inversionDao.consultarInversionesEnMesAno(Integer.parseInt(mount), Integer.parseInt(year));
				} catch (Exception e) {
					e.printStackTrace();
					
					System.out.println("#### ERROR AL EJECUTAR LA CONSULTA " + e.getMessage() + "  ####");
				}
				
			}
		}
		
		return null;
	}

	/**
	 * Fase 4 Tarea 3
     * @Autor RagooS
     * @Descripccion Metodo permite obtener el precio total de las inversiones en el mes
     * @Fecha 22/09/2022 
     */
	@Override
	public Double consultarTotalInversionRango(String rango) {
		System.out.println("#### INICIA METODO consultarTotalInversionMesAño() PARA CONSULTAR EL VALOR TOTAL DE LAS INVERSIONES EN MES AÑO  ####");
		
		String mount = "";
		String year = "";
		
		if(rango.contains("/")) {
			String[] fechArr = rango.split("/"); 
			year = fechArr[0];
			mount = fechArr[1];
			
			if( !mount.equals("") && !year.equals("")) {
				
				try {
					System.out.println("#### SE VA A REALIZAR LA CONSULTA DEL TOTAL EN EL MES: " + mount + " AÑO: " + year + "  ####");
					return inversionDao.consultarTotalInversionEnMesAno(Integer.parseInt(mount), Integer.parseInt(year));
				} catch (Exception e) {
					e.printStackTrace();
					
					System.out.println("#### ERROR AL EJECUTAR LA CONSULTA " + e.getMessage() + "  ####");
				}
				
			}
		} else if(rango.contains("TODAS")) {
			try {
				System.out.println("#### SE VA A REALIZAR LA CONSULTA DEL TOTAL DE TODAS LAS INVERSIONES  ####");
				return inversionDao.consultarTotalTodasInversion();
			} catch (Exception e) {
				e.printStackTrace();
				
				System.out.println("#### ERROR AL EJECUTAR LA CONSULTA " + e.getMessage() + "  ####");
			}
		}
		
		return null;
	}

	@Override
	public List<Inversion> consultarInversionesEnFecha(String fecha) {
		
		System.out.println("#### INICIA METODO consultarInversionesEnFecha() PARA CONSULTAR LAS INVERSIONES REALIZADAS EN EL DIA  ####");
		
		List<Inversion> inversiones = null;
		
		List<Date> fechas = Utilidades.obtenerRangoFechaDia(fecha);
		
		if(fechas != null && !fechas.isEmpty()) {
			inversiones = inversionDao.consultaInversionesEnFecha(fechas.get(0), fechas.get(1) );
		}
		
		return inversiones;
	}

}
