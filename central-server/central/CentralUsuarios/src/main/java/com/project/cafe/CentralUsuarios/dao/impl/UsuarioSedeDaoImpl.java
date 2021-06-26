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
import com.project.cafe.CentralUsuarios.dao.IUsuarioSedeDao;
import com.project.cafe.CentralUsuarios.model.SedeTB;
import com.project.cafe.CentralUsuarios.model.UsuarioSedeTB;

@Repository
public class UsuarioSedeDaoImpl extends AbstractDao<UsuarioSedeTB> implements IUsuarioSedeDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

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
