package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.project.cafe.CentralUsuarios.dao.AbstractDao;
import com.project.cafe.CentralUsuarios.dao.IRolDao;
import com.project.cafe.CentralUsuarios.model.RolTB;

@Repository
public class RolDaoImpl extends AbstractDao<RolTB> implements IRolDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public RolTB crearRol(RolTB rol) {
		super.create(rol);
		return rol;
	}

	@Override
	public RolTB modificarRol(RolTB rol) {
		super.update(rol);
		return rol;
	}

}
