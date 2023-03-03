package com.project.cafe.CentralUsuarios.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.project.cafe.CentralUsuarios.model.BaseEntidadTB;
import org.springframework.beans.factory.annotation.Value;

@Transactional
public abstract class AbstractDao<T extends BaseEntidadTB> {

    private Class<T> clazz;

    @PersistenceContext(unitName = "default")
    EntityManager entityManager;

    public final void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    public T findOne(long id) {
        return this.entityManager.find(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return this.entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    public void create(T entity) {
        entity.setFechaCreacion(new Date());
        entity.setFechaActualizacion(new Date());
        this.entityManager.persist(entity);
    }

    public T update(T entity) {
        entity.setFechaActualizacion(new Date());
        return this.entityManager.merge(entity);
    }

    public void deleteById(long entityId) {
        T entity = findOne(entityId);
        this.entityManager.remove(entity);
    }

    public void flushCommitEM() {
        this.entityManager.flush();
    }

    public void bulkSave(Collection<T> entities) {
        int i = 0;
        for (T t : entities) {
            create(t);
            i++;
            if (i % batchSize == 0) {
                // Flush a batch of inserts and release memory.
                entityManager.flush();
                entityManager.clear();
            }
        }
    }
}