package com.project.cafe.CentralUsuarios.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.cafe.CentralUsuarios.dao.IUnidadDocumentalDao;
import com.project.cafe.CentralUsuarios.dto.ArchivoDTO;
import com.project.cafe.CentralUsuarios.dto.RequestAgregarArchivosDTO;
import com.project.cafe.CentralUsuarios.model.ServidorTB;
import com.project.cafe.CentralUsuarios.model.UnidadDocumentalTB;
import com.project.cafe.CentralUsuarios.service.IArchivoService;
import com.project.cafe.CentralUsuarios.service.ISFTPServicio;

@Service
public class ArchivoServiceImpl implements IArchivoService {

	@Autowired
	private ISFTPServicio SFTPServicio;

	@Autowired
	private IUnidadDocumentalDao unidadDocuementalDAO;

	private String PUERTO_SFTP;

	private String SERVIDOR_SFTP;

	private String USUARIO_SFTP;

	private String CLAVE_SFTP;

	private static final String SEPARADOR = "/";

	@Transactional
	@Override
	public void subirImagen(RequestAgregarArchivosDTO archivo) {
		boolean sftpConectado = false;

		try {
			String rutaSFTP = rutaSftpRetornada(archivo.getIdUnidadDocumental());

			// Abrir conexion a servidor sftp
			sftpConectado = SFTPServicio.conectarServidor(SERVIDOR_SFTP, Integer.parseInt(PUERTO_SFTP), USUARIO_SFTP,
					CLAVE_SFTP);

			// validar conexion a servidor
			if (sftpConectado) {
				boolean rutaExiste = false;

				// validar que la ruta no este vacia
				if (!StringUtils.isBlank(rutaSFTP)) {
					// validar que la ruta exista en el servidor
					rutaExiste = SFTPServicio.esValidaRuta(rutaSFTP);
					if (!rutaExiste) {
						SFTPServicio.crearDirectorio(rutaSFTP);
					}

					for (ArchivoDTO archivoIterado : archivo.getListaArchivosPorSubir()) {
						String rutaSFTPFinal = rutaSFTP + archivoIterado.getNombreArchivo();

						// guardar archivos en el servidor que llegan en la lista
						if (archivoIterado.getArchivo() != null && archivoIterado.getArchivo().length > 0) {
							InputStream inputStreamArchivo = new ByteArrayInputStream(archivoIterado.getArchivo());
							SFTPServicio.guardarArchivoServidor(inputStreamArchivo, rutaSFTPFinal);

						}
					}
				}
			}

			// cerrar conexion con servidor SFTP
			SFTPServicio.cerrarConexion();
		} catch (Exception ex) {
			SFTPServicio.cerrarConexion();
		}

	}

	@Transactional
	@Override
	public void borrarImagen(RequestAgregarArchivosDTO archivo) {
		boolean sftpConectado = false;

		try {
			String rutaSFTP = rutaSftpRetornada(archivo.getIdUnidadDocumental());

			// Abrir conexion a servidor sftp
			sftpConectado = SFTPServicio.conectarServidor(SERVIDOR_SFTP, Integer.parseInt(PUERTO_SFTP), USUARIO_SFTP,
					CLAVE_SFTP);

			// validar conexion a servidor
			if (sftpConectado) {
				
				// validar que la ruta no este vacia
				if (!StringUtils.isBlank(rutaSFTP)) {

					for (ArchivoDTO archivoIterado : archivo.getListaArchivosPorSubir()) {
						String rutaSFTPFinal = rutaSFTP + archivoIterado.getNombreArchivo();
						SFTPServicio.borrarArchivoServidor(rutaSFTPFinal);

					}
				}
			}

			// cerrar conexion con servidor SFTP
			SFTPServicio.cerrarConexion();
		} catch (Exception ex) {
			SFTPServicio.cerrarConexion();
		}

	}

	@Transactional
	private String rutaSftpRetornada(long idUnidadDocumental) {
		UnidadDocumentalTB unidad = unidadDocuementalDAO.buscarUnidadDocumentalPorId(idUnidadDocumental);
		ServidorTB servidor = unidad.getCaja().getSociedad().getServidor();
		PUERTO_SFTP = servidor.getPuerto();
		SERVIDOR_SFTP = servidor.getIp();
		USUARIO_SFTP = servidor.getUsuario();
		CLAVE_SFTP = servidor.getClave();
		if (StringUtils.isBlank(unidad.getRutaArchivo())) {
			String ruta = SEPARADOR + "Archivos" + SEPARADOR + unidad.getCaja().getSociedad().getId() 
					+ SEPARADOR + unidad.getId() + SEPARADOR;
			unidad.setRutaArchivo(ruta);
			unidad = unidadDocuementalDAO.modificarUnidadDocumental(unidad);
		}
		return unidad.getRutaArchivo();
	}

	@Transactional
	@Override
	public List<ArchivoDTO> obtenerArchivos(RequestAgregarArchivosDTO archivo) {
		List<ArchivoDTO> listaArchivosRespuesta = new ArrayList<>();
		boolean sftpConectado = false;

		try {
			String rutaSFTP = rutaSftpRetornada(archivo.getIdUnidadDocumental());
			// Abrir conexion a servidor sftp
			sftpConectado = SFTPServicio.conectarServidor(SERVIDOR_SFTP, Integer.parseInt(PUERTO_SFTP), USUARIO_SFTP,
					CLAVE_SFTP);

			// validar conexion a servidor
			if (sftpConectado) {
				boolean rutaExiste = false;

				// validar que la ruta no este vacia
				if (!StringUtils.isBlank(rutaSFTP)) {
					// validar que la ruta exista en el servidor
					rutaExiste = SFTPServicio.esValidaRuta(rutaSFTP);
					if (rutaExiste) {
						if (archivo.getListaArchivosPorSubir()==null || archivo.getListaArchivosPorSubir().isEmpty()) {
							listaArchivosRespuesta = SFTPServicio.obtenerArchivos(rutaSFTP,
									null);
						} else {
							listaArchivosRespuesta = SFTPServicio.obtenerArchivos(rutaSFTP,
									archivo.getListaArchivosPorSubir().get(0).getNombreArchivo());
						}
					}
				}
			}

			// cerrar conexion con servidor SFTP
			SFTPServicio.cerrarConexion();
		} catch (Exception ex) {
			SFTPServicio.cerrarConexion();
		}

		return listaArchivosRespuesta;
	}

}
