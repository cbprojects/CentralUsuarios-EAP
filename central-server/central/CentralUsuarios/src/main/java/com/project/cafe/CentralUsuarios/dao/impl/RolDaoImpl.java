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
import com.project.cafe.CentralUsuarios.dto.RequestConsultarRolesDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.RolTB;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;

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

	@Override
	public ResponseConsultarDTO<RolTB> consultarRolesPorFiltros(RequestConsultarRolesDTO filtroRol) {

		ResponseConsultarDTO<RolTB> response = new ResponseConsultarDTO<>();

		// PARAMETROS
		Map<String, Object> pamametros = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT r FROM RolTB r WHERE 1 = 1 ");
		// WHERE
		if (StringUtils.isNotBlank(filtroRol.getRol().getCodigo())) {
			JPQL.append(" AND UPPER(r.codigo) LIKE UPPER(:CODIGO) ");
			pamametros.put("CODIGO", ConstantesValidaciones.COMODIN_BD + filtroRol.getRol().getCodigo()
					+ ConstantesValidaciones.COMODIN_BD);
		}

		if (StringUtils.isNotBlank(filtroRol.getRol().getDescripcion())) {
			JPQL.append(" AND UPPER(r.descripcion) LIKE UPPER(:DESCRIPCION) ");
			pamametros.put("DESCRIPCION", ConstantesValidaciones.COMODIN_BD + filtroRol.getRol().getDescripcion()
					+ ConstantesValidaciones.COMODIN_BD);
		}

		String COUNT = "SELECT COUNT(r) " + JPQL.toString().substring(JPQL.toString().indexOf("FROM"));

		// Q. Order By
		JPQL.append(" ORDER BY r.id DESC");
		// END QUERY

		// QUERY COUNT
		TypedQuery<Long> queryCount = em.createQuery(COUNT, Long.class);
		pamametros.forEach((k, v) -> queryCount.setParameter(k, v));
		response.setRegistrosTotales(queryCount.getSingleResult());

		TypedQuery<RolTB> query = em.createQuery(JPQL.toString(), RolTB.class);
		pamametros.forEach((k, v) -> query.setParameter(k, v));

		query.setFirstResult(filtroRol.getRegistroInicial());
		query.setMaxResults(filtroRol.getCantidadRegistro());
		List<RolTB> listaRoles = query.getResultList();

		response.setResultado(listaRoles);

		return response;
	}

	@Override
	public List<RolTB> BuscarRolesNoAsociadosSegunPerfil(long id) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("Select grt from RolTB grt where grt.estado = 1 "
				+ "and grt.id not in(select grpt.rol from RolPerfilTB grpt where grpt.perfil.id =:IDPERFIL )");
		// WHERE
		pamameters.put("IDPERFIL", id);

		// END QUERY

		TypedQuery<RolTB> query = em.createQuery(JPQL.toString(), RolTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}

}
