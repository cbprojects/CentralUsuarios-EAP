package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.project.cafe.CentralUsuarios.dao.AbstractDao;
import com.project.cafe.CentralUsuarios.dao.IUsuarioSedeDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarUsuarioSedeDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.SedeTB;
import com.project.cafe.CentralUsuarios.model.UsuarioSedeTB;

@Repository
public class UsuarioSedeDaoImpl extends AbstractDao<UsuarioSedeTB> implements IUsuarioSedeDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;
	
	@Override
	@Transactional
	public UsuarioSedeTB crearUsuarioSede(UsuarioSedeTB newUsuarioSede) {
		super.create(newUsuarioSede);
		return newUsuarioSede;
	}


	@Override
	@Transactional
	public UsuarioSedeTB modificarUsuarioSede(UsuarioSedeTB newUsuarioSede) {
		super.update(newUsuarioSede);
		return newUsuarioSede;
	}
	
	@Override
	public List<UsuarioSedeTB> buscarUsuarioSedePorId(long idU, long idS) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT r FROM UsuarioSedeTB r WHERE 1 = 1 ");
		// WHERE
		JPQL.append(" AND r.usuario.id = :IDU ");
		pamameters.put("IDU", idU);
		
		JPQL.append(" AND r.sede.id = :IDS ");
		pamameters.put("IDS", idS);
		// Q. Order By
		JPQL.append(" ORDER BY r.id");
		// END QUERY

		TypedQuery<UsuarioSedeTB> query = em.createQuery(JPQL.toString(), UsuarioSedeTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}
	
	@Override
	public ResponseConsultarDTO<UsuarioSedeTB> consultarUsuarioSedeFiltros(RequestConsultarUsuarioSedeDTO request) {

		ResponseConsultarDTO<UsuarioSedeTB> response = new ResponseConsultarDTO<>();

		// PARAMETROS
		Map<String, Object> pamametros = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT r FROM UsuarioSedeTB r WHERE 1 = 1 ");
		// WHERE
		if (request.getUsuarioSede().getUsuario()!=null && request.getUsuarioSede().getUsuario().getId()!=0l) {
			JPQL.append(" AND r.usuario.id = :IDU ");
			pamametros.put("IDU", request.getUsuarioSede().getUsuario().getId());
		}

		if (request.getUsuarioSede().getSede()!=null && request.getUsuarioSede().getSede().getId()!=0l) {
			JPQL.append(" AND r.sede.id = :IDA ");
			pamametros.put("IDA", request.getUsuarioSede().getSede().getId());
		}

		String COUNT = "SELECT COUNT(r) " + JPQL.toString().substring(JPQL.toString().indexOf("FROM"));

		// Q. Order By
		JPQL.append(" ORDER BY r.id DESC");
		// END QUERY

		// QUERY COUNT
		TypedQuery<Long> queryCount = em.createQuery(COUNT, Long.class);
		pamametros.forEach((k, v) -> queryCount.setParameter(k, v));
		response.setRegistrosTotales(queryCount.getSingleResult());

		TypedQuery<UsuarioSedeTB> query = em.createQuery(JPQL.toString(), UsuarioSedeTB.class);
		pamametros.forEach((k, v) -> query.setParameter(k, v));

		query.setFirstResult(request.getRegistroInicial());
		query.setMaxResults(request.getCantidadRegistro());
		List<UsuarioSedeTB> listaSociedadArea= query.getResultList();

		response.setResultado(listaSociedadArea);

		return response;
	}

	@Override
	public List<SedeTB> buscarSedesActivasPorUsuario(String email) {
		List<SedeTB> sedes = new ArrayList<>();
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT us FROM UsuarioSedeTB us WHERE 1 = 1 ");
		// WHERE
		if (!StringUtils.isBlank(email)) {
			JPQL.append("AND us.usuario.email = :EMAIL ");
			pamameters.put("EMAIL", email);
		}
		// Q. Order By
		JPQL.append(" ORDER BY us.id");
		// END QUERY

		TypedQuery<UsuarioSedeTB> query = em.createQuery(JPQL.toString(), UsuarioSedeTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		List<UsuarioSedeTB> usuarioSedes = query.getResultList();
		
		if(!usuarioSedes.isEmpty()) {
			for (UsuarioSedeTB usuarioSedeTB : usuarioSedes) {
				sedes.add(usuarioSedeTB.getSede());
			}
		}

		return sedes;
	}

}
