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
	private static final String SEPARADOR_ARCHIVOS = "#--#";
	private UnidadDocumentalTB unidad = null;
	
	@Transactional
	@Override
	public List<String> subirImagen(RequestAgregarArchivosDTO archivo) {
		boolean sftpConectado = false;
		this.unidad = new UnidadDocumentalTB();
		List<String> lista = new ArrayList<>();
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

					rutaExiste = false;
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
			llenarNombreArchivos(archivo);
			lista = realizarLista(unidad);
		} catch (Exception ex) {
			SFTPServicio.cerrarConexion();
		}
		return lista;
	}
	
	private List<String> realizarLista(UnidadDocumentalTB unidad) {
		List<String> lista = new ArrayList<>();
		String[] listaSeparada = unidad.getNombreArchivos().split(SEPARADOR_ARCHIVOS);
		for (String dato : listaSeparada) {
			lista.add(dato);
		}
		return lista;
	}

	@Transactional
	private void llenarNombreArchivos(RequestAgregarArchivosDTO archivo) {
		String datos="";
		for (int i = 0; i < archivo.getListaArchivosPorSubir().size(); i++) {
			if(i+1==archivo.getListaArchivosPorSubir().size()) {
				datos=datos+archivo.getListaArchivosPorSubir().get(i).getNombreArchivo();
			}else {
				datos=datos+archivo.getListaArchivosPorSubir().get(i).getNombreArchivo()+SEPARADOR_ARCHIVOS;
			}
		}
		if (StringUtils.isBlank(this.unidad.getNombreArchivos())) {
			this.unidad.setNombreArchivos(datos);
			
		}else {
			this.unidad.setNombreArchivos(this.unidad.getNombreArchivos()+SEPARADOR_ARCHIVOS+datos);
		}
		this.unidad = unidadDocuementalDAO.modificarUnidadDocumental(this.unidad);
	}

	@Transactional
	@Override
	public List<String> borrarImagen(RequestAgregarArchivosDTO archivo) {
		boolean sftpConectado = false;
		this.unidad = new UnidadDocumentalTB();
		List<String> lista = new ArrayList<>();
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
			llenarNombreArchivosEliminar(archivo);
			lista = realizarLista(unidad);
		} catch (Exception ex) {
			SFTPServicio.cerrarConexion();
		}
		return lista;
	}
	
	@Transactional
	private void llenarNombreArchivosEliminar(RequestAgregarArchivosDTO archivo) {
		List<String> lista = new ArrayList<>();
		String[] listaSeparada = this.unidad.getNombreArchivos().split(SEPARADOR_ARCHIVOS);
		for (String dato : listaSeparada) {
			lista.add(dato);
		}
		lista.remove(archivo.listaArchivosPorSubir.get(0).getNombreArchivo());
		String datos="";
		if(!lista.isEmpty()) {
			for (int i = 0; i < lista.size(); i++) {
				if(i+1==lista.size()) {
					datos=datos+lista.get(i);
				}else {
					datos=datos+lista.get(i)+SEPARADOR_ARCHIVOS;
				}
			}
		}
		this.unidad.setNombreArchivos(datos);
		this.unidad = unidadDocuementalDAO.modificarUnidadDocumental(this.unidad);
	}

	@Transactional
	private String rutaSftpRetornada(long idUnidadDocumental) {
		this.unidad = unidadDocuementalDAO.buscarUnidadDocumentalPorId(idUnidadDocumental);
		ServidorTB servidor = this.unidad.getSociedadArea().getSociedad().getServidor();
		PUERTO_SFTP = servidor.getPuerto();
		SERVIDOR_SFTP = servidor.getIp();
		USUARIO_SFTP = servidor.getUsuario();
		CLAVE_SFTP = servidor.getClave();
		if (StringUtils.isBlank(this.unidad.getRutaArchivo())) {
			String ruta = SEPARADOR + "Archivos" + SEPARADOR + this.unidad.getCaja().getCliente().getId() 
					+ SEPARADOR + this.unidad.getId() + SEPARADOR;
			this.unidad.setRutaArchivo(ruta);
			this.unidad = unidadDocuementalDAO.modificarUnidadDocumental(this.unidad);
		}
		return this.unidad.getRutaArchivo();
	}

	@Transactional
	@Override
	public List<ArchivoDTO> obtenerArchivos(RequestAgregarArchivosDTO archivo) {
		List<ArchivoDTO> listaArchivosRespuesta = new ArrayList<>();
		boolean sftpConectado = false;
		this.unidad = new UnidadDocumentalTB();
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
