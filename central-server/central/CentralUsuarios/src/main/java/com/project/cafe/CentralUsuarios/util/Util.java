package com.project.cafe.CentralUsuarios.util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import org.apache.commons.lang3.StringUtils;

import com.project.cafe.CentralUsuarios.dto.ArchivoDTO;
import com.project.cafe.CentralUsuarios.dto.RequestSendEMailDTO;
import com.project.cafe.CentralUsuarios.model.PerfilTB;
import com.project.cafe.CentralUsuarios.model.RolTB;
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
			}
		} else {
			errores.add(ConstantesValidaciones.TABLA_NO_ESTABLECIDA_VALIDACIONES);
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
		if (StringUtils.isBlank(usuarioTB.getContrasena())) {
			errores.add(ConstantesValidaciones.CONTRASENA_USUARIO + ConstantesValidaciones.VALOR_VACIO);
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