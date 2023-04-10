package com.project.cafe.CentralUsuarios.service;

import com.project.cafe.CentralUsuarios.dto.RequestAprobarRecepcionDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarActaDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseMensajeCodigoDTO;
import com.project.cafe.CentralUsuarios.model.ActaTB;

public interface IActaService {

	/**
	 * 
	 * @param request
	 * @return
	 */
	ResponseConsultarDTO<ActaTB> consultarActaFiltros(RequestConsultarActaDTO request);

	/**
	 * 
	 * @param request
	 * @return
	 */
	ResponseMensajeCodigoDTO aprobacionActa(RequestAprobarRecepcionDTO request);

}
