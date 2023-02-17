package com.project.cafe.CentralUsuarios.dao;



import java.util.List;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.project.cafe.CentralUsuarios.model.PrestamoHistoricoTB;


@EnableTransactionManagement
public interface IPrestamoHistoricoDao {

	List<PrestamoHistoricoTB> buscarPrestamoHistorico(Long idUd);

	PrestamoHistoricoTB crearPrestamoHistorico(PrestamoHistoricoTB prestamoHis);
	
}
