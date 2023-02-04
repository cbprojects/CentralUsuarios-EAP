package com.project.cafe.test.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.cafe.CentralUsuarios.dao.IRolDao;
import com.project.cafe.CentralUsuarios.model.RolTB;
import com.project.cafe.CentralUsuarios.service.impl.RolServiceImpl;

class RolServiceImplTest {

	@Mock
	private IRolDao rolDAO;
	
	@InjectMocks
	private RolServiceImpl rolServiceImpl;
	
	private RolTB rolTB;
	
	private List<RolTB> lstRolTB;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		
		rolTB = new RolTB();
		rolTB.setId(1);
		rolTB.setCodigo("rolTB");
		
		lstRolTB=new ArrayList<>();
		lstRolTB.add(rolTB);
	}
	
	@Test
	void buscarRolPorCodigo() {
		when(rolDAO.buscarRolPorCodigo(null)).thenReturn(lstRolTB);
		assertNotNull(rolServiceImpl.buscarRolPorCodigo(null));
	}

}
