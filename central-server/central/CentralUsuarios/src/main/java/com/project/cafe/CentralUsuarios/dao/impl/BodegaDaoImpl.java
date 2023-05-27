package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.project.cafe.CentralUsuarios.dao.IBloqueDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarBodegasDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;
import org.apache.commons.lang3.StringUtils;
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
    public ResponseConsultarDTO<BodegaTB> consultarBodegaFiltros(RequestConsultarBodegasDTO filtroBodega) {

        ResponseConsultarDTO<BodegaTB> response = new ResponseConsultarDTO<>();

        // PARAMETROS
        Map<String, Object> pamametros = new HashMap<>();

        // QUERY
        StringBuilder JPQL = new StringBuilder("SELECT b FROM BodegaTB b " + " WHERE 1 = 1 ");
        // WHERE
        if (StringUtils.isNotBlank(filtroBodega.getCodigo())) {
            JPQL.append(" AND UPPER(b.codigo) LIKE UPPER(:CODIGO) ");
            pamametros.put("CODIGO", ConstantesValidaciones.COMODIN_BD + filtroBodega.getCodigo() + ConstantesValidaciones.COMODIN_BD);
        }

        if (StringUtils.isNotBlank(filtroBodega.getNombre())) {
            JPQL.append(" AND UPPER(b.nombre) LIKE UPPER(:NOMBRE) ");
            pamametros.put("NOMBRE", ConstantesValidaciones.COMODIN_BD + filtroBodega.getNombre() + ConstantesValidaciones.COMODIN_BD);
        }

        if (StringUtils.isNotBlank(filtroBodega.getNombre10())) {
            JPQL.append(" AND UPPER(b.nombre10) LIKE UPPER(:NOMBRE10) ");
            pamametros.put("NOMBRE10", ConstantesValidaciones.COMODIN_BD + filtroBodega.getNombre10() + ConstantesValidaciones.COMODIN_BD);
        }

        if (filtroBodega.getIdSede() != 0) {
            JPQL.append(" AND b.sede.id = :IDSEDE ");
            pamametros.put("IDSEDE", filtroBodega.getIdSede());
        }

        String COUNT = "SELECT COUNT(b) " + JPQL.toString().substring(JPQL.toString().indexOf("FROM"));

        // Q. Order By
        JPQL.append(" ORDER BY b.id DESC");
        // END QUERY

        // QUERY COUNT
        TypedQuery<Long> queryCount = em.createQuery(COUNT, Long.class);
        pamametros.forEach((k, v) -> queryCount.setParameter(k, v));
        response.setRegistrosTotales(queryCount.getSingleResult());

        TypedQuery<BodegaTB> query = em.createQuery(JPQL.toString(), BodegaTB.class);
        pamametros.forEach((k, v) -> query.setParameter(k, v));

        query.setFirstResult(filtroBodega.getRegistroInicial());
        query.setMaxResults(filtroBodega.getCantidadRegistro());
        List<BodegaTB> listaBodegas = query.getResultList();

        response.setResultado(listaBodegas);

        return response;
    }

    @Override
    public BodegaTB crearBodega(BodegaTB bodega) {
        super.create(bodega);
        return bodega;
    }

    @Override
    public List<BodegaTB> buscarBodegaPorCodigo(String codigo) {
        // PARAMETROS
        Map<String, Object> pamameters = new HashMap<>();

        // QUERY
        StringBuilder JPQL = new StringBuilder("SELECT b FROM BodegaTB b WHERE 1 = 1 ");
        // WHERE
        if (!StringUtils.isBlank(codigo)) {
            JPQL.append("AND b.codigo = :CODIGO ");
            pamameters.put("CODIGO", codigo);
        }
        // Q. Order By
        JPQL.append(" ORDER BY b.id");
        // END QUERY

        TypedQuery<BodegaTB> query = em.createQuery(JPQL.toString(), BodegaTB.class);
        pamameters.forEach((k, v) -> query.setParameter(k, v));

        return query.getResultList();
    }

    @Override
    public BodegaTB modificarBodega(BodegaTB bodega) {
        super.update(bodega);
        return bodega;
    }

}
