package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;

public class CrearBodegaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public long sedeId;
    public String nombreBodega;
    public String nombre10Bodega;
    public int cantidadBloques;
    public int cantidadCuerposXBloque;
    public int cantidadEstantesXCuerpo;
    public int cantidadEntrepanoXEstante;
    public String usuarioCreacion;
    public String codigoBodega;
    public String ownerNameBodega;
}
