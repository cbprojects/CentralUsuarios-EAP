package com.project.cafe.CentralUsuarios.service.impl;

import java.io.File;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.service.IReportesService;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportesServiceImpl implements IReportesService {

	@Override
	public byte[] generarReporteEJM(String nombreArchivoJasper) {
		byte[] data = null;
		// List<PostTB> listaPost = iPostsDao.consultarPosts();
		String urlJasper = ConstantesValidaciones.RUTA_JASPER_REPORTS + nombreArchivoJasper;
 
		try {
			File file = new ClassPathResource(urlJasper).getFile();
			JasperPrint print = JasperFillManager.fillReport(file.getPath(), null,
					new JRBeanCollectionDataSource(null));
			data = JasperExportManager.exportReportToPdf(print);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

}
