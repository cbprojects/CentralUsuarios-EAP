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
			if (seccion == ESeccionDashboard.BOXES1) {
				DashBoardBoxesUnoDTO boxes1 = new DashBoardBoxesUnoDTO();
				boxes1.setBox1(llenarBoxDto(lista.get(0)));
				boxes1.setBox2(llenarBoxDto(lista.get(1)));
				boxes1.setBox3(llenarBoxDto(lista.get(2)));
				boxes1.setBox4(llenarBoxDto(lista.get(3)));
				boxes1.setBox5(llenarBoxDto(lista.get(4)));
				boxes1.setBox6(llenarBoxDto(lista.get(5)));
				response.setBoxes1(boxes1);
			}
			if (seccion == ESeccionDashboard.BOXES2) {
				DashBoardBoxesDosDTO boxes2 = new DashBoardBoxesDosDTO();
				boxes2.setBox1(llenarBoxDto(lista.get(0)));
				boxes2.setBox2(llenarBoxDto(lista.get(1)));
				response.setBoxes2(boxes2);
			}
			if (seccion == ESeccionDashboard.BOXES3) {
				DashBoardBoxesTresDTO boxes3 = new DashBoardBoxesTresDTO();
				boxes3.setBox1(llenarBoxDto(lista.get(0)));
				boxes3.setBox2(llenarBoxDto(lista.get(1)));
				boxes3.setBox3(llenarBoxDto(lista.get(1)));
				boxes3.setBox4(llenarBoxDto(lista.get(1)));
				response.setBoxes3(boxes3);
			}
			if (seccion == ESeccionDashboard.CHARTBOX1) {
				response.setChartBox1(llenarChartBoxDto(lista.get(0)));
			}

			if (seccion == ESeccionDashboard.CHARTBOX2) {
				response.setChartBox2(llenarChartBoxDto(lista.get(0)));
			}
			if (seccion == ESeccionDashboard.CHARTBOX3) {
				response.setChartBox3(llenarChartBoxDto(lista.get(0)));
			}
			if (seccion == ESeccionDashboard.CHARTBOX4) {
				response.setChartBox4(llenarChartBoxDto(lista.get(0)));
			}

			if (seccion == ESeccionDashboard.CHARTPIE1) {
				response.setChartPie1(llenarChartPieDto(lista.get(0)));
			}

			if (seccion == ESeccionDashboard.CHARTPIE2) {
				response.setChartPie2(llenarChartPieDto(lista.get(0)));
			}

			if (seccion == ESeccionDashboard.CHARTTABLE1) {
				response.setChartTable1(llenarChartTableDto(lista));
			}

			if (seccion == ESeccionDashboard.TABLE1) {
				response.setTable1(llenarTablePersonaDto(lista, 1));
			}

			if (seccion == ESeccionDashboard.TABLE2) {
				response.setTable2(llenarTablePersonaDto(lista, 1));
			}

			if (seccion == ESeccionDashboard.TABLE3) {
				response.setTable3(llenarTablePersonaDto(lista, 1));
			}

			if (seccion == ESeccionDashboard.TABLE4) {
				response.setTable4(llenarTablePersonaDto(lista, 1));
			}

			if (seccion == ESeccionDashboard.TABLE5) {
				response.setTable5(llenarTableFacturaDto(lista));
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

	private DashBoardChartBoxDTO llenarChartBoxDto(Object[] objeto) {
		DashBoardChartBoxDTO chartbox = new DashBoardChartBoxDTO();
		DashBoardBoxDTO box = new DashBoardBoxDTO();
		DashBoardChartDTO data = new DashBoardChartDTO();
		String[] valores = Arrays.copyOf(objeto, objeto.length, String[].class);
		box.setColor(valores[3]);
		box.setPrincipalLabel(valores[4]);
		box.setPrincipalValue(valores[5]);
		box.setSubtitleLabel(valores[6]);
		box.setSubtitleValue(valores[7]);
		box.setUpperLabel(valores[8]);
		box.setUpperValue(valores[9]);
		box.setEstado(valores[10]);

		data.setColor(valores[3]);
		data.setColumns(Arrays.asList(valores[0].split(",")));
		data.setLabel(valores[1]);
		data.setType(valores[2]);

		chartbox.setBox(box);
		chartbox.setData(data);
		chartbox.setEstado(valores[10]);
		return chartbox;
	}

	private DashBoardChartPieDTO llenarChartPieDto(Object[] objeto) {
		DashBoardChartPieDTO chartpie = new DashBoardChartPieDTO();
		DashBoardChartDTO data = new DashBoardChartDTO();
		String[] valores = Arrays.copyOf(objeto, objeto.length, String[].class);

		data.setColumns(Arrays.asList(valores[0].split(",")));
		data.setLabel(valores[1]);
		data.setType(valores[2]);
		data.setColor(valores[3]);

		chartpie.setData(data);
		chartpie.setEstado(valores[4]);
		return chartpie;
	}

	private DashBoardChartTableDTO llenarChartTableDto(List<Object[]> lista) {
		DashBoardChartTableDTO chartable = new DashBoardChartTableDTO();
		DashBoardChartDTO data = new DashBoardChartDTO();
		DashBoardTablePersonaDTO table = new DashBoardTablePersonaDTO();
		Object[] objeto = lista.get(0);

		String[] valores = Arrays.copyOf(objeto, objeto.length, String[].class);

		data.setColumns(Arrays.asList(valores[0].split(",")));
		data.setLabel(valores[1]);
		data.setType(valores[2]);
		data.setColor(valores[3]);

		table.setCabeceras(Arrays.asList(valores[4].split(",")));

		table.setValues(llenarValuesTable(lista, 0));

		table.setEstado(valores[8]);

		chartable.setData(data);
		chartable.setEstado(valores[8]);
		chartable.setTable(table);
		return chartable;
	}

	private List<DashBoardPersonaDTO> llenarValuesTable(List<Object[]> lista, int tipo) {
		List<DashBoardPersonaDTO> listaPersona = new ArrayList<DashBoardPersonaDTO>();
		String[] valores;
		for (Object[] objeto : lista) {
			DashBoardPersonaDTO persona = new DashBoardPersonaDTO();
			valores = Arrays.copyOf(objeto, objeto.length, String[].class);

			if (tipo == 0) {
				persona.setUsuario(valores[5]);
				persona.setCommit(valores[6]);
				persona.setFechaCommit(valores[7]);
				listaPersona.add(persona);
			}

			if (tipo == 1) {
				persona.setUsuario(valores[1]);
				persona.setUsage(valores[2]);
				persona.setPayment(valores[3]);
				persona.setActivity(valores[4]);
				persona.setSatisfaction(valores[5]);
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

		table.setEstado(valores[6]);

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

}
