package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.RequestEditarPrestamoDTO;
import com.project.cafe.CentralUsuarios.model.PrestamoHistoricoTB;
import com.project.cafe.CentralUsuarios.model.PrestamoTB;

public interface IPrestamoService {

	List<PrestamoTB> buscarPrestamoVigente(Long idUd);

	List<PrestamoHistoricoTB> buscarPrestamoHistorico(Long idUd);

	PrestamoTB crearPrestamo(RequestEditarPrestamoDTO request);

	void eliminarPrestamo(RequestEditarPrestamoDTO request);

}
