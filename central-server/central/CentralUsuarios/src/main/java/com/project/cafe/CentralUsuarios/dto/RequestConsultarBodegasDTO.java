package com.project.cafe.CentralUsuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestConsultarBodegasDTO implements Serializable {

    private static final long serialVersionUID = 6613459049077915901L;

    private String codigo;
    private String nombre;
    private String nombre10;
    private long idSede;
    private int registroInicial;
    private int cantidadRegistro;


}