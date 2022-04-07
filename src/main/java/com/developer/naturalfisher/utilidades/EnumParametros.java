package com.developer.naturalfisher.utilidades;

/**
 * de RagooS
 * Autor: Richard Gomez O.
 * Para: EmpresaDevelopers.Backend.NaturalFisher
 * Fecha: 12/01/2021
 */

public enum EnumParametros {
	
	/**
	 * Estados de algunos datos como productos, clientes
	 */
	ESTADO_INACTIVO("INACTIVO"),
	ESTADO_ACTIVO("ACTIVO");
	
	private final String valor;

	EnumParametros(String pCodigo){
        this.valor = pCodigo;
    }

    public String getValor(){
        return valor;
    }

}
