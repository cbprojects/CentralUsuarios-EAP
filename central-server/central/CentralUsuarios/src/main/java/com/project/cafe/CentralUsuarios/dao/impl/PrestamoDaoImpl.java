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
import com.project.cafe.CentralUsuarios.dao.IPrestamoDao;
import com.project.cafe.CentralUsuarios.model.PrestamoTB;

@Repository
public class PrestamoDaoImpl extends AbstractDao<PrestamoTB> implements IPrestamoDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public PrestamoTB crearPrestamo(PrestamoTB prestamo) {
		super.create(prestamo);
		return prestamo;
	}

	@Override
	@Transactional
	public void eliminarPrestamo(Long idUd) {
		em.createQuery("delete from  PrestamoTB " 
                + " t where t.idUd =:IDUD ").setParameter("IDUD", idUd).executeUpdate();
	}
	

	@Override
	public List<PrestamoTB> buscarPrestamoVigente(Long idUd) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT r FROM PrestamoTB r WHERE 1 = 1 ");
		// WHERE
		JPQL.append(" AND r.idUd =:IDUD ");
		pamameters.put("IDUD", idUd);
		
		// Q. Order By
		JPQL.append(" ORDER BY r.id ");
		// END QUERY

		TypedQuery<PrestamoTB> query = em.createQuery(JPQL.toString(), PrestamoTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}

}
