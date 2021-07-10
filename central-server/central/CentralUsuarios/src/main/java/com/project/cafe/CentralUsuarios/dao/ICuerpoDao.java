package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.CuerpoTB;

public interface ICuerpoDao {

/**
 * 
 * @param idBloque
 * @return
 */
	List<CuerpoTB> buscarCuerposActivosPorBloque(Long idBloque);

}
