package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.project.cafe.CentralUsuarios.dao.IBloqueDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.cafe.CentralUsuarios.dao.AbstractDao;
import com.project.cafe.CentralUsuarios.dao.IBodegaDao;
import com.project.cafe.CentralUsuarios.model.BodegaTB;

@Repository
public class BodegaDaoImpl extends AbstractDao<BodegaTB> implements IBodegaDao {

    @Autowired
    private IBloqueDao bloqueDao;

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    @Override
    public List<BodegaTB> buscarBodegasActivasPorSede(Long idSede) {
        // PARAMETROS
        Map<String, Object> pamameters = new HashMap<>();

        // QUERY
        StringBuilder JPQL = new StringBuilder("SELECT b FROM BodegaTB b WHERE 1 = 1 ");
        // WHERE
        JPQL.append("AND b.sede.id = :IDSEDE ");
        pamameters.put("IDSEDE", idSede);

        JPQL.append("AND b.estado = 1");
        // Q. Order By
        JPQL.append(" ORDER BY b.id");
        // END QUERY

        TypedQuery<BodegaTB> query = em.createQuery(JPQL.toString(), BodegaTB.class);
        pamameters.forEach((k, v) -> query.setParameter(k, v));

        return query.getResultList();
    }

    @Override
    public void crearBodega(BodegaTB bodega) {
        super.create(bodega);
    }

}
