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

import com.project.cafe.CentralUsuarios.dto.RequestConsultarPorIDDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarUsuarioClienteDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.ClienteTB;
import com.project.cafe.CentralUsuarios.model.UsuarioClienteTB;
import com.project.cafe.CentralUsuarios.service.IUsuarioClienteService;
import com.project.cafe.CentralUsuarios.util.ConstantesTablasNombre;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;
import com.project.cafe.CentralUsuarios.util.PropertiesUtil;
import com.project.cafe.CentralUsuarios.util.Util;

@RestController
@RequestMapping("/central/UsuarioCliente")
public class ControladorRestUsuarioCliente {

	@Autowired
	private IUsuarioClienteService usuarioClienteService;	

	// CONSULTA
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/crearUsuarioCliente")
	public ResponseEntity<UsuarioClienteTB> crearUsuarioCliente(@RequestBody UsuarioClienteTB usuarioCliente) {
		try {
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_USUARIO_CLIENTE_TB, usuarioCliente);

			UsuarioClienteTB newUsuarioCliente = new UsuarioClienteTB();
			if (errores.isEmpty()) {
				// validar rol unico
				if (validarUsuarioClienteUnicoCrear(usuarioCliente.getUsuario().getId(), usuarioCliente.getCliente().getId())) {
					newUsuarioCliente = usuarioClienteService.crearUsuarioCliente(usuarioCliente);
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

			return new ResponseEntity<UsuarioClienteTB>(newUsuarioCliente, HttpStatus.OK);
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	private boolean validarUsuarioClienteUnicoCrear(long idU, long idS) {
		List<UsuarioClienteTB> listaUsuarioCliente = usuarioClienteService.buscarUsuarioClientePorId(idU,idS);
		if (listaUsuarioCliente == null || listaUsuarioCliente.isEmpty()) {
			return true;
		}
		return false;
	}

	// UPDATE

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/modificarUsuarioCliente")
	public ResponseEntity<UsuarioClienteTB> modificarUsuarioCliente(@RequestBody UsuarioClienteTB usuarioCliente) {
		try {
			UsuarioClienteTB newUsuarioCliente = new UsuarioClienteTB();
			// validaciones de campos vacios o valores incorrectos
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_USUARIO_SEDE_TB, usuarioCliente);
			if (errores.isEmpty()) {
				// validar rol unico
				if (validarUsuarioClienteUnicoEditar(usuarioCliente.getUsuario().getId(), usuarioCliente.getCliente().getId(),usuarioCliente.getId())) {
					newUsuarioCliente = usuarioClienteService.modificarUsuarioCliente(usuarioCliente);
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
			return new ResponseEntity<UsuarioClienteTB>(newUsuarioCliente, HttpStatus.OK);
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}

	}

	private boolean validarUsuarioClienteUnicoEditar(long idU, long idC,long id) {
		List<UsuarioClienteTB> listaUsuarioCliente = usuarioClienteService.buscarUsuarioClientePorId(idU,idC);
		if (listaUsuarioCliente == null || listaUsuarioCliente.isEmpty()) {
			return true;
		} else {
			if (listaUsuarioCliente.get(0).getId() == (id)) {
				return true;
			}
		}
		return false;
	}

	// CONSULTA

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarUsuarioClienteFiltros")
	public ResponseConsultarDTO<UsuarioClienteTB> consultarUsuarioClienteFiltros(@RequestBody RequestConsultarUsuarioClienteDTO request) {
		try {
			return usuarioClienteService.consultarUsuarioClienteFiltros(request);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/buscarClientesActivosPorUsuario")
	public List<ClienteTB> buscarClientesActivosPorUsuario(@RequestBody RequestConsultarPorIDDTO request) {
		try {
			return usuarioClienteService.buscarClientesActivosPorUsuario(request.getId());
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

}
