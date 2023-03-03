package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.CrearBodegaDTO;
import com.project.cafe.CentralUsuarios.model.BodegaTB;

public interface IBodegaService {

	/**
	 * 
	 * @param idSede
	 * @return
	 */
	List<BodegaTB> buscarBodegasActivasPorSede(Long idSede);

	/**
	 * servicio que crea la bodega y sus respectivos bloques, cuerpos,estantes y entrepanos
	 * @param crearBodegaDTO
	 * @return
	 */
    void crearBodega(CrearBodegaDTO crearBodegaDTO);
}
