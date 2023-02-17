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

import com.project.cafe.CentralUsuarios.dto.MasivoDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarMasivoDTO;
import com.project.cafe.CentralUsuarios.dto.RequestCrearMasivoDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.AreaTB;
import com.project.cafe.CentralUsuarios.model.ClienteTB;
import com.project.cafe.CentralUsuarios.model.ContenedorUDTB;
import com.project.cafe.CentralUsuarios.model.SedeTB;
import com.project.cafe.CentralUsuarios.model.TipoUDTB;
import com.project.cafe.CentralUsuarios.service.IAreaService;
import com.project.cafe.CentralUsuarios.service.IClienteService;
import com.project.cafe.CentralUsuarios.service.IContenedorService;
import com.project.cafe.CentralUsuarios.service.ISedeService;
import com.project.cafe.CentralUsuarios.service.ITipoUDService;
import com.project.cafe.CentralUsuarios.util.ConstantesTablasNombre;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;
import com.project.cafe.CentralUsuarios.util.PropertiesUtil;
import com.project.cafe.CentralUsuarios.util.Util;

@RestController
@RequestMapping("/central/Masivo")
public class ControladorRestMasivo {

	
	@Autowired
	private IAreaService areaService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IContenedorService contendorService;
	
	@Autowired
	private ITipoUDService tipoUDService;
	
	@Autowired
	private ISedeService sedeService;

	// CREATE

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/crearMasivo")
	public ResponseEntity<MasivoDTO> crearMasivo(@RequestBody RequestCrearMasivoDTO request) {
		/**/
		try {
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_MASIVO_TB, request);
			MasivoDTO masivo = new MasivoDTO();
			if (errores.isEmpty()) {
				// validar unicos por masivo 1 area 2 cliente 3 contenedor 4 tipoUD 5 sede
				if(request.getTipoMasivo().intValue()==1) {
					if (validarAreaUnicoCrear(request.getMasivoDTO().getNombre1())) {
						masivo = areaService.crearArea(request.getMasivoDTO());
					} else {
						String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
						String mensajeErrores = ConstantesValidaciones.MSG_AREA_REPETIDO;

						throw new ModelNotFoundException(erroresTitle + mensajeErrores);
					}
					
				}else if(request.getTipoMasivo().intValue()==2) {
					if (validarClienteUnicoCrear(request.getMasivoDTO().getNombre1())) {
						masivo = clienteService.crearCliente(request.getMasivoDTO());
					} else {
						String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
						String mensajeErrores = ConstantesValidaciones.MSG_CLIENTE_REPETIDO;

						throw new ModelNotFoundException(erroresTitle + mensajeErrores);
					}
				}else if(request.getTipoMasivo().intValue()==3) {
					if (validarContenedorUnicoCrear(request.getMasivoDTO().getNombre1())) {
						masivo = contendorService.crearContenedor(request.getMasivoDTO());
					} else {
						String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
						String mensajeErrores = ConstantesValidaciones.MSG_CONTENEDOR_REPETIDO;

						throw new ModelNotFoundException(erroresTitle + mensajeErrores);
					}
				}else if(request.getTipoMasivo().intValue()==4) {
					if (validartipoUdUnicoCrear(request.getMasivoDTO().getNombre1())) {
						masivo = tipoUDService.crearTipoUD(request.getMasivoDTO());
					} else {
						String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
						String mensajeErrores = ConstantesValidaciones.MSG_TIPOUD_REPETIDO;

						throw new ModelNotFoundException(erroresTitle + mensajeErrores);
					}
				}else if(request.getTipoMasivo().intValue()==5) {
					if (validarSedeUnicoCrear(request.getMasivoDTO().getNombre1())) {
						masivo = sedeService.crearSede(request.getMasivoDTO());
					} else {
						String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
						String mensajeErrores = ConstantesValidaciones.MSG_SEDE_REPETIDO;

						throw new ModelNotFoundException(erroresTitle + mensajeErrores);
					}
				}
			} else {
				StringBuilder mensajeErrores = new StringBuilder();
				String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");

				for (String error : errores) {
					mensajeErrores.append(error + "|");
				}

				throw new ModelNotFoundException(erroresTitle + mensajeErrores);
			}

			return new ResponseEntity<MasivoDTO>(masivo, HttpStatus.OK);
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}
	
	private boolean validarSedeUnicoCrear(String nombre) {
		List<SedeTB> listaSede = sedeService.buscarSedePorCodigo(nombre);
		if (listaSede == null || listaSede.isEmpty()) {
			return true;
		}
		return false;
	}

	private boolean validarAreaUnicoCrear(String nombre) {
		List<AreaTB> listaArea = areaService.buscarAreaPorCodigo(nombre);
		if (listaArea == null || listaArea.isEmpty()) {
			return true;
		}
		return false;
	}
	
	private boolean validarClienteUnicoCrear(String nombre) {
		List<ClienteTB> listaCliente = clienteService.buscarClientePorCodigo(nombre);
		if (listaCliente == null || listaCliente.isEmpty()) {
			return true;
		}
		return false;
	}
	private boolean validarContenedorUnicoCrear(String nombre) {
		List<ContenedorUDTB> listaContenedor = contendorService.buscarContenedorPorCodigo(nombre);
		if (listaContenedor == null || listaContenedor.isEmpty()) {
			return true;
		}
		return false;
	}

	private boolean validartipoUdUnicoCrear(String nombre) {
		List<TipoUDTB> listaTipo = tipoUDService.buscarTipoUdPorCodigo(nombre);
		if (listaTipo == null || listaTipo.isEmpty()) {
			return true;
		}
		return false;
	}
	// UPDATE

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/modificarMasivo")
	public ResponseEntity<MasivoDTO> modificarRol(@RequestBody RequestCrearMasivoDTO request) {
		try {
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_MASIVO_TB, request);
			MasivoDTO masivo = new MasivoDTO();
			if (errores.isEmpty()) {
				// validar unicos por masivo 1 area 2 cliente 3 contenedor 4 tipoUD 5 sede
				if(request.getTipoMasivo().intValue()==1) {
					if (validarAreaUnicoEditar(request.getMasivoDTO().getNombre1(),request.getMasivoDTO().getIdMasivo())) {
						masivo = areaService.modificarArea(request.getMasivoDTO());
					} else {
						String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
						String mensajeErrores = ConstantesValidaciones.MSG_AREA_REPETIDO;

						throw new ModelNotFoundException(erroresTitle + mensajeErrores);
					}
					
				}else if(request.getTipoMasivo().intValue()==2) {
					if (validarClienteUnicoEditar(request.getMasivoDTO().getNombre1(),request.getMasivoDTO().getIdMasivo())) {
						masivo = clienteService.modificarCliente(request.getMasivoDTO());
					} else {
						String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
						String mensajeErrores = ConstantesValidaciones.MSG_CLIENTE_REPETIDO;

						throw new ModelNotFoundException(erroresTitle + mensajeErrores);
					}
				}else if(request.getTipoMasivo().intValue()==3) {
					if (validarContenedorUnicoEditar(request.getMasivoDTO().getNombre1(),request.getMasivoDTO().getIdMasivo())) {
						masivo = contendorService.modificarContenedor(request.getMasivoDTO());
					} else {
						String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
						String mensajeErrores = ConstantesValidaciones.MSG_CONTENEDOR_REPETIDO;

						throw new ModelNotFoundException(erroresTitle + mensajeErrores);
					}
				}else if(request.getTipoMasivo().intValue()==4) {
					if (validarTipoUnicoEditar(request.getMasivoDTO().getNombre1(),request.getMasivoDTO().getIdMasivo())) {
						masivo = tipoUDService.modificarTipoUd(request.getMasivoDTO());
					} else {
						String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
						String mensajeErrores = ConstantesValidaciones.MSG_TIPOUD_REPETIDO;

						throw new ModelNotFoundException(erroresTitle + mensajeErrores);
					}
				}else if(request.getTipoMasivo().intValue()==5) {
					if (validarSedeUnicoEditar(request.getMasivoDTO().getNombre1(),request.getMasivoDTO().getIdMasivo())) {
						masivo = sedeService.modificarSede(request.getMasivoDTO());
					} else {
						String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
						String mensajeErrores = ConstantesValidaciones.MSG_SEDE_REPETIDO;

						throw new ModelNotFoundException(erroresTitle + mensajeErrores);
					}
				}
			} else {
				String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
				StringBuilder mensajeErrores = new StringBuilder();
				for (String error : errores) {
					mensajeErrores.append(error + "|");
				}

				throw new ModelNotFoundException(erroresTitle + mensajeErrores);
			}
			return new ResponseEntity<MasivoDTO>(masivo, HttpStatus.OK);
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}

	}
	
	private boolean validarSedeUnicoEditar(String codigo, long id) {
		List<SedeTB> listaSede = sedeService.buscarSedePorCodigo(codigo);
		if (listaSede == null || listaSede.isEmpty()) {
			return true;
		} else {
			if (listaSede.get(0).getId() == (id)) {
				return true;
			}
		}
		return false;
	}

	private boolean validarAreaUnicoEditar(String codigo, long id) {
		List<AreaTB> listaArea = areaService.buscarAreaPorCodigo(codigo);
		if (listaArea == null || listaArea.isEmpty()) {
			return true;
		} else {
			if (listaArea.get(0).getId() == (id)) {
				return true;
			}
		}
		return false;
	}
	private boolean validarClienteUnicoEditar(String codigo, long id) {
		List<ClienteTB> listaCliente = clienteService.buscarClientePorCodigo(codigo);
		if (listaCliente == null || listaCliente.isEmpty()) {
			return true;
		} else {
			if (listaCliente.get(0).getId() == (id)) {
				return true;
			}
		}
		return false;
	}
	private boolean validarContenedorUnicoEditar(String codigo, long id) {
		List<ContenedorUDTB> listaCon = contendorService.buscarContenedorPorCodigo(codigo);
		if (listaCon == null || listaCon.isEmpty()) {
			return true;
		} else {
			if (listaCon.get(0).getId() == (id)) {
				return true;
			}
		}
		return false;
	}
	private boolean validarTipoUnicoEditar(String codigo, long id) {
		List<TipoUDTB> listaTipo = tipoUDService.buscarTipoUdPorCodigo(codigo);
		if (listaTipo == null || listaTipo.isEmpty()) {
			return true;
		} else {
			if (listaTipo.get(0).getId() == (id)) {
				return true;
			}
		}
		return false;
	}

	// CONSULTA

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarMasivo")
	public ResponseConsultarDTO<MasivoDTO> consultarRolFiltros(@RequestBody RequestConsultarMasivoDTO request) {
		ResponseConsultarDTO<MasivoDTO> response =new ResponseConsultarDTO<>();
		try {
			if(request.getTipoMasivo().intValue()==1) {
				response= areaService.consultarAreaFiltros(request);
			}else if(request.getTipoMasivo().intValue()==2) {
				response= clienteService.consultarClienteFiltros(request);
			}else if(request.getTipoMasivo().intValue()==3) {
				response= contendorService.consultarContenedorFiltros(request);
			}else if(request.getTipoMasivo().intValue()==4) {
				response= tipoUDService.consultarTipoUDFiltros(request);
			}else if(request.getTipoMasivo().intValue()==5) {
				response= sedeService.consultarSedeFiltros(request);
			}
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
		return response;
	}

}
