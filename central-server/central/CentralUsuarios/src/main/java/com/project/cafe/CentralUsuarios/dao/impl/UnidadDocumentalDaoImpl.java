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
import com.project.cafe.CentralUsuarios.dto.RequestConsultarArchivoUdDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarListaUdDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarUDRecepcionDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarUnidadDocumentalDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.CajaTB;
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
		StringBuilder JPQL = new StringBuilder(
				"SELECT u FROM UnidadDocumentalTB u " + " INNER JOIN u.sociedadArea sa " + " WHERE 1 = 1 ");
		// WHERE
		if (!StringUtils.isBlank(codigo)) {
			JPQL.append(" AND u.codigo = :CODIGO ");
			pamameters.put("CODIGO", codigo);
		}
		if (idSociedad == 0) {
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

	@Override
	public ResponseConsultarDTO<UnidadDocumentalTB> consultarUnidadDocumentalFiltros(
			RequestConsultarUnidadDocumentalDTO filtroUnidadDocumental) {

		ResponseConsultarDTO<UnidadDocumentalTB> response = new ResponseConsultarDTO<>();

		// PARAMETROS
		Map<String, Object> pamametros = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder(
				"SELECT u FROM UnidadDocumentalTB u " + " INNER JOIN u.sociedadArea sa " + " WHERE 1 = 1 ");
		// WHERE
		if (StringUtils.isNotBlank(filtroUnidadDocumental.getUnidadDocumental().getCodigo())) {
			JPQL.append(" AND UPPER(u.codigo) LIKE UPPER(:CODIGO) ");
			pamametros.put("CODIGO", ConstantesValidaciones.COMODIN_BD
					+ filtroUnidadDocumental.getUnidadDocumental().getCodigo() + ConstantesValidaciones.COMODIN_BD);
		}
		if (StringUtils.isNotBlank(filtroUnidadDocumental.getUnidadDocumental().getNombre())) {
			JPQL.append(" AND UPPER(u.nombre) LIKE UPPER(:NOMBRE) ");
			pamametros.put("NOMBRE", ConstantesValidaciones.COMODIN_BD
					+ filtroUnidadDocumental.getUnidadDocumental().getNombre() + ConstantesValidaciones.COMODIN_BD);
		}
		if (StringUtils.isNotBlank(filtroUnidadDocumental.getUnidadDocumental().getCodigoBarra())) {
			JPQL.append(" AND u.codigoBarra = :CODIGOBARRAS ");
			pamametros.put("CODIGOBARRAS", filtroUnidadDocumental.getUnidadDocumental().getCodigoBarra());
		}
		if (filtroUnidadDocumental.getUnidadDocumental().getFechaRecibe() != null) {
			JPQL.append(" AND u.fechaRecibe = :FECHARECIBE ");
			pamametros.put("FECHARECIBE", filtroUnidadDocumental.getUnidadDocumental().getFechaRecibe());
		}
		if (filtroUnidadDocumental.getUnidadDocumental().getTipoDocumental() != null) {
			if (filtroUnidadDocumental.getUnidadDocumental().getTipoDocumental().getId() != 0) {
				JPQL.append(" AND u.tipoDocumental.id = :IDTIPODOCUMENTAL ");
				pamametros.put("IDTIPODOCUMENTAL",
						filtroUnidadDocumental.getUnidadDocumental().getTipoDocumental().getId());
			}
		}
		if (filtroUnidadDocumental.getUnidadDocumental().getContenedor() != null) {
			if (filtroUnidadDocumental.getUnidadDocumental().getContenedor().getId() != 0) {
				JPQL.append(" AND u.contenedor.id = :IDCONTENEDOR ");
				pamametros.put("IDCONTENEDOR", filtroUnidadDocumental.getUnidadDocumental().getContenedor().getId());
			}
		}
		if (filtroUnidadDocumental.getUnidadDocumental().getSociedadArea().getSociedad() != null) {
			if (filtroUnidadDocumental.getUnidadDocumental().getSociedadArea().getSociedad().getId() != 0) {
				JPQL.append(" AND sa.sociedad.id = :IDSOCIEDAD ");
				pamametros.put("IDSOCIEDAD",
						filtroUnidadDocumental.getUnidadDocumental().getSociedadArea().getSociedad().getId());
			}
		}
		if (filtroUnidadDocumental.getUnidadDocumental().getSociedadArea().getArea() != null) {
			if (filtroUnidadDocumental.getUnidadDocumental().getSociedadArea().getArea().getId() != 0) {
				JPQL.append(" AND sa.area.id = :IDAREA ");
				pamametros.put("IDAREA",
						filtroUnidadDocumental.getUnidadDocumental().getSociedadArea().getArea().getId());
			}
		}
		
		JPQL.append(" AND u.caja.codigoAlterno != :CAJA ");
		pamametros.put("CAJA","C-RECEP");
		

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

	@Override
	public ResponseConsultarDTO<UnidadDocumentalTB> consultarUnidadDocumentalFiltrosRecep(
			RequestConsultarUnidadDocumentalDTO filtroUnidadDocumental) {

		ResponseConsultarDTO<UnidadDocumentalTB> response = new ResponseConsultarDTO<>();

		// PARAMETROS
		Map<String, Object> pamametros = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder(
				"SELECT u FROM UnidadDocumentalTB u " + " INNER JOIN u.sociedadArea sa " + " WHERE 1 = 1 ");
		// WHERE
		if (StringUtils.isNotBlank(filtroUnidadDocumental.getUnidadDocumental().getCodigo())) {
			JPQL.append(" AND UPPER(u.codigo) LIKE UPPER(:CODIGO) ");
			pamametros.put("CODIGO", ConstantesValidaciones.COMODIN_BD
					+ filtroUnidadDocumental.getUnidadDocumental().getCodigo() + ConstantesValidaciones.COMODIN_BD);
		}
		if (StringUtils.isNotBlank(filtroUnidadDocumental.getUnidadDocumental().getNombre())) {
			JPQL.append(" AND UPPER(u.nombre) LIKE UPPER(:NOMBRE) ");
			pamametros.put("NOMBRE", ConstantesValidaciones.COMODIN_BD
					+ filtroUnidadDocumental.getUnidadDocumental().getNombre() + ConstantesValidaciones.COMODIN_BD);
		}
		if (StringUtils.isNotBlank(filtroUnidadDocumental.getUnidadDocumental().getCodigoBarra())) {
			JPQL.append(" AND u.codigoBarra = :CODIGOBARRAS ");
			pamametros.put("CODIGOBARRAS", filtroUnidadDocumental.getUnidadDocumental().getCodigoBarra());
		}
		if (filtroUnidadDocumental.getUnidadDocumental().getFechaRecibe() != null) {
			JPQL.append(" AND u.fechaRecibe = :FECHARECIBE ");
			pamametros.put("FECHARECIBE", filtroUnidadDocumental.getUnidadDocumental().getFechaRecibe());
		}
		if (filtroUnidadDocumental.getUnidadDocumental().getTipoDocumental() != null) {
			if (filtroUnidadDocumental.getUnidadDocumental().getTipoDocumental().getId() != 0) {
				JPQL.append(" AND u.tipoDocumental.id = :IDTIPODOCUMENTAL ");
				pamametros.put("IDTIPODOCUMENTAL",
						filtroUnidadDocumental.getUnidadDocumental().getTipoDocumental().getId());
			}
		}
		if (filtroUnidadDocumental.getUnidadDocumental().getContenedor() != null) {
			if (filtroUnidadDocumental.getUnidadDocumental().getContenedor().getId() != 0) {
				JPQL.append(" AND u.contenedor.id = :IDCONTENEDOR ");
				pamametros.put("IDCONTENEDOR", filtroUnidadDocumental.getUnidadDocumental().getContenedor().getId());
			}
		}
		if (filtroUnidadDocumental.getUnidadDocumental().getSociedadArea().getSociedad() != null) {
			if (filtroUnidadDocumental.getUnidadDocumental().getSociedadArea().getSociedad().getId() != 0) {
				JPQL.append(" AND sa.sociedad.id = :IDSOCIEDAD ");
				pamametros.put("IDSOCIEDAD",
						filtroUnidadDocumental.getUnidadDocumental().getSociedadArea().getSociedad().getId());
			}
		}
		if (filtroUnidadDocumental.getUnidadDocumental().getSociedadArea().getArea() != null) {
			if (filtroUnidadDocumental.getUnidadDocumental().getSociedadArea().getArea().getId() != 0) {
				JPQL.append(" AND sa.area.id = :IDAREA ");
				pamametros.put("IDAREA",
						filtroUnidadDocumental.getUnidadDocumental().getSociedadArea().getArea().getId());
			}
		}
		if (filtroUnidadDocumental.getUnidadDocumental().getCaja() != null) {
			if (filtroUnidadDocumental.getUnidadDocumental().getCaja().getId() != 0) {
				JPQL.append(" AND u.caja.id = :CAJA ");
				pamametros.put("CAJA",
						filtroUnidadDocumental.getUnidadDocumental().getCaja().getId());
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
	
	@Override
	public UnidadDocumentalTB buscarUnidadDocumentalPorId(long idUnidadDocumental) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder(
				"SELECT u FROM UnidadDocumentalTB u " + " INNER JOIN u.sociedadArea sa " + " WHERE 1 = 1 ");
		// WHERE
		JPQL.append(" AND u.id = :ID ");
		pamameters.put("ID", idUnidadDocumental);

		// END QUERY

		TypedQuery<UnidadDocumentalTB> query = em.createQuery(JPQL.toString(), UnidadDocumentalTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getSingleResult();
	}

	@Override
	public List<UnidadDocumentalTB> RequestConsultarUnidadDocumentalPorCaja(Long idCaja) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder(
				"SELECT u FROM UnidadDocumentalTB u " + " INNER JOIN u.sociedadArea sa " + " WHERE 1 = 1 ");
		// WHERE
		JPQL.append(" AND u.caja.id = :CAJA ");
		pamameters.put("CAJA", idCaja);

		// Q. Order By
		JPQL.append(" ORDER BY u.id");
		// END QUERY

		TypedQuery<UnidadDocumentalTB> query = em.createQuery(JPQL.toString(), UnidadDocumentalTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}

	@Override
	public List<UnidadDocumentalTB> consultarUnidadDocumentalList(RequestConsultarListaUdDTO request) {
		
		// PARAMETROS
		Map<String, Object> pamametros = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder(
				"SELECT u FROM UnidadDocumentalTB u " + " INNER JOIN u.sociedadArea sa INNER JOIN u.caja ca " + " WHERE 1 = 1 ");
		// WHERE
		if (request.getIdCliente() != null) {
			if (request.getIdCliente() != 0) {
				JPQL.append(" AND ca.cliente.id = :IDCLIENTE ");
				pamametros.put("IDCLIENTE",
						request.getIdCliente());
			}
		}
		
		if (request.getIdSociedad() != null) {
			if (request.getIdSociedad() != 0) {
				JPQL.append(" AND sa.sociedad.id = :IDSOCIEDAD ");
				pamametros.put("IDSOCIEDAD",
						request.getIdSociedad());
			}
		}
		
		if (request.getIdCaja() != null) {
			if (request.getIdCaja() != 0) {
				JPQL.append(" AND u.caja.id = :IDCAJA ");
				pamametros.put("IDCAJA",
						request.getIdCaja());
			}
		}
		
		if (request.getIdUnidadDocumental() != null) {
			if (request.getIdUnidadDocumental() != 0) {
				JPQL.append(" AND u.id = :ID ");
				pamametros.put("ID",
						request.getIdUnidadDocumental());
			}
		}
		
		if (request.getIdTipoUD() != null) {
			if (request.getIdTipoUD() != 0) {
				JPQL.append(" AND u.tipoDocumental.id = :IDTIPODOCUMENTAL ");
				pamametros.put("IDTIPODOCUMENTAL",
						request.getIdTipoUD());
			}
		}
		
		if (request.getIdArea() != null) {
			if (request.getIdArea() != 0) {
				JPQL.append(" AND sa.area.id = :IDAREA ");
				pamametros.put("IDAREA",
						request.getIdArea());
			}
		}
		
		if (request.getIdContenedor() != null) {
			if (request.getIdContenedor() != 0) {
				JPQL.append(" AND u.contenedor.id = :IDCONTENEDOR ");
				pamametros.put("IDCONTENEDOR",
						request.getIdContenedor());
			}
		}

		// Q. Order By
		JPQL.append(" ORDER BY u.id DESC");
		// END QUERY

		

		TypedQuery<UnidadDocumentalTB> query = em.createQuery(JPQL.toString(), UnidadDocumentalTB.class);
		pamametros.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();

		
	}

	@Override
	public List<UnidadDocumentalTB> obtenerArchivos(RequestConsultarArchivoUdDTO request) {
		// PARAMETROS
				Map<String, Object> pamametros = new HashMap<>();

				// QUERY
				StringBuilder JPQL = new StringBuilder(
						"SELECT u FROM UnidadDocumentalTB u " + " INNER JOIN u.sociedadArea sa INNER JOIN u.caja ca " + " WHERE 1 = 1 ");
				// WHERE
				if (request.getIdCliente() != null) {
					if (request.getIdCliente() != 0) {
						JPQL.append(" AND ca.cliente.id = :IDCLIENTE ");
						pamametros.put("IDCLIENTE",
								request.getIdCliente());
					}
				}
				
				if (request.getIdSociedad() != null) {
					if (request.getIdSociedad() != 0) {
						JPQL.append(" AND sa.sociedad.id = :IDSOCIEDAD ");
						pamametros.put("IDSOCIEDAD",
								request.getIdSociedad());
					}
				}
				
				if (request.getIdCaja() != null) {
					if (request.getIdCaja() != 0) {
						JPQL.append(" AND u.caja.id = :IDCAJA ");
						pamametros.put("IDCAJA",
								request.getIdCaja());
					}
				}
				
				if (request.getIdUnidadDocumental() != null) {
					if (request.getIdUnidadDocumental() != 0) {
						JPQL.append(" AND u.id = :ID ");
						pamametros.put("ID",
								request.getIdUnidadDocumental());
					}
				}
				
				if (request.getIdTipoUD() != null) {
					if (request.getIdTipoUD() != 0) {
						JPQL.append(" AND u.tipoDocumental.id = :IDTIPODOCUMENTAL ");
						pamametros.put("IDTIPODOCUMENTAL",
								request.getIdTipoUD());
					}
				}
				
				if (request.getIdArea() != null) {
					if (request.getIdArea() != 0) {
						JPQL.append(" AND sa.area.id = :IDAREA ");
						pamametros.put("IDAREA",
								request.getIdArea());
					}
				}
				
				if (request.getIdContenedor() != null) {
					if (request.getIdContenedor() != 0) {
						JPQL.append(" AND u.contenedor.id = :IDCONTENEDOR ");
						pamametros.put("IDCONTENEDOR",
								request.getIdContenedor());
					}
				}
				if (StringUtils.isNotBlank(request.getFiltroBusqueda())) {
					JPQL.append(" AND UPPER(u.nombreArchivos) LIKE UPPER(:NOMBRE) ");
					pamametros.put("NOMBRE", ConstantesValidaciones.COMODIN_BD
							+ request.getFiltroBusqueda() + ConstantesValidaciones.COMODIN_BD);
				}

				// Q. Order By
				JPQL.append(" ORDER BY u.id DESC");
				// END QUERY

				

				TypedQuery<UnidadDocumentalTB> query = em.createQuery(JPQL.toString(), UnidadDocumentalTB.class);
				pamametros.forEach((k, v) -> query.setParameter(k, v));

				return query.getResultList();
	}

	@Override
	public ResponseConsultarDTO<UnidadDocumentalTB> consultarUnidadDocumentalRecepcion(
			RequestConsultarUDRecepcionDTO request, CajaTB cajaTB) {
		
		ResponseConsultarDTO<UnidadDocumentalTB> response = new ResponseConsultarDTO<>();

		// PARAMETROS
		Map<String, Object> pamametros = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder(
				"SELECT u FROM UnidadDocumentalTB u " + " INNER JOIN u.caja c " + " WHERE 1 = 1 ");
		// WHERE
		JPQL.append(" AND c.cliente.id = :IDCLIENTE ");
				pamametros.put("IDCLIENTE",request.getCliente().getId());
		
		
		JPQL.append(" AND u.caja.id = :IDCAJA ");
		pamametros.put("IDCAJA",cajaTB.getId());
		

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

		query.setFirstResult(request.getRegistroInicial());
		query.setMaxResults(request.getCantidadRegistro());
		List<UnidadDocumentalTB> listaUnidadDocumental = query.getResultList();

		response.setResultado(listaUnidadDocumental);

		return response;
	}

	@Override
	public List<UnidadDocumentalTB> consultarUnidadDocumentalRecepcionPdf(RequestConsultarUDRecepcionDTO request,
			CajaTB cajaTB) {
		// PARAMETROS
				Map<String, Object> pamameters = new HashMap<>();

				
				// QUERY
				StringBuilder JPQL = new StringBuilder(
						"SELECT u FROM UnidadDocumentalTB u " + " INNER JOIN u.caja c " + " WHERE 1 = 1 ");
				// WHERE
				JPQL.append(" AND c.cliente.id = :IDCLIENTE ");
				pamameters.put("IDCLIENTE",request.getCliente().getId());
				
				
				JPQL.append(" AND u.caja.id = :IDCAJA ");
				pamameters.put("IDCAJA",cajaTB.getId());
				
				JPQL.append(" AND u.recepcionAprobada = :APROBADA ");
				pamameters.put("APROBADA",true);

				// Q. Order By
				JPQL.append(" ORDER BY u.id");
				// END QUERY

				TypedQuery<UnidadDocumentalTB> query = em.createQuery(JPQL.toString(), UnidadDocumentalTB.class);
				pamameters.forEach((k, v) -> query.setParameter(k, v));

				return query.getResultList();
	}

}
