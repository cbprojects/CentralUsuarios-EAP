package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.project.cafe.CentralUsuarios.dao.AbstractDao;
import com.project.cafe.CentralUsuarios.dao.IPerfilDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarPerfilesDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.PerfilTB;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;

@Repository
public class PerfilDaoImpl extends AbstractDao<PerfilTB> implements IPerfilDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public PerfilTB crearPerfil(PerfilTB perfilAutor) {
		super.create(perfilAutor);
		return perfilAutor;
	}

	@Override
	public PerfilTB modificarPerfil(PerfilTB perfilAutor) {
		super.update(perfilAutor);
		return perfilAutor;
	}

	@Override
	public List<PerfilTB> buscarPerfilPorCodigo(String codigoPerfil) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT t FROM PerfilTB t WHERE 1 = 1 ");
		// WHERE
		if (!StringUtils.isBlank(codigoPerfil)) {
			JPQL.append("AND t.codigo = :CODIGO ");
			pamameters.put("CODIGO", codigoPerfil);
		}
		// Q. Order By
		JPQL.append(" ORDER BY t.id");
		// END QUERY

		TypedQuery<PerfilTB> query = em.createQuery(JPQL.toString(), PerfilTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}

	@Override
	public ResponseConsultarDTO<PerfilTB> consultarPerfilesPorFiltros(RequestConsultarPerfilesDTO filtroPerfil, boolean activos) {

		ResponseConsultarDTO<PerfilTB> response = new ResponseConsultarDTO<>();

		// PARAMETROS
		Map<String, Object> pamametros = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT r FROM PerfilTB r WHERE 1 = 1 ");
		
		if(activos) {
			JPQL.append(" AND r.estado = 1 ");
		}
		// WHERE
		if (filtroPerfil.getPerfil() != null) {
			if (StringUtils.isNotBlank(filtroPerfil.getPerfil().getCodigo())) {
				JPQL.append(" AND UPPER(r.codigo) LIKE UPPER(:CODIGO) ");
				pamametros.put("CODIGO", ConstantesValidaciones.COMODIN_BD + filtroPerfil.getPerfil().getCodigo()
						+ ConstantesValidaciones.COMODIN_BD);
			}

			if (StringUtils.isNotBlank(filtroPerfil.getPerfil().getDescripcion())) {
				JPQL.append(" AND UPPER(r.descripcion) LIKE UPPER(:DESCRIPCION) ");
				pamametros.put("DESCRIPCION", ConstantesValidaciones.COMODIN_BD
						+ filtroPerfil.getPerfil().getDescripcion() + ConstantesValidaciones.COMODIN_BD);
			}
		}

		String COUNT = "SELECT COUNT(r) " + JPQL.toString().substring(JPQL.toString().indexOf("FROM"));

		// Q. Order By
		JPQL.append(" ORDER BY r.id DESC");
		// END QUERY

		// QUERY COUNT
		TypedQuery<Long> queryCount = em.createQuery(COUNT, Long.class);
		pamametros.forEach((k, v) -> queryCount.setParameter(k, v));
		response.setRegistrosTotales(queryCount.getSingleResult());

		TypedQuery<PerfilTB> query = em.createQuery(JPQL.toString(), PerfilTB.class);
		pamametros.forEach((k, v) -> query.setParameter(k, v));

		query.setFirstResult(filtroPerfil.getRegistroInicial());
		query.setMaxResults(filtroPerfil.getCantidadRegistro());
		List<PerfilTB> listaRoles = query.getResultList();

		response.setResultado(listaRoles);

		return response;
	}

	@Override
	public List<PerfilTB> consultarPerfilesActivos() {

		List<PerfilTB> listaPerfiles = new ArrayList<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT r FROM PerfilTB r WHERE 1 = 1 ");
		// WHERE

		JPQL.append(" AND UPPER(r.estado) = 1 ");

		// Q. Order By
		JPQL.append(" ORDER BY r.id DESC");
		// END QUERY
		TypedQuery<PerfilTB> query = em.createQuery(JPQL.toString(), PerfilTB.class);
		listaPerfiles = query.getResultList();

		return listaPerfiles;
	}

}
