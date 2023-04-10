package com.project.cafe.CentralUsuarios.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IActaDao;
import com.project.cafe.CentralUsuarios.dao.IRolPerfilDao;
import com.project.cafe.CentralUsuarios.dao.IUsuarioDao;
import com.project.cafe.CentralUsuarios.dto.MailDTO;
import com.project.cafe.CentralUsuarios.dto.RequestAprobarRecepcionDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarActaDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseMensajeCodigoDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.ActaTB;
import com.project.cafe.CentralUsuarios.model.PerfilTB;
import com.project.cafe.CentralUsuarios.model.UsuarioTB;
import com.project.cafe.CentralUsuarios.service.IActaService;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;
import com.project.cafe.CentralUsuarios.util.UtilMail;

@Service
public class ActaServiceImpl implements IActaService {

	@Autowired
	private IActaDao actaDAO;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IRolPerfilDao rolPerfilDao;
	
	@Value("${email.servidor}")
	private String EMAIL_SERVIDOR;

	@Value("${ruta.recordar.clave}")
	private String RUTA_RECORDAR_CLAVE;

	@Autowired
	private UtilMail mailUtil;
	
	@Override
	public ResponseConsultarDTO<ActaTB> consultarActaFiltros(RequestConsultarActaDTO request) {
		return actaDAO.consultarActaFiltros(request);
	}

	@Override
	public ResponseMensajeCodigoDTO aprobacionActa(RequestAprobarRecepcionDTO request) {
		List<ActaTB> lstActa =new ArrayList<>();
		lstActa=actaDAO.buscarActaPorId(request.getIdUD());
		ResponseMensajeCodigoDTO response = new ResponseMensajeCodigoDTO();
		try {
			if(lstActa.isEmpty()) {
				response.setCodigo("1");
				response.setMensaje("no existe ninguna acta con ese ID");
			}else {
				ActaTB acta =lstActa.get(0);
				acta.setAprobada(true);
				acta=actaDAO.modificarActa(acta);
				enviarCorreosSatisfactorios(acta);
				response.setCodigo("0");
				response.setMensaje("Acta aprobada con Éxito");
			}
		} catch (Exception e) {
			response.setCodigo("2");
			response.setMensaje(e.toString());
		}
		
		
		return response;
	}

	private void enviarCorreosSatisfactorios(ActaTB acta) {
		MailDTO mailDto = new MailDTO();
		mailDto.setFrom(EMAIL_SERVIDOR);
		mailDto.setTo(acta.getUsuario().getEmail());
		mailDto.setSubject(acta.getCliente().getNombre()+" Acta Aprobada # "+acta.getNumeroFactura());
		List<PerfilTB> lstPerfil=rolPerfilDao.BuscarPerfilPorRolCodigo("ITCADM");
		List<UsuarioTB> lstUsuario;
		if(lstPerfil.isEmpty()) {
			lstUsuario = new ArrayList<>();
		}else {
			List<Long> lstPerfiles=new ArrayList<>();
			for (PerfilTB perfil : lstPerfil) {
				lstPerfiles.add(perfil.getId());
			}
			lstUsuario=usuarioDao.buscarUsuariosAdministrador(lstPerfiles);
		}
		
		String[] miarray;
		if(lstUsuario.isEmpty()) {
			miarray=null;
		}else {
			List<String> lstMail=new ArrayList<>();
			for (UsuarioTB usuario : lstUsuario) {
				lstMail.add(usuario.getEmail());
			}
			miarray = new String[lstMail.size()];
			miarray = lstMail.toArray(miarray);
		}
		Map<String, Object> model = new HashMap<>();
		
		mailDto.setModel(model);

		mailUtil.sendMailActa(mailDto,miarray);
		
	}

	
	
}
