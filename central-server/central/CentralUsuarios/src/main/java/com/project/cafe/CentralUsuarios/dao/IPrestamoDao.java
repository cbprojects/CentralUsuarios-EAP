package com.project.cafe.CentralUsuarios.dao;



import java.util.List;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.project.cafe.CentralUsuarios.model.PrestamoTB;


@EnableTransactionManagement
public interface IPrestamoDao {

	List<PrestamoTB> buscarPrestamoVigente(Long idUd);

	PrestamoTB crearPrestamo(PrestamoTB prestamo);

	void eliminarPrestamo(Long idUd);
	
	

}
