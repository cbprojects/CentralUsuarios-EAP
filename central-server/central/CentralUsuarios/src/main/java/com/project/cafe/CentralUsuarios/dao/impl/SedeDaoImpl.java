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
import com.project.cafe.CentralUsuarios.dao.ISedeDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarMasivoDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.SedeTB;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;

@Repository
public class SedeDaoImpl extends AbstractDao<SedeTB> implements ISedeDao {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    @Override
    public SedeTB crearSede(SedeTB Sede) {
        super.create(Sede);
        return Sede;
    }

    @Override
    public SedeTB modificarSede(SedeTB Sede) {
        super.update(Sede);
        return Sede;
    }

    @Override
    public List<SedeTB> buscarSedePorCodigo(String nombre) {
        // PARAMETROS
        Map<String, Object> pamameters = new HashMap<>();

        // QUERY
        StringBuilder JPQL = new StringBuilder("SELECT r FROM SedeTB r WHERE 1 = 1 ");
        // WHERE
        if (!StringUtils.isBlank(nombre)) {
            JPQL.append(" AND UPPER(r.nombre) =:NOMBRE ");
            pamameters.put("NOMBRE", nombre.toUpperCase());
        }
        // Q. Order By
        JPQL.append(" ORDER BY r.id");
        // END QUERY

        TypedQuery<SedeTB> query = em.createQuery(JPQL.toString(), SedeTB.class);
        pamameters.forEach((k, v) -> query.setParameter(k, v));

        return query.getResultList();
    }

    @Override
    public SedeTB buscarSedePorId(long id) {
        // PARAMETROS
        Map<String, Object> pamameters = new HashMap<>();

        // QUERY
        StringBuilder JPQL = new StringBuilder("SELECT r FROM SedeTB r WHERE r.id =:ID ");
        pamameters.put("ID", id);
        // END QUERY

        TypedQuery<SedeTB> query = em.createQuery(JPQL.toString(), SedeTB.class);
        pamameters.forEach((k, v) -> query.setParameter(k, v));

        return query.getSingleResult();
    }


    @Override
    public ResponseConsultarDTO<SedeTB> consultarSedeFiltros(RequestConsultarMasivoDTO request) {

        ResponseConsultarDTO<SedeTB> response = new ResponseConsultarDTO<>();

        // PARAMETROS
        Map<String, Object> pamametros = new HashMap<>();

        // QUERY
        StringBuilder JPQL = new StringBuilder("SELECT r FROM SedeTB r WHERE 1 = 1 ");
        // WHERE
        if (StringUtils.isNotBlank(request.getMasivo().getNombre1())) {
            JPQL.append(" AND UPPER(r.nombre) LIKE UPPER(:NOMBRE) ");
            pamametros.put("NOMBRE", ConstantesValidaciones.COMODIN_BD + request.getMasivo().getNombre1()
                    + ConstantesValidaciones.COMODIN_BD);
        }

        if (StringUtils.isNotBlank(request.getMasivo().getNombre2())) {
            JPQL.append(" AND UPPER(r.nombre10) LIKE UPPER(:NOMBRE10) ");
            pamametros.put("NOMBRE10", ConstantesValidaciones.COMODIN_BD + request.getMasivo().getNombre2()
                    + ConstantesValidaciones.COMODIN_BD);
        }

        if (StringUtils.isNotBlank(request.getMasivo().getNombre3())) {
            JPQL.append(" AND UPPER(r.address) LIKE UPPER(:ADDRESS) ");
            pamametros.put("ADDRESS", ConstantesValidaciones.COMODIN_BD + request.getMasivo().getNombre3()
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

        TypedQuery<SedeTB> query = em.createQuery(JPQL.toString(), SedeTB.class);
        pamametros.forEach((k, v) -> query.setParameter(k, v));

        query.setFirstResult(request.getRegistroInicial());
        query.setMaxResults(request.getCantidadRegistro());
        List<SedeTB> listaSede = query.getResultList();

        response.setResultado(listaSede);

        return response;
    }

    @Override
    public List<SedeTB> consultarSedeActiva() {

        // QUERY
        StringBuilder JPQL = new StringBuilder("SELECT s FROM SedeTB s WHERE s.estado = 1 ");

        // Q. Order By
        JPQL.append(" ORDER BY s.id");
        // END QUERY

        TypedQuery<SedeTB> query = em.createQuery(JPQL.toString(), SedeTB.class);

        return query.getResultList();
    }
}
