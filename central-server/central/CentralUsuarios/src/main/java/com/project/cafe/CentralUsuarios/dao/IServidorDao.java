package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.ServidorTB;

public interface IServidorDao {

	List<ServidorTB> consultarServidorActivo();

}
