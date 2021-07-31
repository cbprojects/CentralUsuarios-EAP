package com.project.cafe.CentralUsuarios.util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import org.apache.commons.lang3.StringUtils;

import com.project.cafe.CentralUsuarios.dto.ArchivoDTO;
import com.project.cafe.CentralUsuarios.dto.RequestSendEMailDTO;
import com.project.cafe.CentralUsuarios.model.CajaTB;
import com.project.cafe.CentralUsuarios.model.PerfilTB;
import com.project.cafe.CentralUsuarios.model.RolTB;
import com.project.cafe.CentralUsuarios.model.UnidadDocumentalTB;
import com.project.cafe.CentralUsuarios.model.UsuarioTB;

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
			}
		} else {
			errores.add(ConstantesValidaciones.TABLA_NO_ESTABLECIDA_VALIDACIONES);
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
		if (unidadDocumental.getFechaRecibe()==null) {
			errores.add(ConstantesValidaciones.FECHA_RECIBE_UNIDAD_DOCUMENTAL + ConstantesValidaciones.VALOR_VACIO);
		}
		if(unidadDocumental.getTipoDocumental()==null) {
			errores.add(ConstantesValidaciones.TIPO_DOCUMENTAL_UNIDAD_DOCUMENTAL 
					+ ConstantesValidaciones.SIN_TIPO_DOCUMENTAL_UNIDAD_DOCUMENTAL);
		}else {
			if(unidadDocumental.getTipoDocumental().getId()==0) {
				errores.add(ConstantesValidaciones.TIPO_DOCUMENTAL_UNIDAD_DOCUMENTAL 
						+ ConstantesValidaciones.SIN_TIPO_DOCUMENTAL_UNIDAD_DOCUMENTAL);
			}
		}
		if(unidadDocumental.getContenedor()==null) {
			errores.add(ConstantesValidaciones.CONTENEDOR_UNIDAD_DOCUMENTAL 
					+ ConstantesValidaciones.SIN_CONTENEDOR_UNIDAD_DOCUMENTAL);
		}else {
			if(unidadDocumental.getContenedor().getId()==0) {
				errores.add(ConstantesValidaciones.CONTENEDOR_UNIDAD_DOCUMENTAL 
						+ ConstantesValidaciones.SIN_CONTENEDOR_UNIDAD_DOCUMENTAL);
			}
		}
		if(unidadDocumental.getSociedadArea().getSociedad()==null) {
			errores.add(ConstantesValidaciones.SOCIEDAD_UNIDAD_DOCUMENTAL 
					+ ConstantesValidaciones.SIN_SOCIEDAD_UNIDAD_DOCUMENTAL);
		}else {
			if(unidadDocumental.getSociedadArea().getSociedad().getId()==0) {
				errores.add(ConstantesValidaciones.SOCIEDAD_UNIDAD_DOCUMENTAL 
						+ ConstantesValidaciones.SIN_SOCIEDAD_UNIDAD_DOCUMENTAL);
			}
		}
		if(unidadDocumental.getSociedadArea().getArea()==null) {
			errores.add(ConstantesValidaciones.AREA_UNIDAD_DOCUMENTAL 
					+ ConstantesValidaciones.SIN_AREA_UNIDAD_DOCUMENTAL);
		}else {
			if(unidadDocumental.getSociedadArea().getArea().getId()==0) {
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
		if(cajaTB.getEntrepano()==null) {
			errores.add(ConstantesValidaciones.SIN_ENTREPANO_CAJA + ConstantesValidaciones.NO_TIENE_ENTREPANO);
		}else {
			if(cajaTB.getEntrepano().getId()==0) {
				errores.add(ConstantesValidaciones.SIN_ENTREPANO_CAJA + ConstantesValidaciones.NO_TIENE_ENTREPANO);
			}
		}
		if(cajaTB.getSociedad()==null) {
			errores.add(ConstantesValidaciones.SIN_SOCIEDAD_CAJA+ ConstantesValidaciones.NO_TIENE_SOCIEDAD);
		}else {
			if(cajaTB.getSociedad().getId()==0) {
				errores.add(ConstantesValidaciones.SIN_SOCIEDAD_CAJA+ ConstantesValidaciones.NO_TIENE_SOCIEDAD);
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

	public static List<String> validarUsuario(UsuarioTB usuarioTB) {
		List<String> errores = new ArrayList<>();

		if (StringUtils.isBlank(usuarioTB.getEmail())) {
			errores.add(ConstantesValidaciones.NICK_USUARIO + ConstantesValidaciones.VALOR_VACIO);
		}
		return errores;
	}

	public static List<String> validarArchivo(ArchivoDTO archivoDto) {
		List<String> errores = new ArrayList<>();

		if (archivoDto != null) {
			if (archivoDto.getArchivo() == null) {
				errores.add(ConstantesValidaciones.ARCHIVO + ConstantesValidaciones.VALOR_INCORRECTO);
			}
			if (StringUtils.isBlank(archivoDto.getNombreArchivo())) {
				errores.add(ConstantesValidaciones.NOMBRE_ARCHIVO + ConstantesValidaciones.VALOR_VACIO);
			}
			if (StringUtils.isBlank(archivoDto.getRutaArchivo())) {
				errores.add(ConstantesValidaciones.RUTA_ARCHIVO + ConstantesValidaciones.VALOR_VACIO);
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