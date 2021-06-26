package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.project.cafe.CentralUsuarios.model.SociedadTB;

@EnableTransactionManagement
public interface ISociedadDao {
	
	/*
	 * Metodo para consultar las sociedades activas
	 */
	List<SociedadTB> buscarSociedadActiva();
	
	

}
