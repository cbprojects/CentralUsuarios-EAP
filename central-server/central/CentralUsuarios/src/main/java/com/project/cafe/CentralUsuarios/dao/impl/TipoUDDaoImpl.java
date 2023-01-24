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
import com.project.cafe.CentralUsuarios.dao.ITipoUDDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarMasivoDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.TipoUDTB;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;

@Repository
public class TipoUDDaoImpl extends AbstractDao<TipoUDTB> implements ITipoUDDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;
	
	@Override
	public TipoUDTB crearTipoUD(TipoUDTB tipo) {
		super.create(tipo);
		return tipo;
	}

	@Override
	public TipoUDTB modificarTipoUD(TipoUDTB tipo) {
		super.update(tipo);
		return tipo;
	}

	@Override
	public List<TipoUDTB> buscarTipoUdPorCodigo(String nombre) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT r FROM TipoUDTB r WHERE 1 = 1 ");
		// WHERE
		if (!StringUtils.isBlank(nombre)) {
			JPQL.append("AND UPPER(r.nombre) = :NOMBRE ");
			pamameters.put("NOMBRE", nombre.toUpperCase());
		}
		// Q. Order By
		JPQL.append(" ORDER BY r.id");
		// END QUERY

		TypedQuery<TipoUDTB> query = em.createQuery(JPQL.toString(), TipoUDTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}
	
	@Override
	public ResponseConsultarDTO<TipoUDTB> consultarTipoUDFiltros(RequestConsultarMasivoDTO request){

		ResponseConsultarDTO<TipoUDTB> response = new ResponseConsultarDTO<>();

		// PARAMETROS
		Map<String, Object> pamametros = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT r FROM TipoUDTB r WHERE 1 = 1 ");
		// WHERE
		if (StringUtils.isNotBlank(request.getMasivo().getNombre1())) {
			JPQL.append(" AND UPPER(r.nombre) LIKE UPPER(:NOMBRE) ");
			pamametros.put("NOMBRE", ConstantesValidaciones.COMODIN_BD + request.getMasivo().getNombre1()
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

		TypedQuery<TipoUDTB> query = em.createQuery(JPQL.toString(), TipoUDTB.class);
		pamametros.forEach((k, v) -> query.setParameter(k, v));

		query.setFirstResult(request.getRegistroInicial());
		query.setMaxResults(request.getCantidadRegistro());
		List<TipoUDTB> listaCliente = query.getResultList();

		response.setResultado(listaCliente);

		return response;
	}

	@Override
	public List<TipoUDTB> buscarTipoUDActivos() {

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT tud FROM TipoUDTB tud WHERE 1 = 1 ");
		// WHERE
			JPQL.append("AND tud.estado = 1 ");
			
		// Q. Order By
		JPQL.append(" ORDER BY tud.id");
		// END QUERY

		TypedQuery<TipoUDTB> query = em.createQuery(JPQL.toString(), TipoUDTB.class);	

		return query.getResultList();
	}

}
