package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.project.cafe.CentralUsuarios.dao.AbstractDao;
import com.project.cafe.CentralUsuarios.dao.IPerfilDao;
import com.project.cafe.CentralUsuarios.model.PerfilTB;
import com.project.cafe.CentralUsuarios.model.UsuarioAutorTB;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;

@Repository
public class PerfilDaoImpl extends AbstractDao<PerfilTB> implements IPerfilDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	

	@Override
	public PerfilTB crearPerfil(PerfilTB perfilAutor) {
		super.create(perfilAutor);
		return perfilAutor;
	}

	@Override
	public PerfilTB modificarPerfil(PerfilTB perfilAutor) {
		super.update(perfilAutor);
		return perfilAutor;
	}
	
	

}
