package com.project.cafe.CentralUsuarios.controller;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarSedesActivasDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarUsuarioSedeDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.SedeTB;
import com.project.cafe.CentralUsuarios.model.UsuarioSedeTB;
import com.project.cafe.CentralUsuarios.service.IUsuarioSedeService;
import com.project.cafe.CentralUsuarios.util.ConstantesTablasNombre;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;
import com.project.cafe.CentralUsuarios.util.PropertiesUtil;
import com.project.cafe.CentralUsuarios.util.Util;

@RestController
@RequestMapping("/central/UsuarioSede")
public class ControladorRestUsuarioSede {

	@Autowired
	private IUsuarioSedeService usuarioSedeService;	

	// CONSULTA
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/crearUsuarioSede")
	public ResponseEntity<UsuarioSedeTB> crearUsuarioSede(@RequestBody UsuarioSedeTB usuarioSede) {
		try {
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_USUARIO_SEDE_TB, usuarioSede);

			UsuarioSedeTB newUsuarioSede = new UsuarioSedeTB();
			if (errores.isEmpty()) {
				// validar rol unico
				if (validarUsuarioSedeUnicoCrear(usuarioSede.getUsuario().getId(), usuarioSede.getSede().getId())) {
					newUsuarioSede = usuarioSedeService.crearUsuarioSede(usuarioSede);
				} else {
					String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
					String mensajeErrores = ConstantesValidaciones.USUARIO_SEDE_REPETIDO;

					throw new ModelNotFoundException(erroresTitle + mensajeErrores);
				}

			} else {
				StringBuilder mensajeErrores = new StringBuilder();
				String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");

				for (String error : errores) {
					mensajeErrores.append(error + "|");
				}

				throw new ModelNotFoundException(erroresTitle + mensajeErrores);
			}

			return new ResponseEntity<UsuarioSedeTB>(newUsuarioSede, HttpStatus.OK);
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	private boolean validarUsuarioSedeUnicoCrear(long idU, long idS) {
		List<UsuarioSedeTB> listaUsuarioSede = usuarioSedeService.buscarUsuarioSedePorId(idU,idS);
		if (listaUsuarioSede == null || listaUsuarioSede.isEmpty()) {
			return true;
		}
		return false;
	}

	// UPDATE

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/modificarUsuarioSede")
	public ResponseEntity<UsuarioSedeTB> modificarUsuarioSede(@RequestBody UsuarioSedeTB usuarioSede) {
		try {
			UsuarioSedeTB newUsuarioSede = new UsuarioSedeTB();
			// validaciones de campos vacios o valores incorrectos
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_USUARIO_SEDE_TB, usuarioSede);
			if (errores.isEmpty()) {
				// validar rol unico
				if (validarUsuarioSedeUnicoEditar(usuarioSede.getUsuario().getId(), usuarioSede.getSede().getId(),usuarioSede.getId())) {
					newUsuarioSede = usuarioSedeService.modificarUsuarioSede(usuarioSede);
				} else {
					String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
					String mensajeErrores = ConstantesValidaciones.USUARIO_SEDE_REPETIDO;

					throw new ModelNotFoundException(erroresTitle + mensajeErrores);
				}
			} else {
				String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
				StringBuilder mensajeErrores = new StringBuilder();
				for (String error : errores) {
					mensajeErrores.append(error + "|");
				}

				throw new ModelNotFoundException(erroresTitle + mensajeErrores);
			}
			return new ResponseEntity<UsuarioSedeTB>(newUsuarioSede, HttpStatus.OK);
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}

	}

	private boolean validarUsuarioSedeUnicoEditar(long idU, long idS,long id) {
		List<UsuarioSedeTB> listaUsuarioSede = usuarioSedeService.buscarUsuarioSedePorId(idU,idS);
		if (listaUsuarioSede == null || listaUsuarioSede.isEmpty()) {
			return true;
		} else {
			if (listaUsuarioSede.get(0).getId() == (id)) {
				return true;
			}
		}
		return false;
	}

	// CONSULTA

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarUsuarioSedeFiltros")
	public ResponseConsultarDTO<UsuarioSedeTB> consultarUsuarioSedeFiltros(@RequestBody RequestConsultarUsuarioSedeDTO request) {
		try {
			return usuarioSedeService.consultarUsuarioSedeFiltros(request);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/buscarSedesActivasPorUsuario")
	public List<SedeTB> buscarSedesActivasPorUsuario(@RequestBody RequestConsultarSedesActivasDTO request) {
		try {
			return usuarioSedeService.buscarSedesActivasPorUsuario(request.getEmail());
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

}
