package com.developer.naturalfisher.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.developer.naturalfisher.models.entity.Venta;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 22/06/2021
 */

public interface IVentaDao extends JpaRepository<Venta, Long> {
	
	//
	@Query(nativeQuery = false,value = " SELECT v FROM Venta v WHERE fecha BETWEEN  ?1 and ?2 ORDER BY fecha")
	List<Venta> consultaRangoFechaDefault(Date fecha1, Date fecha2);
	
	@Query(nativeQuery = false,value = " SELECT v FROM Venta v WHERE fecha BETWEEN  ?1 and ?2 ORDER BY fecha")
	List<Venta> consultaVentasEnFecha(Date fecha1, Date fecha2);
	
	//SELECT * FROM `venta` WHERE fecha >= '2021-07-26 00:00:00' AND fecha <= '2021-07-26 23:59:59'
	
	
	//SELECT * FROM venta WHERE MONTH(fecha) = 12 AND YEAR(fecha) = 2021
	@Query(nativeQuery = false,value = " SELECT v FROM Venta v WHERE MONTH(fecha) = ?1 and YEAR(fecha) = ?2 ORDER BY fecha")
	List<Venta> consultaVentasEnMesAño(int mount, int year);
	
	//SELECT SUM(total) FROM venta WHERE MONTH(fecha) = 12 AND YEAR(fecha) = 2021
	@Query(nativeQuery = false,value = " SELECT SUM(total) FROM Venta v WHERE MONTH(fecha) = ?1 and YEAR(fecha) = ?2 ORDER BY fecha")
	Double consultarTotalVentaMesAño(int mount, int year);
	
	//SELECT SUM(total) FROM venta WHERE fecha BETWEEN  '2021-12-01' and '2021-12-31'
	@Query(nativeQuery = false,value = " SELECT SUM(total) FROM Venta v WHERE fecha BETWEEN  ?1 and ?2 ORDER BY fecha")
	Double consultarTotalVentaEnRangoFecha(Date fecha1, Date fecha2);
	
	//SELECT * FROM `venta` WHERE fecha >= '2021-12-29 00:00:00' AND id != 24
	@Query(nativeQuery = false,value = " SELECT v FROM Venta v WHERE fecha >= ?1 and id != ?2 ORDER BY fecha")
	List<Venta> consultarVentasDespuesFechaInventarioDiferenteVentaRealizada(Date fecha, Long id);
	
	//SELECT * FROM `venta` WHERE fecha >= '2021-12-29 00:00:00'
	@Query(nativeQuery = false,value = " SELECT v FROM Venta v WHERE fecha >= ?1 ORDER BY fecha")
	List<Venta> consultarVentasDespuesFecha(Date fecha);
	

}
