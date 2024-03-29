package com.project.cafe.CentralUsuarios.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.Predicate;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.project.cafe.CentralUsuarios.dto.ArchivoDTO;
import com.project.cafe.CentralUsuarios.dto.DirectorioDTO;
import com.project.cafe.CentralUsuarios.enums.ETipoDirectorio;
import com.project.cafe.CentralUsuarios.service.ISFTPServicio;

@Service
public class SFTPServicio implements ISFTPServicio {

	private JSch jsch;
	private Session session;
	private ChannelSftp channelSftp;
	private Properties config;
	private static final String SEPARADOR = "/";

	@Override
	public boolean conectarServidor(String servidor, int puerto, String usuario, String clave) throws JSchException {
		boolean resultado = false;
		try {
			config = new Properties();
			jsch = new JSch();
			session = jsch.getSession(usuario, servidor, puerto);
			if (!this.session.isConnected()) {
				session.setPassword(clave);
				config.put("StrictHostKeyChecking", "no");
				JSch.setConfig(config);
				session.connect();
				channelSftp = (ChannelSftp) session.openChannel("sftp");
				channelSftp.connect();
				if (channelSftp != null) {
					resultado = true;
				}
			}
		} catch (JSchException e) {
			if (session != null) {
				session.connect();
				session.disconnect();
			}
		}

		return resultado;
	}

	@Override
	public void cerrarConexion() {
		try {
			if (channelSftp != null) {
				channelSftp.connect();
				channelSftp.disconnect();
				channelSftp.exit();
			}
			if (session != null) {
				session.setPassword("");
				session.connect();
				session.disconnect();
			}
			channelSftp = null;
			session = null;
			jsch = null;
		} catch (JSchException e) {
		}
	}

	@Override
	public boolean guardarArchivoServidor(byte[] bytesFile, String rutaLocal, String rutaSFTP) {
		boolean resultado = false;
		try {
			if (channelSftp != null) {
				File file = new File(rutaLocal);
				OutputStream fos = new FileOutputStream(file);
				fos.write(bytesFile);
				// this.borrarArchivoServidor(rutaSFTP);
				channelSftp.put(rutaLocal, rutaSFTP);
				fos.close();
				resultado = true;
				file.delete();
			}
		} catch (Exception e) {
		}
		return resultado;
	}

	@Override
	public boolean guardarArchivoServidor(InputStream inputStreamFile, String rutaSFTP) {
		boolean resultado = false;
		try {
			if (channelSftp != null) {
				// this.borrarArchivoServidor(rutaSFTP);
				channelSftp.put(inputStreamFile, rutaSFTP);
				resultado = true;
			}
		} catch (Exception e) {

		}
		return resultado;
	}

	@Override
	public boolean moverArchivoServidor(String rutaOrigenSFTP, String rutaDestinoSFTP) {
		boolean resultado = false;
		try {
			if (channelSftp != null) {
				channelSftp.rename(rutaOrigenSFTP, rutaDestinoSFTP);
				// this.borrarArchivoServidor(rutaOrigenSFTP);
				resultado = true;
			}
		} catch (Exception e) {

		}
		return resultado;
	}

	@Override
	public boolean borrarArchivoServidor(String rutaServidor) {
		boolean resultado = false;
		try {
			if (channelSftp != null) {
				if (channelSftp.realpath(rutaServidor) != null) {
					channelSftp.rm(rutaServidor);
					resultado = true;
				}
			}
		} catch (SftpException e) {
		}

		return resultado;
	}

	@Override
	public void borrarDirectorioServidor(String rutaServidor) {
		try {
			if (channelSftp != null) {
				if (channelSftp.realpath(rutaServidor) != null) {
					List<DirectorioDTO> directoriosSFTP = this.listarDirectoriosSFTP(rutaServidor);
					if (directoriosSFTP != null && !directoriosSFTP.isEmpty()) {
						for (DirectorioDTO dirDto : directoriosSFTP) {
							if (dirDto.getTipoDirectorio().ordinal() == ETipoDirectorio.ARCHIVO.ordinal()) {
								// this.borrarArchivoServidor(dirDto.getRuta() + dirDto.getNombre());
								if (directoriosSFTP.size() == 1) {
									channelSftp.rmdir(rutaServidor);
								}
							} else {
								this.borrarDirectorioServidor(dirDto.getRuta() + dirDto.getNombre() + SEPARADOR);
							}
						}
					} else {
						channelSftp.rmdir(rutaServidor);
					}
				}
			}
		} catch (SftpException e) {

		}
	}

	@Override
	public boolean descargarArchivo(String rutaSFTP, String rutaLocal) {
		boolean resultado = false;
		try {
			if (channelSftp != null) {
				channelSftp.get(rutaSFTP, rutaLocal);
				resultado = true;
			}
		} catch (SftpException e) {
			System.out.println(Arrays.toString(e.getStackTrace()));
		}

		return resultado;
	}

	@Override
	public InputStream obtenerInputStreamArchivo(String rutaSFTP) {
		InputStream resultado = null;
		try {
			if (channelSftp != null) {
				resultado = channelSftp.get(rutaSFTP);
			}
		} catch (SftpException e) {

		}

		return resultado;
	}

	@Override
	public List<ArchivoDTO> obtenerArchivos(String rutaSFTP, String nombreArchivo) {
		List<ArchivoDTO> listaArchivos = new ArrayList<>();
		try {
			if (channelSftp != null) {
				if (!StringUtils.isBlank(nombreArchivo)) {
					InputStream resultado = channelSftp.get(rutaSFTP + nombreArchivo);
					byte[] fileArrayBytes = IOUtils.toByteArray(resultado);
					resultado.close();

					ArchivoDTO archivoDTO = new ArchivoDTO();
					archivoDTO.setArchivo(fileArrayBytes);
					archivoDTO.setNombreArchivo(nombreArchivo);
					
					listaArchivos.add(archivoDTO);
				} else {
					List<String> nombresArchivos = this.listarArchivosFiltrados(rutaSFTP, null);
					for (String nombreFile : nombresArchivos) {
						if (!nombreFile.equalsIgnoreCase(".") && !nombreFile.equalsIgnoreCase("..")) {
//							InputStream resultado = channelSftp.get(rutaSFTP + nombreFile);
//							byte[] bytes = IOUtils.toByteArray(resultado);
//							resultado.close();

							ArchivoDTO archivoDTO = new ArchivoDTO();
//							archivoDTO.setArchivo(bytes);
							archivoDTO.setNombreArchivo(nombreFile);
							
							listaArchivos.add(archivoDTO);
						}
					}
				}
			}
		} catch (SftpException | IOException e) {
		}

		return listaArchivos;
	}

	@Override
	public boolean esValidaRuta(String rutaSFTP) {
		boolean resultado = false;
		try {
			if (channelSftp != null) {
				SftpATTRS stat = channelSftp.stat(rutaSFTP);
				if (stat != null) {
					resultado = stat.isDir();
					resultado = true;
				}
			}
		} catch (SftpException e) {
		}
		return resultado;
	}

	@Override
	public boolean crearDirectorio(String rutaSFTP) {
		boolean resultado = false;
		try {
			if (channelSftp != null) {
				channelSftp.mkdir(rutaSFTP);
				resultado = true;
			}
		} catch (SftpException e) {
		}
		return resultado;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DirectorioDTO> listarDirectoriosSFTP(String rutaSFTP) {
		List<DirectorioDTO> listaCarpetas = new ArrayList<>();
		try {
			if (channelSftp != null) {
				List<LsEntry> carpetas = channelSftp.ls(rutaSFTP);
				for (LsEntry carpeta : carpetas) {
					if (!carpeta.getFilename().equalsIgnoreCase(".") && !carpeta.getFilename().equalsIgnoreCase("..")) {
						DirectorioDTO dto = construirDirectorioDto(carpeta.getFilename(), rutaSFTP);
						listaCarpetas.add(dto);
					}
				}
			}
		} catch (SftpException ex) {
		}

		return listaCarpetas;
	}

	/**
	 * Método para construir un DTO de directorio desde un Servidor SFTP
	 */
	@SuppressWarnings("unchecked")
	private DirectorioDTO construirDirectorioDto(String nombreArchivo, String rutaSFTP) {
		DirectorioDTO dto = new DirectorioDTO();
		try {
			dto.setNombre(nombreArchivo);
			dto.setRuta(rutaSFTP);
			List<LsEntry> carpetasInternas = channelSftp.ls(rutaSFTP + nombreArchivo);
			if (carpetasInternas.size() > 1) {
				dto.setTipoDirectorio(ETipoDirectorio.CARPETA);
			} else {
				dto.setTipoDirectorio(ETipoDirectorio.ARCHIVO);
			}
		} catch (SftpException ex) {
		}

		return dto;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> listarArchivosFiltrados(String rutaSFTP, String nombre) {
		List<String> resultado = new ArrayList<>();
		try {
			List<LsEntry> carpetasInternas = channelSftp.ls(rutaSFTP);

			if (!StringUtils.isBlank(nombre)) {
				Predicate<LsEntry> f = g -> g.getFilename().matches(nombre + "-[0-9]{1,2}");
				carpetasInternas.stream().filter(f).forEach(e -> resultado.add(e.getFilename()));
			} else {
				carpetasInternas.stream().forEach(e -> resultado.add(e.getFilename()));
			}
		} catch (SftpException ex) {

		}

		return resultado;
	}

}
