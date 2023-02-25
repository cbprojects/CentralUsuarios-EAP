package com.project.cafe.CentralUsuarios.util;

import java.util.regex.Pattern;

public final class ConstantesValidaciones {

	// Expresiones regulares y cadenas
	public static final String EXPRESION_REGULAR_DE_TEXTO_INGRESADO = "[a-zA-Z0-9- äÄëËïÏöÖüÜáéíóúáéíóúÁÉÍÓÚÂÊÎÔÛâêîôûàèìòùÀÈÌÒÙñÑ//\\.]*";
	public static final String EXPRESION_REGULAR_DE_DIRECCIONES = "[a-zA-Z0-9- äÄëËïÏöÖüÜáéíóúáéíóúÁÉÍÓÚÂÊÎÔÛâêîôûàèìòùÀÈÌÒÙñÑ//\\.]*#[a-zA-Z0-9- äÄëËïÏöÖüÜáéíóúáéíóúÁÉÍÓÚÂÊÎÔÛâêîôûàèìòùÀÈÌÒÙñÑ//\\.]*";
	public static final String EXPRESION_REGULAR_DE_EMAILS = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	public static final Pattern EMAIL_PATTERN = Pattern
			.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	public static final String CARACTERES = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	// Rutas Útiles
	public static final String RUTA_JASPER_REPORTS = PropertiesUtil.getProperty("centralusuarios.ruta.jasper.reports");
	public static final String RUTA_SFTP_IMAGES = PropertiesUtil.getProperty("centralusuarios.ruta.sftp.archivos");
	public static final String RUTA_SFTP_IMAGES_USUARIO = PropertiesUtil
			.getProperty("centralusuarios.ruta.sftp.archivos.user");
	public static final String RUTA_SFTP_IMAGES_POST = PropertiesUtil
			.getProperty("centralusuarios.ruta.sftp.archivos.post");
	public static final String TEMPLATE_MAIL_ACTIVATE_USER = PropertiesUtil
			.getProperty("centralusuarios.template.mail.activateUser");
	public static final String TEMPLATE_MAIL_RECORDAR_CLAVE = PropertiesUtil
			.getProperty("centralusuarios.template.mail.recordarClave");
	public static final String TEMPLATE_MAIL_RESPONDER_COMENTARIO = PropertiesUtil
			.getProperty("centralusuarios.template.mail.comentario");
	public static final String TEMPLATE_MAIL_CONTACTO_BANDA = PropertiesUtil
			.getProperty("centralusuarios.template.mail.contacto");

	// Simbolos y constantes
	public static final String COMODIN_BD = "%";
	public static final String SEPARADOR_TAGS = ";";
	public static final String SEPARADOR_SLASH = "/";
	public static final String PHASE_CREATE = "C";
	public static final String PHASE_UPDATE = "U";
	public static final int TAMANO_TOKEN = 11;
	public static final int MAX_LENGTH_50 = 50;
	public static final int MAX_LENGTH_30 = 30;
	public static final int ITERATIONS = 10000;
	public static final int KEY_LENGTH = 256;
	public static final int SALT_ENCRIPTAR_CLAVE = 28;
	public static final char[] SIMBOLOS = CARACTERES.toCharArray();
	public static final char[] BUFFER = new char[TAMANO_TOKEN];
	public static final String CLAVE_AES = "B13EC3B0742D2308";

	// Mensajes
	public static final String ERROR_LOGIN_DATOS_INCORRECTOS_INACTIVOS = PropertiesUtil
			.getProperty("centralusuarios.msg.login.datos.incorrectos");
	public static final String ERROR_RESTAURAR_CLAVE = PropertiesUtil
			.getProperty("centralusuarios.msg.restaurar.clave");
	public static final String ERROR_LOGIN_DATOS_INSUFICIENTES = PropertiesUtil
			.getProperty("centralusuarios.msg.login.datos.insuficientes");
	public static final String LLAVE_ENCRIPTAR = PropertiesUtil.getProperty("centralusuarios.key.encrypt");
	public static final String VALOR_NULL_OBJETO = PropertiesUtil
			.getProperty("centralusuarios.msg.validate.valor.objeto");
	public static final String LISTA_ARCHIVO_VACIA = PropertiesUtil
			.getProperty("centralusuarios.msg.validate.valor.listaArchivoVacia");
	public static final String VALOR_VACIO = PropertiesUtil.getProperty("centralusuarios.msg.validate.valor.vacio");
	public static final String VALOR_INCORRECTO = PropertiesUtil
			.getProperty("centralusuarios.msg.validate.valor.incorrecto");
	public static final String CORREO_NO_VALIDO = PropertiesUtil
			.getProperty("centralusuarios.msg.validate.correoInvalido");
	public static final String TABLA_NO_ESTABLECIDA_VALIDACIONES = PropertiesUtil
			.getProperty("centralusuarios.msg.validate.tabla.no.establecida");
	public static final String SUPERA_LONGITUD = PropertiesUtil
			.getProperty("centralusuarios.msg.validate.valor.superaLongitud");
	public static final String MSG_USUARIO_REPETIDO = PropertiesUtil
			.getProperty("centralusuarios.msg.validate.usuarioRepetido");
	public static final String MSG_CORREO_REPETIDO = PropertiesUtil
			.getProperty("centralusuarios.msg.validate.correoRepetido");
	public static final String MSG_NO_POSTS = PropertiesUtil.getProperty("centralusuarios.msg.validate.no.posts");
	public static final String MSG_ENVIO_EMAIL_EXITOSO = PropertiesUtil
			.getProperty("centralusuarios.msg.email.exitoso");
	public static final String MSG_ENVIO_EMAIL_EXITOSO_CON_EXCEPCIONES = PropertiesUtil
			.getProperty("centralusuarios.msg.email.exitoso.con.excepciones");

	// Labels
	public static final String ARCHIVO = PropertiesUtil.getProperty("lbl.archivo.archivo");
	public static final String ARCHIVO_CODIGO_UNIDAD_DOCUMENTAL = PropertiesUtil
			.getProperty("lbl.archivo.unidadDocumental");
	public static final String NOMBRE_ARCHIVO = PropertiesUtil.getProperty("lbl.archivo.nombre");
	public static final String RUTA_ARCHIVO = PropertiesUtil.getProperty("lbl.archivo.ruta");

	public static final String USUARIO = PropertiesUtil.getProperty("lbl.usuario.usuario");
	public static final String NOMBRES_USUARIO = PropertiesUtil.getProperty("lbl.usuario.nombres");
	public static final String APELLIDOS_USUARIO = PropertiesUtil.getProperty("lbl.usuario.apellidos");
	public static final String RESENA_USUARIO = PropertiesUtil.getProperty("lbl.usuario.resena");
	public static final String CORREO_USUARIO = PropertiesUtil.getProperty("lbl.usuario.correo");
	public static final String FECHA_NACIMIENTO_USUARIO = PropertiesUtil.getProperty("lbl.usuario.fecha.nacimiento");
	public static final String CLAVE_USUARIO = PropertiesUtil.getProperty("lbl.usuario.clave");
	public static final String IMAGEN_USUARIO = PropertiesUtil.getProperty("lbl.usuario.imagen");

	public static final String COMENTARIO = PropertiesUtil.getProperty("lbl.comentario.comentario");
	public static final String USUARIO_AUTOR_COMENTARIO = PropertiesUtil.getProperty("lbl.comentario.autor");
	public static final String POST_COMENTARIO = PropertiesUtil.getProperty("lbl.comentario.post");

	public static final String TITULO_POST = PropertiesUtil.getProperty("lbl.post.titulo");
	public static final String SUBTITULO_POST = PropertiesUtil.getProperty("lbl.post.subtitulo");
	public static final String ARTICULO_POST = PropertiesUtil.getProperty("lbl.post.articulo");
	public static final String TAGS_POST = PropertiesUtil.getProperty("lbl.post.tags");
	public static final String IMAGEN_POST = PropertiesUtil.getProperty("lbl.post.imagen");
	public static final String CATEGORIA_POST = PropertiesUtil.getProperty("lbl.post.categoria");
	public static final String USUARIO_AUTOR_POST = PropertiesUtil.getProperty("lbl.post.usuario");
	public static final String GENERO_POST = PropertiesUtil.getProperty("lbl.post.genero");

	public static final String ASUNTO_MAIL = PropertiesUtil.getProperty("lbl.mail.asunto");
	public static final String DESTINATARIOS_MAIL = PropertiesUtil.getProperty("lbl.mail.destinatarios");
	public static final String CORREO_DESTINO_MAIL = PropertiesUtil.getProperty("lbl.mail.correo.destino");
	public static final String REMITE_MAIL = PropertiesUtil.getProperty("lbl.mail.correo.remitente");

	public static final String CALIFICACION_ME_GUSTA_ = PropertiesUtil.getProperty("lbl.like.calificacion");
	public static final String USUARIO_AUTOR_ME_GUSTA = PropertiesUtil.getProperty("lbl.like.autor");
	public static final String POST_ME_GUSTA = PropertiesUtil.getProperty("lbl.like.post");

	// Labels Perfil
	public static final String CODIGO_PERFIL = PropertiesUtil.getProperty("lbl.perfil.codigo");
	public static final String DESCRIPCION_PERFIL = PropertiesUtil.getProperty("lbl.perfil.descripcion");
	public static final String MSG_PERFIL_REPETIDO = PropertiesUtil
			.getProperty("centralusuarios.perfil.validate.perfilRepetido");
	public static final String MSG_PERFIL_CANTIDAD_REGISTROS = PropertiesUtil
			.getProperty("centralusuarios.perfil.validate.cantidadRegistros");

	// Labels Rol
	public static final String CODIGO_ROL = PropertiesUtil.getProperty("lbl.rol.codigo");
	public static final String DESCRIPCION_ROL = PropertiesUtil.getProperty("lbl.rol.descripcion");
	public static final String MSG_ROL_REPETIDO = PropertiesUtil
			.getProperty("centralusuarios.msg.validate.rolRepetido");

	// prestamo
	public static final String MSG_PRESTAMO_IDFALTANTE = PropertiesUtil.getProperty("lbl.prestamo.idFaltante");
	public static final String CREAR_PRESTAMO = PropertiesUtil.getProperty("lbl.prestamo.escrear");
	public static final String IDUD_PRESTAMO = PropertiesUtil.getProperty("lbl.prestamo.idud");
	public static final String RESPONSABLE_PRESTAMO = PropertiesUtil.getProperty("lbl.prestamo.responsable");
	public static final String USUARIO_PRESTAMO = PropertiesUtil.getProperty("lbl.prestamo.usuario");

	// sociedad
	public static final String CLIENTE_SOCIEDAD = PropertiesUtil.getProperty("lbl.sociedad.cliente");
	public static final String SERVIDOR_SOCIEDAD = PropertiesUtil.getProperty("lbl.sociedad.servidor");
	public static final String NOMBRE_SOCIEDAD = PropertiesUtil.getProperty("lbl.sociedad.nombre");
	public static final String NOMBRE10_SOCIEDAD = PropertiesUtil.getProperty("lbl.sociedad.nombre10");
	public static final String TAX_SOCIEDAD = PropertiesUtil.getProperty("lbl.sociedad.tax");
	public static final String SOCIEDAD_REPETIDO = PropertiesUtil.getProperty("lbl.sociedad.repetido");
	// sociedad area
	public static final String SOCIEDAD_SOCIEDAD_AREA = PropertiesUtil.getProperty("lbl.sociedad.area.sociedad");
	public static final String AREA_SOCIEDAD_AREA = PropertiesUtil.getProperty("lbl.sociedad.area.area");
	public static final String SOCIEDAD_AREA_REPETIDO = PropertiesUtil.getProperty("lbl.sociedad.area.repetido");

	// usuario sede
	public static final String USUARIO_USUARIO_SEDE = PropertiesUtil.getProperty("lbl.usuario.sede.usuario");
	public static final String SEDE_USUARIO_SEDE = PropertiesUtil.getProperty("lbl.usuario.sede.sede");
	public static final String USUARIO_SEDE_REPETIDO = PropertiesUtil.getProperty("lbl.usuario.sede.repetido");
	//tipo masivo
	public static final String TIPO_MASIVO = PropertiesUtil.getProperty("centralusuarios.msg.tipoMasivo");

	// Labels Area
	public static final String NOMBRE_AREA = PropertiesUtil.getProperty("lbl.area.nombre");
	public static final String NOMBRE10_AREA = PropertiesUtil.getProperty("lbl.area.nombre10");
	public static final String MSG_AREA_REPETIDO = PropertiesUtil.getProperty("lbl.area.repetido");

	// Labels Cliente
	public static final String NOMBRE_CLIENTE = PropertiesUtil.getProperty("lbl.cliente.nombre");
	public static final String TAXID_CLIENTE = PropertiesUtil.getProperty("lbl.cliente.taxid");
	public static final String MSG_CLIENTE_REPETIDO = PropertiesUtil.getProperty("lbl.cliente.repetido");

	// Labels Contenedor
	public static final String NOMBRE_CONTENEDOR = PropertiesUtil.getProperty("lbl.contenedor.nombre");
	public static final String MSG_CONTENEDOR_REPETIDO = PropertiesUtil.getProperty("lbl.contenedor.repetido");

	// Labels tipoUD
	public static final String NOMBRE_TIPOUD = PropertiesUtil.getProperty("lbl.tipoUD.nombre");
	public static final String MSG_TIPOUD_REPETIDO = PropertiesUtil.getProperty("lbl.tipoUD.repetido");

	// Labels usuario
	public static final String NICK_USUARIO = PropertiesUtil.getProperty("lbl.rol.codigo");
	public static final String CONTRASENA_USUARIO = PropertiesUtil.getProperty("lbl.rol.descripcion");

	// Labels sede
	public static final String NOMBRE_SEDE = PropertiesUtil.getProperty("lbl.sede.nombre");
	public static final String NOMBRE10_SEDE = PropertiesUtil.getProperty("lbl.sede.nombre10");
	public static final String DIRECCION_SEDE = PropertiesUtil.getProperty("lbl.sede.direccion");
	public static final String MSG_SEDE_REPETIDO = PropertiesUtil.getProperty("lbl.sede.repetido");

	// labels RolPerfil
	public static final String MSG_BUSQUEDA_ROLPERFIL_VACIO = PropertiesUtil
			.getProperty("centralusuarios.rolperfil.validate.busquedaRolPerfilVacio");

	// labels caja
	public static final String CODIGO_CAJA = PropertiesUtil.getProperty("lbl.caja.codigo");
	public static final String CODIGO_BARRAS_CAJA = PropertiesUtil.getProperty("lbl.caja.codigoBarras");
	public static final String SIN_ENTREPANO_CAJA = PropertiesUtil.getProperty("lbl.caja.entrepano");
	public static final String NO_TIENE_ENTREPANO = PropertiesUtil.getProperty("lbl.msg.validate.cajaSinEntrepano");
	public static final String SIN_SOCIEDAD_CAJA = PropertiesUtil.getProperty("lbl.caja.sociedad");
	public static final String NO_TIENE_SOCIEDAD = PropertiesUtil.getProperty("lbl.msg.validate.cajaSinSociedad");
	public static final String MSG_CAJA_REPETIDA = PropertiesUtil.getProperty("lbl.msg.validate.cajaRepetida");

	// labels unidad documental
	public static final String CODIGO_UNIDAD_DOCUMENTAL = PropertiesUtil.getProperty("lbl.unidad.documental.codigo");
	public static final String NOMBRE_UNIDAD_DOCUMENTAL = PropertiesUtil.getProperty("lbl.unidad.documental.nombre");
	public static final String CODIGO_BARRAS_UNIDAD_DOCUMENTAL = PropertiesUtil
			.getProperty("lbl.unidad.documental.codigoBarras");
	public static final String FECHA_RECIBE_UNIDAD_DOCUMENTAL = PropertiesUtil
			.getProperty("lbl.unidad.documental.fechaRecibe");
	public static final String SOCIEDAD_UNIDAD_DOCUMENTAL = PropertiesUtil
			.getProperty("lbl.unidad.documental.sociedad");
	public static final String SIN_SOCIEDAD_UNIDAD_DOCUMENTAL = PropertiesUtil
			.getProperty("lbl.msg.validate.unidadDocumentalSinSociedad");
	public static final String AREA_UNIDAD_DOCUMENTAL = PropertiesUtil.getProperty("lbl.unidad.documental.area");
	public static final String SIN_AREA_UNIDAD_DOCUMENTAL = PropertiesUtil
			.getProperty("lbl.msg.validate.unidadDocumentalSinArea");
	public static final String TIPO_DOCUMENTAL_UNIDAD_DOCUMENTAL = PropertiesUtil
			.getProperty("lbl.unidad.documental.tipoDocumental");
	public static final String SIN_TIPO_DOCUMENTAL_UNIDAD_DOCUMENTAL = PropertiesUtil
			.getProperty("lbl.msg.validate.unidadDocumentalSinTipoDocumental");
	public static final String CONTENEDOR_UNIDAD_DOCUMENTAL = PropertiesUtil
			.getProperty("lbl.unidad.documental.Contenedor");
	public static final String SIN_CONTENEDOR_UNIDAD_DOCUMENTAL = PropertiesUtil
			.getProperty("lbl.msg.validate.unidadDocumentalSinContenedor");
	public static final String MSG_UNIDAD_DOCUMENTAL_REPETIDA = PropertiesUtil
			.getProperty("lbl.msg.validate.unidadDocumentalRepetida");
}
