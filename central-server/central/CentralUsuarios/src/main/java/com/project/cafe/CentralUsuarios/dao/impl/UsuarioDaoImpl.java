package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.project.cafe.CentralUsuarios.dao.AbstractDao;
import com.project.cafe.CentralUsuarios.dao.IUsuarioDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarUsuariosDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.UsuarioTB;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;

@Repository
public class UsuarioDaoImpl extends AbstractDao<UsuarioTB> implements IUsuarioDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public UsuarioTB crearUsuario(UsuarioTB usuario) {
		super.create(usuario);
		return usuario;
	}

	@Override
	public UsuarioTB modificarUsuario(UsuarioTB usuario) {
		super.update(usuario);
		return usuario;
	}

	@Override
	public List<UsuarioTB> buscarUsuarioPorEmail(String email) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT u FROM UsuarioTB u WHERE 1 = 1 ");
		// WHERE
		if (!StringUtils.isBlank(email)) {
			JPQL.append("AND u.email = :EMAIL");
			pamameters.put("EMAIL", email);
		}
		// Q. Order By
		JPQL.append(" ORDER BY u.id");
		// END QUERY

		TypedQuery<UsuarioTB> query = em.createQuery(JPQL.toString(), UsuarioTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}

	@Override
	public ResponseConsultarDTO<UsuarioTB> consultarUsuariosPorFiltros(RequestConsultarUsuariosDTO filtroUsuario)
			throws Exception {

		ResponseConsultarDTO<UsuarioTB> response = new ResponseConsultarDTO<>();

		// PARAMETROS
		Map<String, Object> pamametros = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT u FROM UsuarioTB u WHERE 1 = 1 ");
		// WHERE
		if (filtroUsuario.getusuario() != null && filtroUsuario.getusuario().getPerfil() != null
				&& filtroUsuario.getusuario().getPerfil().getId() != 0L) {
			JPQL.append(" AND u.perfil.id = :IDPERFIL ");
			pamametros.put("IDPERFIL", filtroUsuario.getusuario().getPerfil().getId());
		}

		if (StringUtils.isNotBlank(filtroUsuario.getusuario().getEmail())) {
			filtroUsuario.getusuario().setEmail(filtroUsuario.getusuario().getEmail());
			JPQL.append(" AND UPPER(u.email) LIKE UPPER(:EMAIL) ");
			pamametros.put("EMAIL", ConstantesValidaciones.COMODIN_BD + filtroUsuario.getusuario().getEmail()
					+ ConstantesValidaciones.COMODIN_BD);
		}

		if (StringUtils.isNotBlank(filtroUsuario.getusuario().getDocumento())) {
			JPQL.append(" AND u.documento = :DOCUMENTO");
			pamametros.put("DOCUMENTO", ConstantesValidaciones.COMODIN_BD + filtroUsuario.getusuario().getDocumento()
					+ ConstantesValidaciones.COMODIN_BD);
		}

		if (StringUtils.isNotBlank(filtroUsuario.getusuario().getNombre())) {
			JPQL.append(" AND UPPER(u.nombre) LIKE UPPER(:NOMBRE) ");
			pamametros.put("NOMBRE", ConstantesValidaciones.COMODIN_BD + filtroUsuario.getusuario().getNombre()
					+ ConstantesValidaciones.COMODIN_BD);
		}

		String COUNT = "SELECT COUNT(u) " + JPQL.toString().substring(JPQL.toString().indexOf("FROM"));

		// Q. Order By
		JPQL.append(" ORDER BY u.id DESC");
		// END QUERY

		// QUERY COUNT
		TypedQuery<Long> queryCount = em.createQuery(COUNT, Long.class);
		pamametros.forEach((k, v) -> queryCount.setParameter(k, v));
		response.setRegistrosTotales(queryCount.getSingleResult());

		TypedQuery<UsuarioTB> query = em.createQuery(JPQL.toString(), UsuarioTB.class);
		pamametros.forEach((k, v) -> query.setParameter(k, v));

		query.setFirstResult(filtroUsuario.getRegistroInicial());
		query.setMaxResults(filtroUsuario.getCantidadRegistro());
		List<UsuarioTB> listaUsuarios = query.getResultList();

		response.setResultado(listaUsuarios);

		return response;
	}

	@Override
	public Optional<UsuarioTB> loginUsuario(String user, String clave) {
		try {
			// PARAMETROS
			Map<String, Object> pamameters = new HashMap<>();

			// QUERY
			StringBuilder JPQL = new StringBuilder("SELECT u FROM UsuarioTB u WHERE u.estado = 1 ");
			// WHERE
			JPQL.append(" AND u.email = :EMAIL ");
			pamameters.put("EMAIL", user);
			JPQL.append(" AND u.contrasena = :CLAVE ");
			pamameters.put("CLAVE", clave);

			// Q. Order By
			JPQL.append(" ORDER BY u.id");
			// END QUERY

			TypedQuery<UsuarioTB> query = em.createQuery(JPQL.toString(), UsuarioTB.class);
			pamameters.forEach((k, v) -> query.setParameter(k, v));

			return Optional.of(query.getSingleResult());

		} catch (Exception ex) {
			if (ex instanceof NoResultException) {
				return Optional.empty();
			}
		}
		return null;
	}

	@Override
	public List<UsuarioTB> consultarUsuarioActivo() {
		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT s FROM UsuarioTB s WHERE s.estado = 1 ");

		// Q. Order By
		JPQL.append(" ORDER BY s.id");
		// END QUERY

		TypedQuery<UsuarioTB> query = em.createQuery(JPQL.toString(), UsuarioTB.class);

		return query.getResultList();
	}

}
