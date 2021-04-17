package com.project.cafe.CentralUsuarios.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.project.cafe.CentralUsuarios.model.BaseEntidadTB;

public abstract class AbstractDao<T extends BaseEntidadTB> { 

	private Class<T> clazz;

	@PersistenceContext
	EntityManager entityManager;

	public final void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	public T findOne(long id) {
		return this.entityManager.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return this.entityManager.createQuery("from " + clazz.getName()).getResultList();
	}

	public void create(T entity) {
		entity.setFechaCreacion(new Date());
		entity.setUsuarioCreacion("SYSTEM");
		entity.setFechaActualizacion(new Date());
		entity.setUsuarioActualizacion("SYSTEM");
		this.entityManager.persist(entity);
	}

	public T update(T entity) {
		entity.setFechaActualizacion(new Date());
		entity.setUsuarioActualizacion("SYSTEM");
		return this.entityManager.merge(entity);
	}

	public void deleteById(long entityId) {
		T entity = findOne(entityId);
		this.entityManager.remove(entity);
	}

	public void flushCommitEM() {
		this.entityManager.flush();
	}
}