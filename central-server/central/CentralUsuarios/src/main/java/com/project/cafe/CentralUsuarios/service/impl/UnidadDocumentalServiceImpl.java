package com.project.cafe.CentralUsuarios.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IActaDao;
import com.project.cafe.CentralUsuarios.dao.ICajaDao;
import com.project.cafe.CentralUsuarios.dao.IClienteDao;
import com.project.cafe.CentralUsuarios.dao.IRolPerfilDao;
import com.project.cafe.CentralUsuarios.dao.IUnidadDocumentalDao;
import com.project.cafe.CentralUsuarios.dao.IUsuarioDao;
import com.project.cafe.CentralUsuarios.dto.ArchivoDTO;
import com.project.cafe.CentralUsuarios.dto.CajaListDTO;
import com.project.cafe.CentralUsuarios.dto.MailDTO;
import com.project.cafe.CentralUsuarios.dto.RequestAprobarRecepcionDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarArchivoUdDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarListaUdDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarUDRecepcionDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarUnidadDocumentalDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseGenerarPdfDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseMensajeCodigoDTO;
import com.project.cafe.CentralUsuarios.dto.UdListDTO;
import com.project.cafe.CentralUsuarios.dto.UdPdfItconDTO;
import com.project.cafe.CentralUsuarios.model.ActaTB;
import com.project.cafe.CentralUsuarios.model.CajaTB;
import com.project.cafe.CentralUsuarios.model.ClienteTB;
import com.project.cafe.CentralUsuarios.model.PerfilTB;
import com.project.cafe.CentralUsuarios.model.UnidadDocumentalTB;
import com.project.cafe.CentralUsuarios.model.UsuarioTB;
import com.project.cafe.CentralUsuarios.service.ICajaService;
import com.project.cafe.CentralUsuarios.service.IUnidadDocumentalService;
import com.project.cafe.CentralUsuarios.util.UtilMail;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRFontNotFoundException;

@Service
public class UnidadDocumentalServiceImpl implements IUnidadDocumentalService {

	@Value("${email.servidor}")
	private String EMAIL_SERVIDOR;

	@Autowired
	private IUsuarioDao usuarioDao;

	@Autowired
	private IRolPerfilDao rolPerfilDao;

	@Autowired
	private IUnidadDocumentalDao unidadDocumentalDAO;

	@Autowired
	private ICajaDao cajaDAO;

	@Autowired
	private IClienteDao clienteDAO;

	@Autowired
	private IActaDao actaDAO;

	@Autowired
	private UtilMail mailUtil;

	@Autowired
	private ICajaService cajaService;

	@Transactional
	@Override
	public UnidadDocumentalTB crearUnidadDocumental(UnidadDocumentalTB unidadDocumental) {
		return unidadDocumentalDAO.crearUnidadDocumental(unidadDocumental);
	}

	@Transactional
	@Override
	public UnidadDocumentalTB modificarUnidadDocumental(UnidadDocumentalTB unidadDocumental) {
		return unidadDocumentalDAO.modificarUnidadDocumental(unidadDocumental);
	}

	@Override
	public List<UnidadDocumentalTB> buscarUnidadDocumentalPorCodigoSociedad(String codigo, long idSociedad) {
		return unidadDocumentalDAO.buscarUnidadDocumentalPorCodigoSociedad(codigo, idSociedad);
	}

	@Override
	public ResponseConsultarDTO<UnidadDocumentalTB> consultarUnidadDocumentalFiltros(
			RequestConsultarUnidadDocumentalDTO filtroUnidadDocumental) {
		return unidadDocumentalDAO.consultarUnidadDocumentalFiltros(filtroUnidadDocumental);
	}

	@Override
	public UnidadDocumentalTB buscarUnidadDocumentalPorId(long idUnidadDocumental) {
		return unidadDocumentalDAO.buscarUnidadDocumentalPorId(idUnidadDocumental);
	}

	@Override
	public List<UnidadDocumentalTB> RequestConsultarUnidadDocumentalPorCaja(Long idCaja) {
		return unidadDocumentalDAO.RequestConsultarUnidadDocumentalPorCaja(idCaja);
	}

	@Override
	@Transactional
	public void cambiarCajaUnidadDocumentalMasivo(List<UnidadDocumentalTB> lstUnidadDocumentalCajaUno, Long idCaja) {
		CajaTB nuevaCaja = new CajaTB();
		nuevaCaja = cajaDAO.consultarCajaPorId(idCaja);
		for (UnidadDocumentalTB unidadDocumentalTB : lstUnidadDocumentalCajaUno) {
			unidadDocumentalTB.setCaja(nuevaCaja);
			unidadDocumentalDAO.modificarUnidadDocumental(unidadDocumentalTB);
		}
	}

	@Override
	public List<UnidadDocumentalTB> consultarUnidadDocumentalList(RequestConsultarListaUdDTO request) {
		return unidadDocumentalDAO.consultarUnidadDocumentalList(request);
	}

	@Override
	public List<CajaListDTO> obtenerArchivos(RequestConsultarArchivoUdDTO request) {
		List<UnidadDocumentalTB> listaUD = unidadDocumentalDAO.obtenerArchivos(request);
		List<CajaListDTO> cajaLst = new ArrayList<>();
		if (!listaUD.isEmpty()) {
			for (int i = 0; i < listaUD.size(); i++) {
				if (!cajaLst.isEmpty()) {
					Boolean encontrado = false;
					for (int j = 0; j < cajaLst.size(); j++) {
						if (cajaLst.get(j).idCaja == listaUD.get(i).getCaja().getId()) {
							List<UdListDTO> udList = cajaLst.get(j).lstUdTotales;
							UdListDTO ud = new UdListDTO();
							ud.setIdUd(listaUD.get(i).getId());
							ud.setCodigoUd(listaUD.get(i).getCodigo() + "-" + listaUD.get(i).getNombre());
							List<String> strList = new ArrayList<String>();
							if (StringUtils.isNotBlank(listaUD.get(i).getNombreArchivos())) {
								String[] strArr = listaUD.get(i).getNombreArchivos().split("#--#");
								strList = new ArrayList<String>(Arrays.asList(strArr));
							}
							ud.setDocumentosUd(strList);
							udList.add(ud);
							cajaLst.get(j).setLstUdTotales(udList);
							encontrado = true;
							break;
						}
					}
					if (!encontrado) {
						CajaListDTO caja = new CajaListDTO();
						caja.setIdCaja(listaUD.get(i).getCaja().getId());
						caja.setCodigoCaja(listaUD.get(i).getCaja().getCodigoAlterno());
						List<UdListDTO> udList = new ArrayList<UdListDTO>();
						UdListDTO ud = new UdListDTO();
						ud.setIdUd(listaUD.get(i).getId());
						ud.setCodigoUd(listaUD.get(i).getCodigo() + "-" + listaUD.get(i).getNombre());
						List<String> strList = new ArrayList<String>();
						if (StringUtils.isNotBlank(listaUD.get(i).getNombreArchivos())) {
							String[] strArr = listaUD.get(i).getNombreArchivos().split("#--#");
							strList = new ArrayList<String>(Arrays.asList(strArr));
						}
						ud.setDocumentosUd(strList);
						udList.add(ud);
						caja.setLstUdTotales(udList);
						cajaLst.add(caja);
					}
				} else {
					CajaListDTO caja = new CajaListDTO();
					caja.setIdCaja(listaUD.get(i).getCaja().getId());
					caja.setCodigoCaja(listaUD.get(i).getCaja().getCodigoAlterno());
					List<UdListDTO> udList = new ArrayList<UdListDTO>();
					UdListDTO ud = new UdListDTO();
					ud.setIdUd(listaUD.get(i).getId());
					ud.setCodigoUd(listaUD.get(i).getCodigo() + "-" + listaUD.get(i).getNombre());
					List<String> strList = new ArrayList<String>();
					if (StringUtils.isNotBlank(listaUD.get(i).getNombreArchivos())) {
						String[] strArr = listaUD.get(i).getNombreArchivos().split("#--#");
						strList = new ArrayList<String>(Arrays.asList(strArr));
					}
					ud.setDocumentosUd(strList);
					udList.add(ud);
					caja.setLstUdTotales(udList);
					cajaLst.add(caja);
				}
			}
		}
		return cajaLst;
	}

	@Override
	public ResponseConsultarDTO<UnidadDocumentalTB> consultarUnidadDocumentalRecepcion(
			RequestConsultarUDRecepcionDTO request) {
		ResponseConsultarDTO<UnidadDocumentalTB> response = new ResponseConsultarDTO<>();
		List<CajaTB> lstCajas = cajaDAO.buscarcajaPorCodigoCliente("C-RECEP", request.getCliente().getId());
		if (lstCajas == null || lstCajas.isEmpty()) {
			response.setRegistrosTotales(0L);
			List<UnidadDocumentalTB> listaUnidadDocumental = new ArrayList<>();
			response.setResultado(listaUnidadDocumental);
		} else {
			response = unidadDocumentalDAO.consultarUnidadDocumentalRecepcion(request, lstCajas.get(0));
		}
		return response;
	}

	@Override
	public ResponseMensajeCodigoDTO aprobacionRecepcion(RequestAprobarRecepcionDTO request) {
		ResponseMensajeCodigoDTO response = new ResponseMensajeCodigoDTO();
		try {
			UnidadDocumentalTB unidad = unidadDocumentalDAO.buscarUnidadDocumentalPorId(request.getIdUD());
			unidad.setRecepcionAprobada(request.getAprobacion());
			unidad = unidadDocumentalDAO.modificarUnidadDocumental(unidad);
			response.setCodigo("0");
			response.setMensaje("Éxito");
		} catch (Exception e) {
			response.setCodigo("1");
			response.setMensaje("Error al momento de actualizar");
		}
		return response;
	}

	@Override
	public ResponseMensajeCodigoDTO aprobacionRecepcionTodo(RequestConsultarUDRecepcionDTO request) {
		ResponseMensajeCodigoDTO response = new ResponseMensajeCodigoDTO();
		ResponseConsultarDTO<UnidadDocumentalTB> respuesta = new ResponseConsultarDTO<>();
		List<CajaTB> lstCajas = cajaDAO.buscarcajaPorCodigoCliente("C-RECEP", request.getCliente().getId());
		if (lstCajas == null || lstCajas.isEmpty()) {
			response.setCodigo("1");
			response.setMensaje("El cliente no tiene caja en recepción");
		} else {
			respuesta = unidadDocumentalDAO.consultarUnidadDocumentalRecepcion(request, lstCajas.get(0));
			if (respuesta.getResultado().isEmpty()) {
				response.setCodigo("1");
				response.setMensaje("El cliente no tiene unidades documentales en recepción");
			}else {
				for (int i = 0; i < respuesta.getResultado().size(); i++) {
					respuesta.getResultado().get(i).setRecepcionAprobada(true);
					unidadDocumentalDAO.modificarUnidadDocumental(respuesta.getResultado().get(i));
				}
				response.setCodigo("0");
				response.setMensaje("Éxito");
			}
		}
		return response;
	}

	@Override
	public ResponseGenerarPdfDTO generarPdf(RequestConsultarUDRecepcionDTO request) {
		ResponseGenerarPdfDTO response = new ResponseGenerarPdfDTO();
		try {
			Map<String, Object> dataPdf = new HashMap<String, Object>();
			List<CajaTB> lstCajas = cajaDAO.buscarcajaPorCodigoCliente("C-RECEP", request.getCliente().getId());
			if (lstCajas == null || lstCajas.isEmpty()) {
				response.setCodigo("1");
				response.setMensaje("No existe una caja para el cliente");
			} else {
				List<UnidadDocumentalTB> listaUnidadDocumental = unidadDocumentalDAO
						.consultarUnidadDocumentalRecepcionPdf(request, lstCajas.get(0));
				if (listaUnidadDocumental == null || listaUnidadDocumental.isEmpty()) {
					response.setCodigo("1");
					response.setMensaje("No existe UD para el cliente en recepcion ");
				} else {
					dataPdf.put("clienteNombre", listaUnidadDocumental.get(0).getCaja().getCliente().getNombre());
					dataPdf.put("parrafoUno",
							"El día indicado en el asunto, se realiza la transferencia documental número "
									+ (listaUnidadDocumental.get(0).getCaja().getCliente().getNumeroFactura() + 1)
									+ ". Correspondiente a " + listaUnidadDocumental.size()
									+ " Unidades Documentales Nuevas del área correspondiente.");
					List<UdPdfItconDTO> lstDatosAprobados = convertirDatosUdPdf(listaUnidadDocumental);
					JRBeanCollectionDataSource item = new JRBeanCollectionDataSource(lstDatosAprobados);
					dataPdf.put("CollectionBean", item);
					ArchivoDTO archivo = new ArchivoDTO();
					Date fecha = new Date();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(fecha);
					SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
					String formater = format1.format(calendar.getTime());
					archivo.setNombreArchivo(listaUnidadDocumental.get(0).getCaja().getCliente().getNombre()
							+ " Documento_transferencia_"
							+ (listaUnidadDocumental.get(0).getCaja().getCliente().getNumeroFactura() + 1) + " "
							+ formater + ".pdf");
					archivo.setArchivo(generatePdf(dataPdf));
					response.setCodigo("0");
					response.setMensaje("Éxito");
					response.setArchivo(archivo);
				}
			}
		} catch (Exception e) {
			response.setCodigo("1");
			response.setMensaje("Error al momento de generar el pdf");
		}
		return response;

	}

	private List<UdPdfItconDTO> convertirDatosUdPdf(List<UnidadDocumentalTB> listaUnidadDocumental) {
		List<UdPdfItconDTO> lstPdf = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
		String formater = "";
		UdPdfItconDTO udPdf;
		for (UnidadDocumentalTB ud : listaUnidadDocumental) {
			udPdf = new UdPdfItconDTO();
			udPdf.setCaja(ud.getCajaRecibido());
			udPdf.setNombre(ud.getNombre());
			udPdf.setTipo(ud.getTipoDocumental().getNombre());
			udPdf.setContenedor(ud.getContenedor().getNombre());
			udPdf.setConIni(ud.getConsecutivoIni() == null ? " " : ud.getConsecutivoIni());
			udPdf.setConFin(ud.getConsecutivoFin() == null ? " " : ud.getConsecutivoFin());
			calendar.setTime(ud.getFechaIni());
			formater = format1.format(calendar.getTime());
			udPdf.setFechaIni(formater);
			calendar.setTime(ud.getFechaFin());
			formater = format1.format(calendar.getTime());
			udPdf.setFechaFin(formater);
			lstPdf.add(udPdf);
		}
		return lstPdf;
	}

	/**
	 * 
	 * Genera el pdf del formulario de credito
	 * 
	 */

	public byte[] generatePdf(Map<String, Object> data) {

		InputStream reportStream = null;
		byte[] pdfBytes = null;
		// String bytes = null;

		System.out.println("[generatePdf] -> Ingresa a generar el documento: ");

		try {
			reportStream = Thread.currentThread().getContextClassLoader().getResource("report/reporteRecepcion.jrxml")
					.openStream();
			JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
			// Rellenamos el reporte
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, new JREmptyDataSource());
			pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
			// bytes = Utilities.getBytesToPdf(pdfBytes);
			// String file = nameFile + ".pdf";
		} catch (IOException e) {
			System.out.println("Error generando pdf : {} ".concat(e.toString()));
		} catch (JRException e) {
			System.out.println("Error generando pdf : {} ".concat(e.toString()));
		} catch (JRFontNotFoundException e) {
			System.out.println("Error generando pdf : {} ".concat(e.toString()));
		} finally {
			if (reportStream != null) {
				cerrar(reportStream);
			}
		}
		return pdfBytes;
	}

	public String getBytesToPdf(byte[] file) {

		String bytes = "";

		if (file != null) {
			byte[] encodedBytes = Base64.getEncoder().encode(file);
			String encodedString = new String(encodedBytes);
			bytes = new String(encodedString);
		}

		return bytes;
	}

	public void cerrar(InputStream fis) {
		if (fis != null) {
			try {
				fis.close();
			} catch (Exception e) {
				System.out.println("Error cerrando pdf : {} ".concat(e.toString()));
			}
		}
	}

	@Override
	public ResponseMensajeCodigoDTO enviarPdf(RequestConsultarUDRecepcionDTO request) {
		ResponseMensajeCodigoDTO response = new ResponseMensajeCodigoDTO();
		try {
			Map<String, Object> dataPdf = new HashMap<String, Object>();
			List<CajaTB> lstCajas = cajaDAO.buscarcajaPorCodigoCliente("C-RECEP", request.getCliente().getId());
			if (lstCajas == null || lstCajas.isEmpty()) {
				response.setCodigo("1");
				response.setMensaje("No existe una caja para el cliente");
			} else {
				List<UnidadDocumentalTB> listaUnidadDocumental = unidadDocumentalDAO
						.consultarUnidadDocumentalRecepcionPdf(request, lstCajas.get(0));
				if (listaUnidadDocumental == null || listaUnidadDocumental.isEmpty()) {
					response.setCodigo("1");
					response.setMensaje("No existe UD para el cliente en recepcion ");
				} else {
					dataPdf.put("clienteNombre", listaUnidadDocumental.get(0).getCaja().getCliente().getNombre());
					dataPdf.put("parrafoUno",
							"El día indicado en el asunto, se realiza la transferencia documental número "
									+ (listaUnidadDocumental.get(0).getCaja().getCliente().getNumeroFactura() + 1)
									+ ". Correspondiente a " + listaUnidadDocumental.size()
									+ " Unidades Documentales Nuevas del área correspondiente.");
					List<UdPdfItconDTO> lstDatosAprobados = convertirDatosUdPdf(listaUnidadDocumental);
					JRBeanCollectionDataSource item = new JRBeanCollectionDataSource(lstDatosAprobados);
					dataPdf.put("CollectionBean", item);
					ArchivoDTO archivo = new ArchivoDTO();
					Date fecha = new Date();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(fecha);
					SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
					String formater = format1.format(calendar.getTime());
					archivo.setNombreArchivo(listaUnidadDocumental.get(0).getCaja().getCliente().getNombre()
							+ " Documento_transferencia_"
							+ (listaUnidadDocumental.get(0).getCaja().getCliente().getNumeroFactura() + 1) + " "
							+ formater + ".pdf");
					archivo.setArchivo(generatePdf(dataPdf));
					ClienteTB cliente = listaUnidadDocumental.get(0).getCaja().getCliente();
					cliente.setNumeroFactura(cliente.getNumeroFactura() + 1);
					cliente = clienteDAO.modificarCliente(cliente);
					UsuarioTB usuario = usuarioDao.consultarUsuariosPorId(request.getIdUser());
					ActaTB acta = llenarActa(cliente, usuario, listaUnidadDocumental.size());
					acta = actaDAO.crearActa(acta);
					actualizarUD(listaUnidadDocumental);
					enviarCorreosSatisfactorios(archivo, acta);
					response.setCodigo("0");
					response.setMensaje("Éxito");
				}
			}
		} catch (Exception e) {
			response.setCodigo("1");
			response.setMensaje("Fallo en envio de correo");
		}

		return response;
	}

	private void actualizarUD(List<UnidadDocumentalTB> listaUnidadDocumental) {
		CajaTB caja = cajaService.retornarCajaPrimeraPorCliente(listaUnidadDocumental.get(0).getCaja().getCliente());
		for (UnidadDocumentalTB unidadDocumentalTB : listaUnidadDocumental) {
			unidadDocumentalTB.setCaja(caja);
			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);
			unidadDocumentalTB.setFechaRecibe(date);
			unidadDocumentalDAO.modificarUnidadDocumental(unidadDocumentalTB);
		}
		System.out.println("Termino de actulizar UD totales");
	}

	private ActaTB llenarActa(ClienteTB cliente, UsuarioTB usuario, int size) {
		ActaTB acta = new ActaTB();
		Short activo = 1;
		acta.setCantidad(Long.valueOf(size));
		acta.setAprobada(false);
		acta.setCliente(cliente);
		acta.setEstado(activo);
		acta.setNumeroFactura(Long.valueOf(cliente.getNumeroFactura()));
		acta.setUsuario(usuario);
		acta.setUsuarioCreacion(usuario.getDocumento());
		acta.setUsuarioActualizacion(usuario.getDocumento());
		return acta;
	}

	private void enviarCorreosSatisfactorios(ArchivoDTO archivo, ActaTB acta) {
		MailDTO mailDto = new MailDTO();
		mailDto.setFrom(EMAIL_SERVIDOR);
		mailDto.setTo(acta.getUsuario().getEmail());
		// mailDto.setTo("jamm0465@hotmail.com");
		mailDto.setSubject(acta.getCliente().getNombre() + " documento de transferencias # " + acta.getNumeroFactura());
		// mailDto.setSubject("Cliente Prueba"+" documento de trnsferencias documental #
		// "+"30");
		List<PerfilTB> lstPerfil = rolPerfilDao.BuscarPerfilPorRolCodigo("ITCADM");
		List<UsuarioTB> lstUsuario;
		if (lstPerfil.isEmpty()) {
			lstUsuario = new ArrayList<>();
		} else {
			List<Long> lstPerfiles = new ArrayList<>();
			for (PerfilTB perfil : lstPerfil) {
				lstPerfiles.add(perfil.getId());
			}
			lstUsuario = usuarioDao.buscarUsuariosAdministrador(lstPerfiles);
		}

		String[] miarray;
		if (lstUsuario.isEmpty()) {
			miarray = null;
		} else {
			List<String> lstMail = new ArrayList<>();
			for (UsuarioTB usuario : lstUsuario) {
				lstMail.add(usuario.getEmail());
			}
			miarray = new String[lstMail.size()];
			miarray = lstMail.toArray(miarray);
		}
		Map<String, Object> model = new HashMap<>();

		mailDto.setModel(model);

		mailUtil.sendMailPdf(mailDto, miarray, archivo);

	}

	@Override
	public ResponseConsultarDTO<UnidadDocumentalTB> consultarUnidadDocumentalFiltrosRecep(
			RequestConsultarUnidadDocumentalDTO request) {
		ResponseConsultarDTO<UnidadDocumentalTB> response = new ResponseConsultarDTO<>();
		if (request.getUnidadDocumental().getSociedadArea().getSociedad().getCliente() != null
				&& request.getUnidadDocumental().getSociedadArea().getSociedad().getCliente().getId() != 0l) {
			List<CajaTB> lstCajas = cajaDAO.buscarcajaPorCodigoCliente("C-RECEP",
					request.getUnidadDocumental().getSociedadArea().getSociedad().getCliente().getId());
			if (lstCajas == null || lstCajas.isEmpty()) {
				response.setRegistrosTotales(0L);
				List<UnidadDocumentalTB> listaUnidadDocumental = new ArrayList<>();
				response.setResultado(listaUnidadDocumental);
			} else {
				request.getUnidadDocumental().setCaja(lstCajas.get(0));
				response = unidadDocumentalDAO.consultarUnidadDocumentalFiltrosRecep(request);
			}
		} else {
			response.setRegistrosTotales(0L);
			List<UnidadDocumentalTB> listaUnidadDocumental = new ArrayList<>();
			response.setResultado(listaUnidadDocumental);
		}
		return response;
	}

}
