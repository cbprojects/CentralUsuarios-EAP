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
import com.project.cafe.CentralUsuarios.dao.IUnidadDocumentalDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarUnidadDocumentalDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.UnidadDocumentalTB;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;

@Repository
public class UnidadDocumentalDaoImpl extends AbstractDao<UnidadDocumentalTB> implements IUnidadDocumentalDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public UnidadDocumentalTB crearUnidadDocumental(UnidadDocumentalTB unidadDocumental) {
		super.create(unidadDocumental);
		return unidadDocumental;
	}

	@Override
	public UnidadDocumentalTB modificarUnidadDocumental(UnidadDocumentalTB unidadDocumental) {
		super.update(unidadDocumental);
		return unidadDocumental;
	}

	@Override
	public List<UnidadDocumentalTB> buscarUnidadDocumentalPorCodigoSociedad(String codigo, long idSociedad) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT u FROM UnidadDocumentalTB u "
				+ " INNER JOIN u.sociedadArea sa "
				+ " WHERE 1 = 1 ");
		// WHERE
		if (!StringUtils.isBlank(codigo)) {
			JPQL.append(" AND u.codigo = :CODIGO ");
			pamameters.put("CODIGO", codigo);
		}
		if (idSociedad==0) {
			JPQL.append(" AND sa.sociedad.id = :SOCIEDAD ");
			pamameters.put("SOCIEDAD", idSociedad);
		}
		// Q. Order By
		JPQL.append(" ORDER BY u.id");
		// END QUERY

		TypedQuery<UnidadDocumentalTB> query = em.createQuery(JPQL.toString(), UnidadDocumentalTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}

//	@Override
	public ResponseConsultarDTO<UnidadDocumentalTB> consultarUnidadDocumentalFiltros(RequestConsultarUnidadDocumentalDTO filtroUnidadDocumental) {

		ResponseConsultarDTO<UnidadDocumentalTB> response = new ResponseConsultarDTO<>();

		// PARAMETROS
		Map<String, Object> pamametros = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT u FROM UnidadDocumentalTB u "
				+ " INNER JOIN u.sociedadArea sa "
				+ " WHERE 1 = 1 ");
		// WHERE
		if (!StringUtils.isBlank(filtroUnidadDocumental.getUnidadDocumental().getCodigo())) {
			JPQL.append(" AND u.codigo = :CODIGO ");
			pamametros.put("CODIGO", ConstantesValidaciones.COMODIN_BD 
					+filtroUnidadDocumental.getUnidadDocumental().getCodigo()+ ConstantesValidaciones.COMODIN_BD );
		}
		if (StringUtils.isBlank(filtroUnidadDocumental.getUnidadDocumental().getNombre())) {
			JPQL.append(" AND u.nombre = :NOMBRE ");
			pamametros.put("NOMBRE", ConstantesValidaciones.COMODIN_BD 
					+filtroUnidadDocumental.getUnidadDocumental().getNombre()+ ConstantesValidaciones.COMODIN_BD );
		}
		if (StringUtils.isBlank(filtroUnidadDocumental.getUnidadDocumental().getCodigoBarra())) {
			JPQL.append(" AND u.codigoBarra = :CODIGOBARRAS ");
			pamametros.put("CODIGOBARRAS", filtroUnidadDocumental.getUnidadDocumental().getCodigoBarra());
		}
		if (filtroUnidadDocumental.getUnidadDocumental().getFechaRecibe()==null) {
			JPQL.append(" AND u.fechaRecibe = :FECHARECIBE ");
			pamametros.put("FECHARECIBE", filtroUnidadDocumental.getUnidadDocumental().getFechaRecibe());
		}
		if(filtroUnidadDocumental.getUnidadDocumental().getTipoDocumental()==null) {
			if(filtroUnidadDocumental.getUnidadDocumental().getTipoDocumental().getId()==0) {
				JPQL.append(" AND u.tipoDocumental.id = :IDTIPODOCUMENTAL ");
				pamametros.put("IDTIPODOCUMENTAL", filtroUnidadDocumental.getUnidadDocumental().getTipoDocumental().getId());
			}
		}
		if(filtroUnidadDocumental.getUnidadDocumental().getContenedor()==null) {
			if(filtroUnidadDocumental.getUnidadDocumental().getContenedor().getId()==0) {
				JPQL.append(" AND u.contenedor.id = :IDCONTENEDOR ");
				pamametros.put("IDCONTENEDOR", filtroUnidadDocumental.getUnidadDocumental().getContenedor().getId());
			}
		}
		if(filtroUnidadDocumental.getUnidadDocumental().getSociedadArea().getSociedad()==null) {
			if(filtroUnidadDocumental.getUnidadDocumental().getSociedadArea().getSociedad().getId()==0) {
				JPQL.append(" AND sa.sociedad.id = :IDSOCIEDAD ");
				pamametros.put("IDSOCIEDAD", filtroUnidadDocumental.getUnidadDocumental().getSociedadArea().getSociedad().getId());
			}
		}
		if(filtroUnidadDocumental.getUnidadDocumental().getSociedadArea().getArea()==null) {
			if(filtroUnidadDocumental.getUnidadDocumental().getSociedadArea().getSociedad().getId()==0) {
				JPQL.append(" AND sa.area.id = :IDAREA ");
				pamametros.put("IDAREA", filtroUnidadDocumental.getUnidadDocumental().getSociedadArea().getArea().getId());
			}
		}
		
		String COUNT = "SELECT COUNT(u) " + JPQL.toString().substring(JPQL.toString().indexOf("FROM"));

		// Q. Order By
		JPQL.append(" ORDER BY u.id DESC");
		// END QUERY

		// QUERY COUNT
		TypedQuery<Long> queryCount = em.createQuery(COUNT, Long.class);
		pamametros.forEach((k, v) -> queryCount.setParameter(k, v));
		response.setRegistrosTotales(queryCount.getSingleResult());

		TypedQuery<UnidadDocumentalTB> query = em.createQuery(JPQL.toString(), UnidadDocumentalTB.class);
		pamametros.forEach((k, v) -> query.setParameter(k, v));

		query.setFirstResult(filtroUnidadDocumental.getRegistroInicial());
		query.setMaxResults(filtroUnidadDocumental.getCantidadRegistro());
		List<UnidadDocumentalTB> listaUnidadDocumental = query.getResultList();

		response.setResultado(listaUnidadDocumental);

		return response;
	}

	

}
