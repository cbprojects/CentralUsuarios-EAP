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
import com.project.cafe.CentralUsuarios.dao.IRolDao;
import com.project.cafe.CentralUsuarios.model.RolTB;

@Repository
public class RolDaoImpl extends AbstractDao<RolTB> implements IRolDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public RolTB crearRol(RolTB rol) {
		super.create(rol);
		return rol;
	}

	@Override
	public RolTB modificarRol(RolTB rol) {
		super.update(rol);
		return rol;
	}
	
	@Override
	public List<RolTB> buscarRolPorCodigo(String codigoRol) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT r FROM RolTB r WHERE 1 = 1 ");
		// WHERE
		if (!StringUtils.isBlank(codigoRol)) {
			JPQL.append("AND r.codigo = :CODIGO ");
			pamameters.put("CODIGO", codigoRol);
		}
		// Q. Order By
		JPQL.append(" ORDER BY r.id");
		// END QUERY

		TypedQuery<RolTB> query = em.createQuery(JPQL.toString(), RolTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}

}
