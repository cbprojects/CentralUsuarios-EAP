package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.ContenedorUDTB;

public interface IContenedorDao {

	/**
	 * 
	 * @return
	 */
	List<ContenedorUDTB> buscarContenedoresActivos();

}
