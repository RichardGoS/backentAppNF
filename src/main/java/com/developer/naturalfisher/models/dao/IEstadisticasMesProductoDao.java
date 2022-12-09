package com.developer.naturalfisher.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.developer.naturalfisher.models.entity.EstadisticasMesProducto;

/**
 * Fase 4 Tarea 1
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 01/08/2022
 */
public interface IEstadisticasMesProductoDao extends JpaRepository<EstadisticasMesProducto, Long> {
	
	//SELECT * FROM estadisticas_mes_producto WHERE MONTH(fecha) = 2 AND YEAR(fecha) = 2022;
	@Query(nativeQuery = false,value = " SELECT e FROM EstadisticasMesProducto e WHERE MONTH(fecha) = ?1 and YEAR(fecha) = ?2")
	List<EstadisticasMesProducto> findByMes(int mount, int year);
	
	//SELECT * FROM estadisticas_mes_producto WHERE MONTH(fecha) = 4 AND YEAR(fecha) = 2022 AND producto_id = 1;
	@Query(nativeQuery = false,value = " SELECT e FROM EstadisticasMesProducto e WHERE MONTH(fecha) = ?1 and YEAR(fecha) = ?2 and producto_id = ?3")
	EstadisticasMesProducto findByMesProducto(int mount, int year, Long producto_id);

}
