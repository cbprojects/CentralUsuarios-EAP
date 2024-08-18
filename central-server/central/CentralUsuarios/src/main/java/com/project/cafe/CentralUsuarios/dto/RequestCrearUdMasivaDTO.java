package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import java.util.List;

import com.project.cafe.CentralUsuarios.model.UnidadDocumentalTB;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCrearUdMasivaDTO implements Serializable {
	
	private static final long serialVersionUID = 6039746782364610925L;

	private List<UnidadDocumentalTB> lstUdMasiva;

}