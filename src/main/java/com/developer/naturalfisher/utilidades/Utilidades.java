package com.developer.naturalfisher.utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 06/07/2021
 */

public class Utilidades {
	
	/**
	 * @author RagooS
	 * @Descripccion Metodo permite formatear fecha y restar 1 mes
	 * @param fecha1 fechaActual
	 */
	public static List<Date> restarFechaActual() {
		
		List<Date> fechas = new ArrayList<>();
		Date fechaActual = new Date();
		Date fechaPasada = new Date();
		
		if(fechaActual != null) {
			fechaPasada.setMonth(fechaPasada.getMonth() - 1);
		}
		
		System.out.println("Fecha Actual: " + fechaActual);
		System.out.println("Fecha Pasada: " + fechaPasada);
		
		if(fechaActual != null && fechaPasada != null) {
			fechas.add(fechaActual);
			fechas.add(fechaPasada);
		} else {
			return null;
		}
		
		return fechas;
	}
	
	
	/**
	 * @author RagooS
	 * @Descripccion Metodo permite formatear fecha String a Date
	 * @param fecha String a formatear
	 * @return Date formateado
	 */
	public static Date formatearStringDate(String fecha) {
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Date date = format.parse(fecha);
			
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}


	public static List<Date> obtenerRangoFechaDia(String fecha) {
		// TODO Auto-generated method stub
		
		List<Date> fechas = new ArrayList<>();
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    	
    	Date fechaInicial = null;
    	Date fechaFinal = null;
    	
		try {
			fechaInicial = format.parse(fecha);
			fechaFinal = format.parse(fecha);
			if(fechaInicial != null && fechaFinal != null) {
				fechaFinal.setHours(23);
				fechaFinal.setMinutes(59);
				fechaFinal.setSeconds(59);
				
				fechas.add(fechaInicial);
				fechas.add(fechaFinal);
			} else {
				fechas = null;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fechas = null;
		}
		
		return fechas;
	}
	
	/**
	 * @author RagooS
	 * @Descripccion Metodo permite formatear fecha String a Date
	 * @param fecha String a formatear
	 * @return Date formateado
	 */
	public static String formatearFechaDateString(Date fechaDate) {
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String strFecha = "";
		
		try {
			strFecha = format.format(fechaDate);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return strFecha;
	}

}
