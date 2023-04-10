package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGenerarPdfDTO implements Serializable {
	
	private static final long serialVersionUID = 5448943775026449589L;
	public String codigo;
	public String mensaje;
	public ArchivoDTO archivo;
	
}
