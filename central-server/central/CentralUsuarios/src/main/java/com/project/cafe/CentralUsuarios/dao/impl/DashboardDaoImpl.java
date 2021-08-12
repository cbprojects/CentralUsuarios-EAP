package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.project.cafe.CentralUsuarios.dao.AbstractDao;
import com.project.cafe.CentralUsuarios.dao.IBloqueDao;
import com.project.cafe.CentralUsuarios.dao.IDashboardDao;
import com.project.cafe.CentralUsuarios.dto.DashBoardBoxDTO;
import com.project.cafe.CentralUsuarios.dto.DashBoardBoxesDosDTO;
import com.project.cafe.CentralUsuarios.dto.DashBoardBoxesTresDTO;
import com.project.cafe.CentralUsuarios.dto.DashBoardBoxesUnoDTO;
import com.project.cafe.CentralUsuarios.dto.DashBoardChartBoxDTO;
import com.project.cafe.CentralUsuarios.dto.DashBoardChartDTO;
import com.project.cafe.CentralUsuarios.dto.DashBoardChartPieDTO;
import com.project.cafe.CentralUsuarios.dto.DashBoardChartTableDTO;
import com.project.cafe.CentralUsuarios.dto.DashBoardFacturaDTO;
import com.project.cafe.CentralUsuarios.dto.DashBoardImgDTO;
import com.project.cafe.CentralUsuarios.dto.DashBoardPersonaDTO;
import com.project.cafe.CentralUsuarios.dto.DashBoardTableFacturaDTO;
import com.project.cafe.CentralUsuarios.dto.DashBoardTablePersonaDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarDashBoardDTO;
import com.project.cafe.CentralUsuarios.enums.ESeccionDashboard;
import com.project.cafe.CentralUsuarios.model.BloqueTB;

@Repository
public class DashboardDaoImpl extends AbstractDao<BloqueTB> implements IDashboardDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public RequestConsultarDashBoardDTO consultarDashboard(String idPerfil) {
		RequestConsultarDashBoardDTO response = new RequestConsultarDashBoardDTO();

		for (ESeccionDashboard seccion : ESeccionDashboard.getListaSeccion()) {
			StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("Dashboard_Home");
			storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN).setParameter(1,
					idPerfil);
			storedProcedure.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN).setParameter(2,
					seccion.ordinal());

			List<Object[]> lista = storedProcedure.getResultList();
			switch (seccion) {
			case BOXES1:
				DashBoardBoxesUnoDTO boxes1 = new DashBoardBoxesUnoDTO();
				boxes1.setBox1(llenarBoxDto(lista.get(0)));
				boxes1.setBox2(llenarBoxDto(lista.get(1)));
				boxes1.setBox3(llenarBoxDto(lista.get(2)));
				boxes1.setBox4(llenarBoxDto(lista.get(3)));
				boxes1.setBox5(llenarBoxDto(lista.get(4)));
				boxes1.setBox6(llenarBoxDto(lista.get(5)));
				response.setBoxes1(boxes1);
				break;
			case BOXES2:
				DashBoardBoxesDosDTO boxes2 = new DashBoardBoxesDosDTO();
				boxes2.setBox1(llenarBoxDto(lista.get(0)));
				boxes2.setBox2(llenarBoxDto(lista.get(1)));
				response.setBoxes2(boxes2);
				break;
			case BOXES3:
				DashBoardBoxesTresDTO boxes3 = new DashBoardBoxesTresDTO();
				boxes3.setBox1(llenarBoxDto(lista.get(0)));
				boxes3.setBox2(llenarBoxDto(lista.get(1)));
				boxes3.setBox3(llenarBoxDto(lista.get(1)));
				boxes3.setBox4(llenarBoxDto(lista.get(1)));
				response.setBoxes3(boxes3);
				break;
			case CHARTBOX1:
				response.setChartBox1(llenarChartBoxDto(lista));
				break;
			case CHARTBOX2:
				response.setChartBox2(llenarChartBoxDto(lista));
				break;
			case CHARTBOX3:
				response.setChartBox3(llenarChartBoxDto(lista));
				break;
			case CHARTBOX4:
				response.setChartBox4(llenarChartBoxDto(lista));
				break;
			case CHARTPIE1:
				response.setChartPie1(llenarChartPieDto(lista));
				break;
			case CHARTPIE2:
				response.setChartPie2(llenarChartPieDto(lista));
				break;
			case CHARTCHARTTABLE1:
				response.setChartTable1(new DashBoardChartTableDTO());
				response.getChartTable1().setData(llenarListChartDto(lista));
				break;
			case TABLECHARTTABLE1:
				response.getChartTable1().setTable(llenarTablePersonaDto(lista, 0));
				break;
			case TABLE1:
				response.setTable1(llenarTablePersonaDto(lista, 1));
				break;
			case TABLE2:
				response.setTable2(llenarTablePersonaDto(lista, 1));
				break;
			case TABLE3:
				response.setTable3(llenarTablePersonaDto(lista, 1));
				break;
			case TABLE4:
				response.setTable4(llenarTablePersonaDto(lista, 1));
				break;
			case TABLE5:
				response.setTable5(llenarTableFacturaDto(lista));
				break;
			case IMG:
				response.setImg(llenarImg(lista.get(0)));
				break;

			default:
				break;
			}
		}
		return response;
	}

	private DashBoardBoxDTO llenarBoxDto(Object[] objeto) {
		DashBoardBoxDTO box = new DashBoardBoxDTO();
		String[] valores = Arrays.copyOf(objeto, objeto.length, String[].class);
		box.setColor(valores[0]);
		box.setPrincipalLabel(valores[1]);
		box.setPrincipalValue(valores[2]);
		box.setSubtitleLabel(valores[3]);
		box.setSubtitleValue(valores[4]);
		box.setUpperLabel(valores[5]);
		box.setUpperValue(valores[6]);
		box.setEstado(valores[7]);
		return box;
	}

	private DashBoardChartBoxDTO llenarChartBoxDto(List<Object[]> listaObjeto) {
		DashBoardChartBoxDTO chartbox = new DashBoardChartBoxDTO();
		DashBoardBoxDTO box = new DashBoardBoxDTO();
		List<DashBoardChartDTO> listData = new ArrayList<>();
		for (Object[] objeto : listaObjeto) {
			DashBoardChartDTO data = new DashBoardChartDTO();
			String[] valores = Arrays.copyOf(objeto, objeto.length, String[].class);
			box.setColor(valores[5]);
			box.setPrincipalLabel(valores[6]);
			box.setPrincipalValue(valores[7]);
			box.setSubtitleLabel(valores[8]);
			box.setSubtitleValue(valores[9]);
			box.setUpperLabel(valores[10]);
			box.setUpperValue(valores[11]);
			box.setEstado(valores[12]);

			data.setColumn(valores[0]);
			data.setValue(valores[1]);
			data.setLabel(valores[2]);
			data.setType(valores[3]);
			data.setColor(valores[4]);

			listData.add(data);
			chartbox.setEstado(valores[12]);
		}

		chartbox.setBox(box);
		chartbox.setData(listData);

		return chartbox;
	}

	private DashBoardChartPieDTO llenarChartPieDto(List<Object[]> listaObjeto) {
		DashBoardChartPieDTO chartpie = new DashBoardChartPieDTO();
		List<DashBoardChartDTO> listData = new ArrayList<>();
		for (Object[] objeto : listaObjeto) {
			DashBoardChartDTO data = new DashBoardChartDTO();
			String[] valores = Arrays.copyOf(objeto, objeto.length, String[].class);

			data.setColumn(valores[0]);
			data.setValue(valores[1]);
			data.setLabel(valores[2]);
			data.setType(valores[3]);
			data.setColor(valores[4]);
			listData.add(data);
			chartpie.setEstado(valores[5]);
		}

		chartpie.setData(listData);

		return chartpie;
	}

	private List<DashBoardChartDTO> llenarListChartDto(List<Object[]> listaObjeto) {
		List<DashBoardChartDTO> listData = new ArrayList<>();
		for (Object[] objeto : listaObjeto) {
			DashBoardChartDTO data = new DashBoardChartDTO();
			String[] valores = Arrays.copyOf(objeto, objeto.length, String[].class);

			data.setColumn(valores[0]);
			data.setValue(valores[1]);
			data.setLabel(valores[2]);
			data.setType(valores[3]);
			data.setColor(valores[4]);
			listData.add(data);
		}
		return listData;
	}

	private List<DashBoardPersonaDTO> llenarValuesTable(List<Object[]> lista, int tipo) {
		List<DashBoardPersonaDTO> listaPersona = new ArrayList<DashBoardPersonaDTO>();
		String[] valores;
		for (Object[] objeto : lista) {
			DashBoardPersonaDTO persona = new DashBoardPersonaDTO();
			valores = Arrays.copyOf(objeto, objeto.length, String[].class);

			if (tipo == 0) {
				persona.setUsuario(valores[1]);
				persona.setCommit(valores[2]);
				persona.setFechaCommit(valores[3]);
				persona.setRutaImagen(valores[4]);
				listaPersona.add(persona);
			}

			if (tipo == 1) {
				persona.setUsuario(valores[1]);
				persona.setUsage(valores[2]);
				persona.setPayment(valores[3]);
				persona.setActivity(valores[4]);
				persona.setSatisfaction(valores[5]);
				persona.setRutaImagen(valores[6]);
				listaPersona.add(persona);
			}
		}

		return listaPersona;
	}

	private DashBoardTablePersonaDTO llenarTablePersonaDto(List<Object[]> lista, int tipo) {
		DashBoardTablePersonaDTO table = new DashBoardTablePersonaDTO();

		Object[] objeto = lista.get(0);

		String[] valores = Arrays.copyOf(objeto, objeto.length, String[].class);

		table.setCabeceras(Arrays.asList(valores[0].split(",")));

		table.setValues(llenarValuesTable(lista, tipo));
		if (tipo == 0) {
			table.setEstado(valores[5]);
		}
		if (tipo == 1) {
			table.setEstado(valores[7]);
		}

		return table;
	}

	private DashBoardTableFacturaDTO llenarTableFacturaDto(List<Object[]> lista) {
		DashBoardTableFacturaDTO table = new DashBoardTableFacturaDTO();

		Object[] objeto = lista.get(0);

		String[] valores = Arrays.copyOf(objeto, objeto.length, String[].class);

		table.setCabeceras(Arrays.asList(valores[0].split(",")));

		table.setValues(llenarValuesFacturaTable(lista));

		table.setEstado(valores[8]);

		return table;
	}

	private List<DashBoardFacturaDTO> llenarValuesFacturaTable(List<Object[]> lista) {
		List<DashBoardFacturaDTO> listaFactura = new ArrayList<DashBoardFacturaDTO>();
		String[] valores;
		for (Object[] objeto : lista) {
			DashBoardFacturaDTO factura = new DashBoardFacturaDTO();
			valores = Arrays.copyOf(objeto, objeto.length, String[].class);

			factura.setNumero(valores[1]);
			factura.setEmpresa(valores[2]);
			factura.setCliente(valores[3]);
			factura.setVatNo(valores[4]);
			factura.setFechaCreacion(valores[5]);
			factura.setEstado(valores[6]);
			factura.setPrecio(valores[7]);

			listaFactura.add(factura);

		}

		return listaFactura;
	}

	private DashBoardImgDTO llenarImg(Object[] objeto) {
		DashBoardImgDTO img = new DashBoardImgDTO();
		String[] valores;
		valores = Arrays.copyOf(objeto, objeto.length, String[].class);
		img.setRutaImg(valores[0]);
		img.setEstado(valores[1]);
		return img;
	}

}
