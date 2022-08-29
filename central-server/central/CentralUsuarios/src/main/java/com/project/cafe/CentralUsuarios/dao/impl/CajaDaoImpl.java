package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.project.cafe.CentralUsuarios.dao.AbstractDao;
import com.project.cafe.CentralUsuarios.dao.ICajaDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarCajasDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.CajaTB;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;

@Repository
public class CajaDaoImpl extends AbstractDao<CajaTB> implements ICajaDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public CajaTB crearCaja(CajaTB caja) {
		super.create(caja);
		return caja;
	}

	@Override
	public CajaTB modificarCaja(CajaTB caja) {
		super.update(caja);
		return caja;
	}

	@Override
	public List<CajaTB> buscarcajaPorCodigoCliente(String codigocaja, long idCliente) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT c FROM CajaTB c WHERE 1 = 1 ");
		// WHERE
		if (!StringUtils.isBlank(codigocaja)) {
			JPQL.append(" AND c.codigoAlterno = :CODIGO ");
			pamameters.put("CODIGO", codigocaja);
		}
		if (idCliente == 0) {
			JPQL.append(" AND c.cliente.id = :CLIENTE ");
			pamameters.put("CLIENTE", idCliente);
		}
		// Q. Order By
		JPQL.append(" ORDER BY c.id");
		// END QUERY

		TypedQuery<CajaTB> query = em.createQuery(JPQL.toString(), CajaTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}

//	@Override
	public ResponseConsultarDTO<CajaTB> consultarCajasFiltros(RequestConsultarCajasDTO filtroCaja) {

		ResponseConsultarDTO<CajaTB> response = new ResponseConsultarDTO<>();

		// PARAMETROS
		Map<String, Object> pamametros = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT c FROM CajaTB c " + " INNER JOIN c.entrepano e "
				+ "INNER JOIN e.estante es " + "INNER JOIN es.cuerpo cu " + "INNER JOIN cu.bloque bl "
				+ "INNER JOIN bl.bodega b " + "INNER JOIN b.sede s " + " WHERE 1 = 1 ");
		// WHERE
		if (StringUtils.isNotBlank(filtroCaja.getCaja().getCodigoAlterno())) {
			JPQL.append(" AND UPPER(c.codigoAlterno) LIKE UPPER(:CODIGO) ");
			pamametros.put("CODIGO", ConstantesValidaciones.COMODIN_BD + filtroCaja.getCaja().getCodigoAlterno()
					+ ConstantesValidaciones.COMODIN_BD);
		}

		if (StringUtils.isNotBlank(filtroCaja.getCaja().getDescripcion())) {
			JPQL.append(" AND UPPER(c.descripcion) LIKE UPPER(:DESCRIPCION) ");
			pamametros.put("DESCRIPCION", ConstantesValidaciones.COMODIN_BD + filtroCaja.getCaja().getDescripcion()
					+ ConstantesValidaciones.COMODIN_BD);
		}

		if (StringUtils.isNotBlank(filtroCaja.getCaja().getCodigoBarras())) {
			JPQL.append(" AND c.codigoBarras = :CODIGOBARRAS ");
			pamametros.put("CODIGOBARRAS", filtroCaja.getCaja().getCodigoBarras());
		}

		if (StringUtils.isNotBlank(filtroCaja.getCaja().getQr())) {
			JPQL.append(" AND c.qr = :QR ");
			pamametros.put("QR", filtroCaja.getCaja().getQr());
		}
		if (filtroCaja.getCaja().getCliente() != null) {
			if (filtroCaja.getCaja().getCliente().getId() != 0) {
				JPQL.append(" AND c.cliente.id = :IDCLIENTE ");
				pamametros.put("IDCLIENTE", filtroCaja.getCaja().getCliente().getId());
			}

		}
		if (filtroCaja.getCaja().getEntrepano() != null) {
			if (filtroCaja.getCaja().getEntrepano().getId() != 0) {
				JPQL.append(" AND c.entrepano.id = :IDENTREPANO ");
				pamametros.put("IDENTREPANO", filtroCaja.getCaja().getEntrepano().getId());
			}

		}
		if (filtroCaja.getIdEstante() != 0) {
			JPQL.append(" AND es.id = :IDESTANTE ");
			pamametros.put("IDESTANTE", filtroCaja.getIdEstante());
		}
		if (filtroCaja.getIdCuerpo() != 0) {
			JPQL.append(" AND cu.id = :IDCUERPO ");
			pamametros.put("IDCUERPO", filtroCaja.getIdCuerpo());
		}
		if (filtroCaja.getIdBloque() != 0) {
			JPQL.append(" AND bl.id = :IDBLOQUE ");
			pamametros.put("IDBLOQUE", filtroCaja.getIdBloque());
		}
		if (filtroCaja.getIdBodega() != 0) {
			JPQL.append(" AND b.id = :IDBODEGA ");
			pamametros.put("IDBODEGA", filtroCaja.getIdBodega());
		}
		if (filtroCaja.getIdSede() != 0) {
			JPQL.append(" AND s.id = :IDSEDE ");
			pamametros.put("IDSEDE", filtroCaja.getIdSede());
		}

		String COUNT = "SELECT COUNT(c) " + JPQL.toString().substring(JPQL.toString().indexOf("FROM"));

		// Q. Order By
		JPQL.append(" ORDER BY c.id DESC");
		// END QUERY

		// QUERY COUNT
		TypedQuery<Long> queryCount = em.createQuery(COUNT, Long.class);
		pamametros.forEach((k, v) -> queryCount.setParameter(k, v));
		response.setRegistrosTotales(queryCount.getSingleResult());

		TypedQuery<CajaTB> query = em.createQuery(JPQL.toString(), CajaTB.class);
		pamametros.forEach((k, v) -> query.setParameter(k, v));

		query.setFirstResult(filtroCaja.getRegistroInicial());
		query.setMaxResults(filtroCaja.getCantidadRegistro());
		List<CajaTB> listaCajas = query.getResultList();

		response.setResultado(listaCajas);

		return response;
	}

	@Override
	public List<CajaTB> consultarCajasPorCliente(Long idCliente) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT c FROM CajaTB c WHERE 1 = 1 ");
		// WHERE
		JPQL.append(" AND c.cliente.id = :ID ");
		pamameters.put("ID", idCliente);

		// Q. Order By
		JPQL.append(" ORDER BY c.id");
		// END QUERY

		TypedQuery<CajaTB> query = em.createQuery(JPQL.toString(), CajaTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}

	@Override
	public CajaTB consultarCajaPorId(Long idCaja) {
		// PARAMETROS
				Map<String, Object> pamameters = new HashMap<>();

				// QUERY
				StringBuilder JPQL = new StringBuilder("SELECT c FROM CajaTB c WHERE 1 = 1 ");
				// WHERE
				JPQL.append(" AND c.id = :ID ");
				pamameters.put("ID", idCaja);

				// Q. Order By
				JPQL.append(" ORDER BY c.id");
				// END QUERY

				TypedQuery<CajaTB> query = em.createQuery(JPQL.toString(), CajaTB.class);
				pamameters.forEach((k, v) -> query.setParameter(k, v));

				return query.getSingleResult();
	}

}
