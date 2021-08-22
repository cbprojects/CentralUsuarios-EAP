package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.ArchivoDTO;
import com.project.cafe.CentralUsuarios.dto.RequestAgregarArchivosDTO;

public interface IArchivoService {

	/*
	 * Método para subir imagen al servidor sftp
	 */
	public void subirImagen(RequestAgregarArchivosDTO archivoDto);

	/*
	 * Método para obtener archivos del servidor sftp
	 */
	public List<ArchivoDTO> obtenerArchivos(RequestAgregarArchivosDTO archivo);

	/*
	 * Método para borrar imagen al servidor sftp
	 */
	public void borrarImagen(RequestAgregarArchivosDTO archivoDto);

}
