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
import com.project.cafe.CentralUsuarios.dao.ISociedadDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarSociedadDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.SociedadTB;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;

@Repository
public class SociedadDaoImpl extends AbstractDao<SociedadTB> implements ISociedadDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public SociedadTB crearSociedad(SociedadTB sociedad) {
		super.create(sociedad);
		return sociedad;
	}

	@Override
	public SociedadTB modificarSociedad(SociedadTB sociedad) {
		super.update(sociedad);
		return sociedad;
	}
	
	@Override
	public List<SociedadTB> buscarRolPorNombre(String nombre) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT r FROM SociedadTB r WHERE 1 = 1 ");
		// WHERE
		if (!StringUtils.isBlank(nombre)) {
			JPQL.append("AND UPPER(r.nombre) = :NOMBRE ");
			pamameters.put("NOMBRE", nombre.toUpperCase());
		}
		// Q. Order By
		JPQL.append(" ORDER BY r.id");
		// END QUERY

		TypedQuery<SociedadTB> query = em.createQuery(JPQL.toString(), SociedadTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}
	
	@Override
	public ResponseConsultarDTO<SociedadTB> consultarSociedadFiltros(RequestConsultarSociedadDTO request) {

		ResponseConsultarDTO<SociedadTB> response = new ResponseConsultarDTO<>();

		// PARAMETROS
		Map<String, Object> pamametros = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT r FROM SociedadTB r WHERE 1 = 1 ");
		// WHERE
		if (StringUtils.isNotBlank(request.getSociedad().getNombre())) {
			JPQL.append(" AND UPPER(r.nombre) LIKE UPPER(:NOMBRE) ");
			pamametros.put("NOMBRE", ConstantesValidaciones.COMODIN_BD + request.getSociedad().getNombre()
					+ ConstantesValidaciones.COMODIN_BD);
		}

		if (StringUtils.isNotBlank(request.getSociedad().getNombre10())) {
			JPQL.append(" AND UPPER(r.nombre10) LIKE UPPER(:NOMBRE10) ");
			pamametros.put("NOMBRE10", ConstantesValidaciones.COMODIN_BD + request.getSociedad().getNombre10()
					+ ConstantesValidaciones.COMODIN_BD);
		}
		
		if (StringUtils.isNotBlank(request.getSociedad().getTax())) {
			JPQL.append(" AND UPPER(r.tax) LIKE UPPER(:TAX) ");
			pamametros.put("TAX", ConstantesValidaciones.COMODIN_BD + request.getSociedad().getTax()
					+ ConstantesValidaciones.COMODIN_BD);
		}
		
		if (request.getSociedad().getCliente()!=null && request.getSociedad().getCliente().getId()!=0l) {
			JPQL.append(" AND r.cliente.id =:CLIENTE ");
			pamametros.put("CLIENTE", request.getSociedad().getCliente().getId());
		}
		
		if (request.getSociedad().getServidor()!=null && request.getSociedad().getServidor().getId()!=0l) {
			JPQL.append(" AND r.servidor.id =:SERVIDOR ");
			pamametros.put("SERVIDOR", request.getSociedad().getServidor().getId());
		}

		String COUNT = "SELECT COUNT(r) " + JPQL.toString().substring(JPQL.toString().indexOf("FROM"));

		// Q. Order By
		JPQL.append(" ORDER BY r.id DESC");
		// END QUERY

		// QUERY COUNT
		TypedQuery<Long> queryCount = em.createQuery(COUNT, Long.class);
		pamametros.forEach((k, v) -> queryCount.setParameter(k, v));
		response.setRegistrosTotales(queryCount.getSingleResult());

		TypedQuery<SociedadTB> query = em.createQuery(JPQL.toString(), SociedadTB.class);
		pamametros.forEach((k, v) -> query.setParameter(k, v));

		query.setFirstResult(request.getRegistroInicial());
		query.setMaxResults(request.getCantidadRegistro());
		List<SociedadTB> listaSociedad = query.getResultList();

		response.setResultado(listaSociedad);

		return response;
	}
	
	@Override
	public List<SociedadTB> buscarSociedadActiva() {

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT s FROM SociedadTB s WHERE s.estado = 1 ");

		// Q. Order By
		JPQL.append(" ORDER BY s.id");
		// END QUERY

		TypedQuery<SociedadTB> query = em.createQuery(JPQL.toString(), SociedadTB.class);
		
		return query.getResultList();
	}
	
	@Override
	public List<SociedadTB> consultarSociedadActivaPorCliente(Long idCliente) {
		// PARAMETROS
		Map<String, Object> pamametros = new HashMap<>();
		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT s FROM SociedadTB s WHERE s.estado = 1 ");
		
		JPQL.append(" AND s.cliente.id =:CLIENTE ");
		pamametros.put("CLIENTE", idCliente);
		
		// Q. Order By
		JPQL.append(" ORDER BY s.id");
		// END QUERY
		
		TypedQuery<SociedadTB> query = em.createQuery(JPQL.toString(), SociedadTB.class);
		pamametros.forEach((k, v) -> query.setParameter(k, v));
		return query.getResultList();
	}

}
