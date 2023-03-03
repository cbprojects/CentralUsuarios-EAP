package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.EstanteTB;

public interface IEstanteDao {

    /**
     * @param idCuerpo
     * @return
     */
    List<EstanteTB> buscarEstantesActivosPorCuerpo(Long idCuerpo);

    void bulkEstantes(List<EstanteTB> listaEstantes);
}
