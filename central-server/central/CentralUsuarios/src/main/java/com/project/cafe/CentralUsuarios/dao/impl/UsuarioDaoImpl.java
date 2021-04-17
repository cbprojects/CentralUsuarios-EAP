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
	public List<UsuarioTB> buscarUsuarioPorNick(String usuario) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT u FROM UsuarioTB u WHERE 1 = 1 ");
		// WHERE
		if (!StringUtils.isBlank(usuario)) {
			JPQL.append("AND u.usuario = :USUARIO ");
			pamameters.put("USUARIO", usuario);
		}
		// Q. Order By
		JPQL.append(" ORDER BY u.id");
		// END QUERY

		TypedQuery<UsuarioTB> query = em.createQuery(JPQL.toString(), UsuarioTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}

	@Override
	public ResponseConsultarDTO<UsuarioTB> consultarUsuariosPorFiltros(RequestConsultarUsuariosDTO filtroUsuario) {

		ResponseConsultarDTO<UsuarioTB> response = new ResponseConsultarDTO<>();
		
		// PARAMETROS
		Map<String, Object> pamametros = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT u FROM UsuarioTB u WHERE 1 = 1 ");
		// WHERE
		if (StringUtils.isNotBlank(filtroUsuario.getusuario().getUsuario())) {
			JPQL.append(" AND UPPER(u.usuario) LIKE UPPER(:USUARIO) ");
			pamametros.put("USUARIO", ConstantesValidaciones.COMODIN_BD + filtroUsuario.getusuario().getUsuario()
					+ ConstantesValidaciones.COMODIN_BD);
		}
		
		if (filtroUsuario.getusuario() != null && filtroUsuario.getusuario().getPerfil()!= null
				&& filtroUsuario.getusuario().getPerfil().getId() != 0L) {
			JPQL.append(" AND u.perfil.id = :IDPERFIL ");
			pamametros.put("IDPERFIL", ConstantesValidaciones.COMODIN_BD + filtroUsuario.getusuario().getPerfil().getId()
					+ ConstantesValidaciones.COMODIN_BD);
		}
		
		if (StringUtils.isNotBlank(filtroUsuario.getusuario().getUsuario())) {
			JPQL.append(" AND UPPER(u.usuario) LIKE UPPER(:USUARIO) ");
			pamametros.put("USUARIO", ConstantesValidaciones.COMODIN_BD + filtroUsuario.getusuario().getUsuario()
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
		
		//QUERY COUNT
		TypedQuery<Long> queryCount = em.createQuery(COUNT, Long.class);
		pamametros.forEach((k, v) -> queryCount.setParameter(k, v));
		response.setRegistrosTotales(queryCount.getSingleResult());
		

		TypedQuery<UsuarioTB> query = em.createQuery(JPQL.toString(), UsuarioTB.class);
		pamametros.forEach((k, v) -> query.setParameter(k, v));
		
		query.setFirstResult(filtroUsuario.getRegistroInicial());
		query.setMaxResults(filtroUsuario.getCantidadRegistro());
		List<UsuarioTB> listaUsuarios=query.getResultList();
		
		response.setResultado(listaUsuarios);

		return response;
	}

}