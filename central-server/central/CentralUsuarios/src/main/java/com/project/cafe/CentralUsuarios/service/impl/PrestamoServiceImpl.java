package com.project.cafe.CentralUsuarios.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IPrestamoDao;
import com.project.cafe.CentralUsuarios.dao.IPrestamoHistoricoDao;
import com.project.cafe.CentralUsuarios.dto.RequestEditarPrestamoDTO;
import com.project.cafe.CentralUsuarios.model.PrestamoHistoricoTB;
import com.project.cafe.CentralUsuarios.model.PrestamoTB;
import com.project.cafe.CentralUsuarios.service.IPrestamoService;

@Service
public class PrestamoServiceImpl implements IPrestamoService {

	@Autowired
	private IPrestamoDao prestamoDAO;
	
	@Autowired
	private IPrestamoHistoricoDao prestamoHistoricoDAO;
	
	private final Short activo=1;

	@Transactional
	@Override
	public PrestamoTB crearPrestamo(RequestEditarPrestamoDTO request) {
		PrestamoTB prestamo=convertirPrestamo(request);
		PrestamoHistoricoTB prestamoHis=convertirPrestamoHistorico(request);
		prestamoHis=prestamoHistoricoDAO.crearPrestamoHistorico(prestamoHis);
		return prestamoDAO.crearPrestamo(prestamo);
	}

	private PrestamoHistoricoTB convertirPrestamoHistorico(RequestEditarPrestamoDTO request) {
		PrestamoHistoricoTB prestamoHis=new PrestamoHistoricoTB();
		prestamoHis.setIdUd(request.getIdUd());
		prestamoHis.setResponsable(request.getResponsable());
		prestamoHis.setObservacion(StringUtils.isBlank(request.getObservacion()) ? "PRESTADO: " : "PRESTADO: "+request.getObservacion());
		prestamoHis.setFechaPrestamo(new Date());
		prestamoHis.setUsuarioCreacion(request.getUsuarioCreacion());
		prestamoHis.setUsuarioActualizacion(request.getUsuarioCreacion());
		prestamoHis.setEstado(activo);
		return prestamoHis;
	}

	private PrestamoTB convertirPrestamo(RequestEditarPrestamoDTO request) {
		PrestamoTB prestamo=new PrestamoTB();
		prestamo.setIdUd(request.getIdUd());
		prestamo.setResponsable(request.getResponsable());
		prestamo.setObservacion(request.getObservacion());
		prestamo.setFechaPrestamo(new Date());
		prestamo.setUsuarioCreacion(request.getUsuarioCreacion());
		prestamo.setUsuarioActualizacion(request.getUsuarioCreacion());
		prestamo.setEstado(activo);
		return prestamo;
	}
	
	private PrestamoHistoricoTB convertirPrestamoHistoricoDevolver(RequestEditarPrestamoDTO request) {
		PrestamoHistoricoTB prestamoHis=new PrestamoHistoricoTB();
		prestamoHis.setIdUd(request.getIdUd());
		prestamoHis.setResponsable(request.getResponsable());
		prestamoHis.setObservacion(StringUtils.isBlank(request.getObservacion()) ? "DEVUELTO: " : "DEVUELTO: "+request.getObservacion());
		prestamoHis.setFechaPrestamo(new Date());
		prestamoHis.setUsuarioCreacion(request.getUsuarioCreacion());
		prestamoHis.setUsuarioActualizacion(request.getUsuarioCreacion());
		prestamoHis.setEstado(activo);
		return prestamoHis;
	}

	@Transactional
	@Override
	public void eliminarPrestamo(RequestEditarPrestamoDTO request) {
		PrestamoHistoricoTB prestamoHis=convertirPrestamoHistoricoDevolver(request);
		prestamoHis=prestamoHistoricoDAO.crearPrestamoHistorico(prestamoHis);
		prestamoDAO.eliminarPrestamo(request.getIdUd());
	}
	
	@Override
	public List<PrestamoTB> buscarPrestamoVigente(Long idUd) {
		return prestamoDAO.buscarPrestamoVigente(idUd);
	}
	
	@Override
	public List<PrestamoHistoricoTB> buscarPrestamoHistorico(Long idUd) {
		return prestamoHistoricoDAO.buscarPrestamoHistorico(idUd);
	}
	 
}
