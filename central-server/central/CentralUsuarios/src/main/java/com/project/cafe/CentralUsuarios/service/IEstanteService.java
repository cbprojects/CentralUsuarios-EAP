package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.CuerpoTB;
import com.project.cafe.CentralUsuarios.model.EstanteTB;

public interface IEstanteService {

    /**
     * @param idCuerpo
     * @return
     */
    List<EstanteTB> buscarEstantesActivosPorCuerpo(Long idCuerpo);

    void bulkEstantes(List<EstanteTB> listaEstantes);

    List<EstanteTB> buildEstantes(int cantidadEstantesXCuerpo, String usuarioCreacion, String usuarioAct, CuerpoTB cuerpo);
}
