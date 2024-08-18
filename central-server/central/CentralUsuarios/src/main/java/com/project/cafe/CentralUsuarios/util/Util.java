package com.project.cafe.CentralUsuarios.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import com.project.cafe.CentralUsuarios.dto.*;
import com.project.cafe.CentralUsuarios.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public abstract class Util {

	public static boolean esCorreoValido(String email) {
		Matcher mather = ConstantesValidaciones.EMAIL_PATTERN.matcher(email.toLowerCase());
		return mather.find();
	}

	public static boolean tieneCantidadCharPermitida(String cadenaValidar, int cantidadChar) {
		boolean result = false;
		if (!StringUtils.isBlank(cadenaValidar)) {
			result = cadenaValidar.length() <= cantidadChar;
		}
		return result;
	}

	public static List<String> validaDatos(String tabla, Object entidadTB) {
		List<String> errores = new ArrayList<>();
		if (!StringUtils.isBlank(tabla)) {
			switch (tabla) {
			case ConstantesTablasNombre.MRA_PERFIL_TB:
				errores = validarPerfilAutor((PerfilTB) entidadTB);
				break;
			case ConstantesTablasNombre.MRA_ROL_TB:
				errores = validarRol((RolTB) entidadTB);
				break;

			case ConstantesTablasNombre.MRA_USUARIO_TB:
				errores = validarUsuario((UsuarioTB) entidadTB);
				break;

			case ConstantesTablasNombre.MRA_CAJA_TB:
				errores = validarCaja((CajaTB) entidadTB);
				break;

			case ConstantesTablasNombre.MRA_UNIDAD_DOCUMENTAL_TB:
				errores = validarUnidadDocumental((UnidadDocumentalTB) entidadTB);
				break;
			case ConstantesTablasNombre.MRA_UNIDAD_DOCUMENTAL_TB_MASIVO:
				errores = validarUnidadDocumentalMasivo((List<UnidadDocumentalTB>) entidadTB);
				break;
			case ConstantesTablasNombre.MRA_MASIVO_TB:
				errores = validarMasivo((RequestCrearMasivoDTO) entidadTB);
				break;
			case ConstantesTablasNombre.MRA_PRESTAMO_TB:
				errores = validarPrestamo((RequestEditarPrestamoDTO) entidadTB);
				break;
			case ConstantesTablasNombre.MRA_SOCIEDAD_TB:
				errores = validarSociedad((SociedadTB) entidadTB);
				break;
			case ConstantesTablasNombre.MRA_SOCIEDAD_AREA_TB:
				errores = validarSociedadArea((SociedadAreaTB) entidadTB);
				break;
			case ConstantesTablasNombre.MRA_USUARIO_SEDE_TB:
				errores = validarUsuarioSede((UsuarioSedeTB) entidadTB);
				break;
			case ConstantesTablasNombre.MRA_BODEGA_TB:
				errores = validarBodega((BodegaTB) entidadTB);
				break;
			case ConstantesTablasNombre.MRA_PROYECTO_TB:
				errores = validarProyecto((ProyectoTB) entidadTB);
				break;
			case ConstantesTablasNombre.MRA_PERMISOS_BODEGA_TB:
				errores = validarPermisosBodega((PermisosBodegaTB) entidadTB);
				break;
			case ConstantesTablasNombre.MRA_USUARIO_CLIENTE_TB:
				errores = validarUsuarioCliente((UsuarioClienteTB) entidadTB);
				break;
			}
		} else {
			errores.add(ConstantesValidaciones.TABLA_NO_ESTABLECIDA_VALIDACIONES);
		}

		return errores;
	}

	private static List<String> validarUnidadDocumentalMasivo(List<UnidadDocumentalTB> entidadTB) {
		List<String> errores = new ArrayList<>();
		if (entidadTB.isEmpty()) {
			errores.add("Lista vacía");
		} else {
			for (int i = 0; i < entidadTB.size(); i++) {
				if (StringUtils.isBlank(entidadTB.get(i).getCodigo())) {
					errores.add(ConstantesValidaciones.CODIGO_UNIDAD_DOCUMENTAL + " " + (i + 1) + " "
							+ ConstantesValidaciones.VALOR_VACIO);
				}
				if (StringUtils.isBlank(entidadTB.get(i).getNombre())) {
					errores.add(ConstantesValidaciones.NOMBRE_UNIDAD_DOCUMENTAL + " " + (i + 1) + " "
							+ ConstantesValidaciones.VALOR_VACIO);
				}
				if (StringUtils.isBlank(entidadTB.get(i).getCodigoBarra())) {
					errores.add(ConstantesValidaciones.CODIGO_BARRAS_UNIDAD_DOCUMENTAL + " " + (i + 1) + " "
							+ ConstantesValidaciones.VALOR_VACIO);
				}
				if (entidadTB.get(i).getFechaRecibe() == null) {
					errores.add(ConstantesValidaciones.FECHA_RECIBE_UNIDAD_DOCUMENTAL + " " + (i + 1) + " "
							+ ConstantesValidaciones.VALOR_VACIO);
				}
				if (entidadTB.get(i).getFechaIni() == null) {
					errores.add(ConstantesValidaciones.FECHA_INICIO_UNIDAD_DOCUMENTAL + " " + (i + 1) + " "
							+ ConstantesValidaciones.VALOR_VACIO);
				}
				if (entidadTB.get(i).getFechaFin() == null) {
					errores.add(ConstantesValidaciones.FECHA_FIN_UNIDAD_DOCUMENTAL + " " + (i + 1) + " "
							+ ConstantesValidaciones.VALOR_VACIO);
				}
				if (entidadTB.get(i).getTipoDocumental() == null) {
					errores.add(ConstantesValidaciones.TIPO_DOCUMENTAL_UNIDAD_DOCUMENTAL + " " + (i + 1) + " "
							+ ConstantesValidaciones.SIN_TIPO_DOCUMENTAL_UNIDAD_DOCUMENTAL);
				} else {
					if (entidadTB.get(i).getTipoDocumental().getId() == 0) {
						errores.add(ConstantesValidaciones.TIPO_DOCUMENTAL_UNIDAD_DOCUMENTAL + " " + (i + 1) + " "
								+ ConstantesValidaciones.SIN_TIPO_DOCUMENTAL_UNIDAD_DOCUMENTAL);
					}
				}
				if (entidadTB.get(i).getContenedor() == null) {
					errores.add(ConstantesValidaciones.CONTENEDOR_UNIDAD_DOCUMENTAL + " " + (i + 1) + " "
							+ ConstantesValidaciones.SIN_CONTENEDOR_UNIDAD_DOCUMENTAL);
				} else {
					if (entidadTB.get(i).getContenedor().getId() == 0) {
						errores.add(ConstantesValidaciones.CONTENEDOR_UNIDAD_DOCUMENTAL + " " + (i + 1) + " "
								+ ConstantesValidaciones.SIN_CONTENEDOR_UNIDAD_DOCUMENTAL);
					}
				}
				if (entidadTB.get(i).getProyecto() == null) {
					errores.add(ConstantesValidaciones.PROYECTO_UNIDAD_DOCUMENTAL + " " + (i + 1) + " "
							+ ConstantesValidaciones.SIN_PROYECTO_UNIDAD_DOCUMENTAL);
				} else {
					if (entidadTB.get(i).getProyecto().getId() == 0) {
						errores.add(ConstantesValidaciones.PROYECTO_UNIDAD_DOCUMENTAL + " " + (i + 1) + " "
								+ ConstantesValidaciones.SIN_PROYECTO_UNIDAD_DOCUMENTAL);
					}
				}
				if (entidadTB.get(i).getSociedadArea().getSociedad() == null) {
					errores.add(ConstantesValidaciones.SOCIEDAD_UNIDAD_DOCUMENTAL + " " + (i + 1) + " "
							+ ConstantesValidaciones.SIN_SOCIEDAD_UNIDAD_DOCUMENTAL);
				} else {
					if (entidadTB.get(i).getSociedadArea().getSociedad().getId() == 0) {
						errores.add(ConstantesValidaciones.SOCIEDAD_UNIDAD_DOCUMENTAL + " " + (i + 1) + " "
								+ ConstantesValidaciones.SIN_SOCIEDAD_UNIDAD_DOCUMENTAL);
					}
				}
				if (entidadTB.get(i).getSociedadArea().getArea() == null) {
					errores.add(ConstantesValidaciones.AREA_UNIDAD_DOCUMENTAL
							+ ConstantesValidaciones.SIN_AREA_UNIDAD_DOCUMENTAL);
				} else {
					if (entidadTB.get(i).getSociedadArea().getArea().getId() == 0) {
						errores.add(ConstantesValidaciones.AREA_UNIDAD_DOCUMENTAL
								+ ConstantesValidaciones.SIN_AREA_UNIDAD_DOCUMENTAL);
					}
				}
			}
		}
		return errores;
	}

	private static List<String> validarUsuarioCliente(UsuarioClienteTB entidadTB) {
		List<String> errores = new ArrayList<>();
		if (entidadTB.getUsuario() == null) {
			errores.add(ConstantesValidaciones.USUARIO_USUARIO_CLIENTE + ConstantesValidaciones.VALOR_VACIO);
		} else {
			if (entidadTB.getUsuario().getId() == 0l) {
				errores.add(ConstantesValidaciones.USUARIO_USUARIO_CLIENTE + ConstantesValidaciones.VALOR_VACIO);
			}
		}
		if (entidadTB.getCliente() == null) {
			errores.add(ConstantesValidaciones.USUARIO_CLIENTE_CLIENTE + ConstantesValidaciones.VALOR_VACIO);
		} else {
			if (entidadTB.getCliente().getId() == 0l) {
				errores.add(ConstantesValidaciones.USUARIO_CLIENTE_CLIENTE + ConstantesValidaciones.VALOR_VACIO);
			}
		}
		return errores;
	}

	public static List<String> validarPermisosBodega(PermisosBodegaTB permisosBodega) {
		List<String> errores = new ArrayList<>();

		if (permisosBodega.getCrear() == null) {
			errores.add(ConstantesValidaciones.CREAR_PERMISOS_BODEGA + ConstantesValidaciones.VALOR_VACIO);
		}
		if (permisosBodega.getEditar() == null) {
			errores.add(ConstantesValidaciones.EDITAR_PERMISOS_BODEGA + ConstantesValidaciones.VALOR_VACIO);
		}
		if (permisosBodega.getConsultar() == null) {
			errores.add(ConstantesValidaciones.CONSULTAR_PERMISOS_BODEGA + ConstantesValidaciones.VALOR_VACIO);
		}
		if (permisosBodega.getEliminar() == null) {
			errores.add(ConstantesValidaciones.ELIMINAR_PERMISOS_BODEGA + ConstantesValidaciones.VALOR_VACIO);
		}
		if (permisosBodega.getBodega() == null) {
			errores.add(ConstantesValidaciones.BODEGA_PERMISOS_BODEGA + ConstantesValidaciones.VALOR_VACIO);
		} else {
			if (permisosBodega.getBodega().getId() == 0l) {
				errores.add(ConstantesValidaciones.BODEGA_PERMISOS_BODEGA + ConstantesValidaciones.VALOR_VACIO);
			}
		}
		if (permisosBodega.getUsuario() == null) {
			errores.add(ConstantesValidaciones.USUARIO_PERMISOS_BODEGA + ConstantesValidaciones.VALOR_VACIO);
		} else {
			if (permisosBodega.getUsuario().getId() == 0l) {
				errores.add(ConstantesValidaciones.USUARIO_PERMISOS_BODEGA + ConstantesValidaciones.VALOR_VACIO);
			}
		}
		return errores;
	}

	public static List<String> validarProyecto(ProyectoTB proyectoTB) {
		List<String> errores = new ArrayList<>();

		if (StringUtils.isBlank(proyectoTB.getNombre())) {
			errores.add(ConstantesValidaciones.NOMBRE_PROYECTO + ConstantesValidaciones.VALOR_VACIO);
		}
		if (proyectoTB.getSociedad() == null) {
			errores.add(ConstantesValidaciones.SOCIEDAD_PROYECTO + ConstantesValidaciones.VALOR_VACIO);
		} else {
			if (proyectoTB.getSociedad().getId() == 0l) {
				errores.add(ConstantesValidaciones.SOCIEDAD_PROYECTO + ConstantesValidaciones.VALOR_VACIO);
			}
		}
		return errores;
	}

	public static List<String> validarUsuarioSede(UsuarioSedeTB entidadTB) {
		List<String> errores = new ArrayList<>();
		if (entidadTB.getUsuario() == null) {
			errores.add(ConstantesValidaciones.USUARIO_USUARIO_SEDE + ConstantesValidaciones.VALOR_VACIO);
		} else {
			if (entidadTB.getUsuario().getId() == 0l) {
				errores.add(ConstantesValidaciones.USUARIO_USUARIO_SEDE + ConstantesValidaciones.VALOR_VACIO);
			}
		}
		if (entidadTB.getSede() == null) {
			errores.add(ConstantesValidaciones.SEDE_USUARIO_SEDE + ConstantesValidaciones.VALOR_VACIO);
		} else {
			if (entidadTB.getSede().getId() == 0l) {
				errores.add(ConstantesValidaciones.SEDE_USUARIO_SEDE + ConstantesValidaciones.VALOR_VACIO);
			}
		}
		return errores;
	}

	public static List<String> validarSociedadArea(SociedadAreaTB entidadTB) {
		List<String> errores = new ArrayList<>();
		if (entidadTB.getSociedad() == null) {
			errores.add(ConstantesValidaciones.SOCIEDAD_SOCIEDAD_AREA + ConstantesValidaciones.VALOR_VACIO);
		} else {
			if (entidadTB.getSociedad().getId() == 0l) {
				errores.add(ConstantesValidaciones.SOCIEDAD_SOCIEDAD_AREA + ConstantesValidaciones.VALOR_VACIO);
			}
		}
		if (entidadTB.getArea() == null) {
			errores.add(ConstantesValidaciones.AREA_SOCIEDAD_AREA + ConstantesValidaciones.VALOR_VACIO);
		} else {
			if (entidadTB.getArea().getId() == 0l) {
				errores.add(ConstantesValidaciones.AREA_SOCIEDAD_AREA + ConstantesValidaciones.VALOR_VACIO);
			}
		}
		return errores;
	}

	public static List<String> validarSociedad(SociedadTB sociedad) {
		List<String> errores = new ArrayList<>();
		if (sociedad.getCliente() == null) {
			errores.add(ConstantesValidaciones.CLIENTE_SOCIEDAD + ConstantesValidaciones.VALOR_VACIO);
		} else {
			if (sociedad.getCliente().getId() == 0l) {
				errores.add(ConstantesValidaciones.CLIENTE_SOCIEDAD + ConstantesValidaciones.VALOR_VACIO);
			}
		}
		if (sociedad.getServidor() == null) {
			errores.add(ConstantesValidaciones.SERVIDOR_SOCIEDAD + ConstantesValidaciones.VALOR_VACIO);
		} else {
			if (sociedad.getServidor().getId() == 0l) {
				errores.add(ConstantesValidaciones.SERVIDOR_SOCIEDAD + ConstantesValidaciones.VALOR_VACIO);
			}
		}
		if (StringUtils.isBlank(sociedad.getNombre())) {
			errores.add(ConstantesValidaciones.NOMBRE_SOCIEDAD + ConstantesValidaciones.VALOR_VACIO);
		}
		if (StringUtils.isBlank(sociedad.getNombre10())) {
			errores.add(ConstantesValidaciones.NOMBRE10_SOCIEDAD + ConstantesValidaciones.VALOR_VACIO);
		}
		if (StringUtils.isBlank(sociedad.getTax())) {
			errores.add(ConstantesValidaciones.TAX_SOCIEDAD + ConstantesValidaciones.VALOR_VACIO);
		}
		return errores;
	}

	public static List<String> validarPrestamo(RequestEditarPrestamoDTO request) {
		List<String> errores = new ArrayList<>();
		if (request.getEsCrear() == null) {
			errores.add(ConstantesValidaciones.CREAR_PRESTAMO + ConstantesValidaciones.VALOR_VACIO);
		}
		if (request.getIdUd() == null || request.getIdUd() == 0l) {
			errores.add(ConstantesValidaciones.IDUD_PRESTAMO + ConstantesValidaciones.VALOR_VACIO);
		}
		if (StringUtils.isBlank(request.getResponsable())) {
			errores.add(ConstantesValidaciones.RESPONSABLE_PRESTAMO + ConstantesValidaciones.VALOR_VACIO);
		}
		if (StringUtils.isBlank(request.getUsuarioCreacion())) {
			errores.add(ConstantesValidaciones.USUARIO_PRESTAMO + ConstantesValidaciones.VALOR_VACIO);
		}
		return errores;
	}

	public static List<String> validarMasivo(RequestCrearMasivoDTO request) {
		List<String> errores = new ArrayList<>();
		if (request.getTipoMasivo() == null || request.getMasivoDTO() == null) {
			errores.add(ConstantesValidaciones.TIPO_MASIVO + ConstantesValidaciones.VALOR_VACIO);
		} else {
			if (request.getTipoMasivo().intValue() == 1) {
				if (StringUtils.isBlank(request.getMasivoDTO().getNombre1())) {
					errores.add(ConstantesValidaciones.NOMBRE_AREA + ConstantesValidaciones.VALOR_VACIO);
				}
				if (StringUtils.isBlank(request.getMasivoDTO().getNombre2())) {
					errores.add(ConstantesValidaciones.NOMBRE10_AREA + ConstantesValidaciones.VALOR_VACIO);
				}
			} else if (request.getTipoMasivo().intValue() == 2) {
				if (StringUtils.isBlank(request.getMasivoDTO().getNombre1())) {
					errores.add(ConstantesValidaciones.NOMBRE_CLIENTE + ConstantesValidaciones.VALOR_VACIO);
				}
				if (StringUtils.isBlank(request.getMasivoDTO().getNombre2())) {
					errores.add(ConstantesValidaciones.TAXID_CLIENTE + ConstantesValidaciones.VALOR_VACIO);
				}
			} else if (request.getTipoMasivo().intValue() == 3) {
				if (StringUtils.isBlank(request.getMasivoDTO().getNombre1())) {
					errores.add(ConstantesValidaciones.NOMBRE_CONTENEDOR + ConstantesValidaciones.VALOR_VACIO);
				}
			} else if (request.getTipoMasivo().intValue() == 4) {
				if (StringUtils.isBlank(request.getMasivoDTO().getNombre1())) {
					errores.add(ConstantesValidaciones.NOMBRE_TIPOUD + ConstantesValidaciones.VALOR_VACIO);
				}
			} else if (request.getTipoMasivo().intValue() == 5) {
				if (StringUtils.isBlank(request.getMasivoDTO().getNombre1())) {
					errores.add(ConstantesValidaciones.NOMBRE_SEDE + ConstantesValidaciones.VALOR_VACIO);
				}
				if (StringUtils.isBlank(request.getMasivoDTO().getNombre2())) {
					errores.add(ConstantesValidaciones.NOMBRE10_SEDE + ConstantesValidaciones.VALOR_VACIO);
				}
				if (StringUtils.isBlank(request.getMasivoDTO().getNombre3())) {
					errores.add(ConstantesValidaciones.DIRECCION_SEDE + ConstantesValidaciones.VALOR_VACIO);
				}
			}
		}

		return errores;
	}

	public static List<String> validarUnidadDocumental(UnidadDocumentalTB unidadDocumental) {
		List<String> errores = new ArrayList<>();

		if (StringUtils.isBlank(unidadDocumental.getCodigo())) {
			errores.add(ConstantesValidaciones.CODIGO_UNIDAD_DOCUMENTAL + ConstantesValidaciones.VALOR_VACIO);
		}
		if (StringUtils.isBlank(unidadDocumental.getNombre())) {
			errores.add(ConstantesValidaciones.NOMBRE_UNIDAD_DOCUMENTAL + ConstantesValidaciones.VALOR_VACIO);
		}
		if (StringUtils.isBlank(unidadDocumental.getCodigoBarra())) {
			errores.add(ConstantesValidaciones.CODIGO_BARRAS_UNIDAD_DOCUMENTAL + ConstantesValidaciones.VALOR_VACIO);
		}
		if (unidadDocumental.getFechaRecibe() == null) {
			errores.add(ConstantesValidaciones.FECHA_RECIBE_UNIDAD_DOCUMENTAL + ConstantesValidaciones.VALOR_VACIO);
		}
		if (unidadDocumental.getFechaIni() == null) {
			errores.add(ConstantesValidaciones.FECHA_INICIO_UNIDAD_DOCUMENTAL + ConstantesValidaciones.VALOR_VACIO);
		}
		if (unidadDocumental.getFechaFin() == null) {
			errores.add(ConstantesValidaciones.FECHA_FIN_UNIDAD_DOCUMENTAL + ConstantesValidaciones.VALOR_VACIO);
		}
		if (unidadDocumental.getTipoDocumental() == null) {
			errores.add(ConstantesValidaciones.TIPO_DOCUMENTAL_UNIDAD_DOCUMENTAL
					+ ConstantesValidaciones.SIN_TIPO_DOCUMENTAL_UNIDAD_DOCUMENTAL);
		} else {
			if (unidadDocumental.getTipoDocumental().getId() == 0) {
				errores.add(ConstantesValidaciones.TIPO_DOCUMENTAL_UNIDAD_DOCUMENTAL
						+ ConstantesValidaciones.SIN_TIPO_DOCUMENTAL_UNIDAD_DOCUMENTAL);
			}
		}
		if (unidadDocumental.getContenedor() == null) {
			errores.add(ConstantesValidaciones.CONTENEDOR_UNIDAD_DOCUMENTAL
					+ ConstantesValidaciones.SIN_CONTENEDOR_UNIDAD_DOCUMENTAL);
		} else {
			if (unidadDocumental.getContenedor().getId() == 0) {
				errores.add(ConstantesValidaciones.CONTENEDOR_UNIDAD_DOCUMENTAL
						+ ConstantesValidaciones.SIN_CONTENEDOR_UNIDAD_DOCUMENTAL);
			}
		}
		if (unidadDocumental.getProyecto() == null) {
			errores.add(ConstantesValidaciones.PROYECTO_UNIDAD_DOCUMENTAL
					+ ConstantesValidaciones.SIN_PROYECTO_UNIDAD_DOCUMENTAL);
		} else {
			if (unidadDocumental.getProyecto().getId() == 0) {
				errores.add(ConstantesValidaciones.PROYECTO_UNIDAD_DOCUMENTAL
						+ ConstantesValidaciones.SIN_PROYECTO_UNIDAD_DOCUMENTAL);
			}
		}
		if (unidadDocumental.getSociedadArea().getSociedad() == null) {
			errores.add(ConstantesValidaciones.SOCIEDAD_UNIDAD_DOCUMENTAL
					+ ConstantesValidaciones.SIN_SOCIEDAD_UNIDAD_DOCUMENTAL);
		} else {
			if (unidadDocumental.getSociedadArea().getSociedad().getId() == 0) {
				errores.add(ConstantesValidaciones.SOCIEDAD_UNIDAD_DOCUMENTAL
						+ ConstantesValidaciones.SIN_SOCIEDAD_UNIDAD_DOCUMENTAL);
			}
		}
		if (unidadDocumental.getSociedadArea().getArea() == null) {
			errores.add(
					ConstantesValidaciones.AREA_UNIDAD_DOCUMENTAL + ConstantesValidaciones.SIN_AREA_UNIDAD_DOCUMENTAL);
		} else {
			if (unidadDocumental.getSociedadArea().getArea().getId() == 0) {
				errores.add(ConstantesValidaciones.AREA_UNIDAD_DOCUMENTAL
						+ ConstantesValidaciones.SIN_AREA_UNIDAD_DOCUMENTAL);
			}
		}
		return errores;
	}

	public static List<String> validarPerfilAutor(PerfilTB perfilTB) {
		List<String> errores = new ArrayList<>();

		if (StringUtils.isBlank(perfilTB.getCodigo())) {
			errores.add(ConstantesValidaciones.CODIGO_PERFIL + ConstantesValidaciones.VALOR_VACIO);
		}
		if (StringUtils.isBlank(perfilTB.getDescripcion())) {
			errores.add(ConstantesValidaciones.DESCRIPCION_PERFIL + ConstantesValidaciones.VALOR_VACIO);
		}
		return errores;
	}

	public static List<String> validarCaja(CajaTB cajaTB) {
		List<String> errores = new ArrayList<>();

		if (StringUtils.isBlank(cajaTB.getCodigoAlterno())) {
			errores.add(ConstantesValidaciones.CODIGO_CAJA + ConstantesValidaciones.VALOR_VACIO);
		}
		if (StringUtils.isBlank(cajaTB.getCodigoBarras())) {
			errores.add(ConstantesValidaciones.CODIGO_BARRAS_CAJA + ConstantesValidaciones.VALOR_VACIO);
		}
		if (cajaTB.getEntrepano() == null) {
			errores.add(ConstantesValidaciones.SIN_ENTREPANO_CAJA + ConstantesValidaciones.NO_TIENE_ENTREPANO);
		} else {
			if (cajaTB.getEntrepano().getId() == 0) {
				errores.add(ConstantesValidaciones.SIN_ENTREPANO_CAJA + ConstantesValidaciones.NO_TIENE_ENTREPANO);
			}
		}
		if (cajaTB.getCliente() == null) {
			errores.add(ConstantesValidaciones.SIN_SOCIEDAD_CAJA + ConstantesValidaciones.NO_TIENE_SOCIEDAD);
		} else {
			if (cajaTB.getCliente().getId() == 0) {
				errores.add(ConstantesValidaciones.SIN_SOCIEDAD_CAJA + ConstantesValidaciones.NO_TIENE_SOCIEDAD);
			}
		}
		return errores;
	}

	public static List<String> validarRol(RolTB rolTB) {
		List<String> errores = new ArrayList<>();

		if (StringUtils.isBlank(rolTB.getCodigo())) {
			errores.add(ConstantesValidaciones.CODIGO_ROL + ConstantesValidaciones.VALOR_VACIO);
		}
		if (StringUtils.isBlank(rolTB.getDescripcion())) {
			errores.add(ConstantesValidaciones.DESCRIPCION_PERFIL + ConstantesValidaciones.VALOR_VACIO);
		}
		return errores;
	}

	public static List<String> validarBodega(BodegaTB bodegaTB) {
		List<String> errores = new ArrayList<>();

		if (StringUtils.isBlank(bodegaTB.getCodigo())) {
			errores.add(ConstantesValidaciones.CODIGO_BODEGA + ConstantesValidaciones.VALOR_VACIO);
		}
		if (StringUtils.isBlank(bodegaTB.getNombre())) {
			errores.add(ConstantesValidaciones.NOMBRE_BODEGA + ConstantesValidaciones.VALOR_VACIO);
		}
		if (StringUtils.isBlank(bodegaTB.getNombre10())) {
			errores.add(ConstantesValidaciones.NOMBRE_10_BODEGA + ConstantesValidaciones.VALOR_VACIO);
		}
		if (StringUtils.isBlank(bodegaTB.getOwnerName())) {
			errores.add(ConstantesValidaciones.OWNER_NOMBRE_BODEGA + ConstantesValidaciones.VALOR_VACIO);
		}
		if (bodegaTB.getSede() == null) {
			errores.add(ConstantesValidaciones.SIN_SEDE_BODEGA + ConstantesValidaciones.NO_TIENE_SEDE);
		} else {
			if (bodegaTB.getSede().getId() == 0) {
				errores.add(ConstantesValidaciones.SIN_SEDE_BODEGA + ConstantesValidaciones.NO_TIENE_SEDE);
			}
		}
		return errores;
	}

	public static List<String> validarUsuario(UsuarioTB usuarioTB) {
		List<String> errores = new ArrayList<>();

		if (StringUtils.isBlank(usuarioTB.getEmail())) {
			errores.add(ConstantesValidaciones.NICK_USUARIO + ConstantesValidaciones.VALOR_VACIO);
		}
		return errores;
	}

	public static List<String> validarArchivo(RequestAgregarArchivosDTO archivoDto) {
		List<String> errores = new ArrayList<>();

		if (archivoDto != null) {
			if (archivoDto.getIdUnidadDocumental() == 0) {
				errores.add(ConstantesValidaciones.ARCHIVO_CODIGO_UNIDAD_DOCUMENTAL
						+ ConstantesValidaciones.VALOR_INCORRECTO);
			} else {
				if (archivoDto.getListaArchivosPorSubir() != null && !archivoDto.getListaArchivosPorSubir().isEmpty()) {
					int constante = 1;
					for (ArchivoDTO archivo : archivoDto.getListaArchivosPorSubir()) {
						if (archivo.getArchivo() == null) {
							errores.add(ConstantesValidaciones.ARCHIVO + " " + constante
									+ ConstantesValidaciones.VALOR_INCORRECTO);
						}
						if (StringUtils.isBlank(archivo.getNombreArchivo())) {
							errores.add(ConstantesValidaciones.NOMBRE_ARCHIVO + " " + constante
									+ ConstantesValidaciones.VALOR_VACIO);
						}
						constante++;
					}

				} else {
					errores.add(ConstantesValidaciones.LISTA_ARCHIVO_VACIA);
				}
			}
		} else {
			errores.add(ConstantesValidaciones.VALOR_NULL_OBJETO);
		}

		return errores;
	}

	public static List<String> validarArchivoBorrar(RequestAgregarArchivosDTO archivoDto) {
		List<String> errores = new ArrayList<>();

		if (archivoDto != null) {
			if (archivoDto.getIdUnidadDocumental() == 0) {
				errores.add(ConstantesValidaciones.ARCHIVO_CODIGO_UNIDAD_DOCUMENTAL
						+ ConstantesValidaciones.VALOR_INCORRECTO);
			} else {
				if (archivoDto.getListaArchivosPorSubir() != null && !archivoDto.getListaArchivosPorSubir().isEmpty()) {
					int constante = 1;
					for (ArchivoDTO archivo : archivoDto.getListaArchivosPorSubir()) {
						if (StringUtils.isBlank(archivo.getNombreArchivo())) {
							errores.add(ConstantesValidaciones.NOMBRE_ARCHIVO + " " + constante
									+ ConstantesValidaciones.VALOR_VACIO);
						}
						constante++;
					}

				} else {
					errores.add(ConstantesValidaciones.LISTA_ARCHIVO_VACIA);
				}
			}
		} else {
			errores.add(ConstantesValidaciones.VALOR_NULL_OBJETO);
		}

		return errores;
	}

	public static List<String> validarMail(RequestSendEMailDTO mailDto) {
		List<String> errores = new ArrayList<>();

		if (mailDto != null) {
			if (StringUtils.isBlank(mailDto.getAsunto())) {
				errores.add(ConstantesValidaciones.ASUNTO_MAIL + ConstantesValidaciones.VALOR_VACIO);
			}
			if (StringUtils.isBlank(mailDto.getDesde())) {
				errores.add(ConstantesValidaciones.REMITE_MAIL + ConstantesValidaciones.VALOR_VACIO);
			}
			if (mailDto.getPara() == null || mailDto.getPara().isEmpty()) {
				errores.add(ConstantesValidaciones.DESTINATARIOS_MAIL + ConstantesValidaciones.VALOR_VACIO);
			} else {
				for (String correoDestino : mailDto.getPara()) {
					if (!Util.esCorreoValido(correoDestino)) {
						errores.add(
								ConstantesValidaciones.CORREO_DESTINO_MAIL + ConstantesValidaciones.VALOR_INCORRECTO);
					}
				}
			}
		} else {
			errores.add(ConstantesValidaciones.VALOR_NULL_OBJETO);
		}

		return errores;
	}

	public static List<String> validarCrearBodega(CrearBodegaDTO request) {
		List<String> errores = new ArrayList<>();
		if (request.sedeId == 0L) {
			errores.add(ConstantesValidaciones.SEDE_ID + ConstantesValidaciones.VALOR_VACIO);
		}
		if (StringUtils.isBlank(request.nombreBodega)) {
			errores.add(ConstantesValidaciones.NOMBRE_BODEGA + ConstantesValidaciones.VALOR_VACIO);
		}
		if (StringUtils.isBlank(request.nombre10Bodega)) {
			errores.add(ConstantesValidaciones.NOMBRE_10_BODEGA + ConstantesValidaciones.VALOR_VACIO);
		}
		if (StringUtils.isBlank(request.usuarioCreacion)) {
			errores.add(ConstantesValidaciones.USU_CREACION + ConstantesValidaciones.VALOR_VACIO);
		}
		if (StringUtils.isBlank(request.codigoBodega)) {
			errores.add(ConstantesValidaciones.CODIGO_BODEGA + ConstantesValidaciones.VALOR_VACIO);
		}
		if (StringUtils.isBlank(request.ownerNameBodega)) {
			errores.add(ConstantesValidaciones.OWNER_NOMBRE_BODEGA + ConstantesValidaciones.VALOR_VACIO);
		}
		if (request.cantidadBloques == 0) {
			errores.add(ConstantesValidaciones.CANTIDAD_BLOQUES + ConstantesValidaciones.VALOR_VACIO);
		}
		if (request.cantidadCuerposXBloque == 0) {
			errores.add(ConstantesValidaciones.CUERPO_X_BLOQUE + ConstantesValidaciones.VALOR_VACIO);
		}
		if (request.cantidadEstantesXCuerpo == 0) {
			errores.add(ConstantesValidaciones.ESTANTE_X_CUERPO + ConstantesValidaciones.VALOR_VACIO);
		}
		if (request.cantidadEntrepanoXEstante == 0) {
			errores.add(ConstantesValidaciones.ENTREPANO_ESTANTE + ConstantesValidaciones.VALOR_VACIO);
		}

		return errores;
	}

	public static String generarToken(String usuario) {
		char[] SYM_USUARIO = usuario.toCharArray();
		char[] BUF_USUARIO = new char[ConstantesValidaciones.TAMANO_TOKEN];
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < ConstantesValidaciones.BUFFER.length; i++) {
			ConstantesValidaciones.BUFFER[i] = ConstantesValidaciones.SIMBOLOS[random
					.nextInt(ConstantesValidaciones.SIMBOLOS.length)];
		}
		for (int i = 0; i < BUF_USUARIO.length; i++) {
			BUF_USUARIO[i] = SYM_USUARIO[random.nextInt(SYM_USUARIO.length)];
		}
		String result = new String(ConstantesValidaciones.BUFFER) + new String(BUF_USUARIO);

		return result.substring(5, 15);
	}

	public static String encriptarPassword(String password) {
		String salt = PasswordUtil.getSalt(ConstantesValidaciones.SALT_ENCRIPTAR_CLAVE, password);
		return PasswordUtil.generateSecurePassword(password, salt);
	}

}