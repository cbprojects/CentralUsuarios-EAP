package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.project.cafe.CentralUsuarios.dao.AbstractDao;
import com.project.cafe.CentralUsuarios.dao.IRolPerfilDao;
import com.project.cafe.CentralUsuarios.model.RolPerfilTB;

@Repository
public class RolPerfilDaoImpl extends AbstractDao<RolPerfilTB> implements IRolPerfilDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public RolPerfilTB crearRolPerfil(RolPerfilTB rolPerfil) {
		super.create(rolPerfil);
		return rolPerfil;
	}

	@Override
	@Transactional
	public void eliminarRolPerfilMasivoXPerfil(Long id) {
		em.createQuery("delete from  RolPerfilTB " 
                + " t where t.perfil.id =:IDPERFIL").setParameter("IDPERFIL", id).executeUpdate();
	}

	@Override
	public List<RolPerfilTB> BuscarRolesSegunPerfil(long id) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT t FROM RolPerfilTB t WHERE t.estado = 1 ");
		// WHERE
		JPQL.append(" AND t.perfil.id = :IDPERFIL ");
		pamameters.put("IDPERFIL", id);
		
		// Q. Order By
		JPQL.append(" ORDER BY t.id");
		// END QUERY

		TypedQuery<RolPerfilTB> query = em.createQuery(JPQL.toString(), RolPerfilTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}
	
}
