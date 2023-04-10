package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestAprobarRecepcionDTO implements Serializable {
	
	
	private static final long serialVersionUID = -4584837706384198959L;

	private Long idUD;

	private Boolean aprobacion;

}