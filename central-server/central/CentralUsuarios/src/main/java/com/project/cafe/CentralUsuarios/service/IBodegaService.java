package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.CrearBodegaDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarBodegasDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.BodegaTB;

import javax.transaction.Transactional;

public interface IBodegaService {

    /**
     * @param idSede
     * @return
     */
    List<BodegaTB> buscarBodegasActivasPorSede(Long idSede);

    ResponseConsultarDTO<BodegaTB> consultarBodegaFiltros(RequestConsultarBodegasDTO filtroBodega);

    /**
     * servicio que crea la bodega y sus respectivos bloques, cuerpos,estantes y entrepanos
     *
     * @param crearBodegaDTO
     * @return
     */
    void crearBodega(CrearBodegaDTO crearBodegaDTO);

    List<BodegaTB> buscarBodegaPorCodigo(String codigo);

    @Transactional
    BodegaTB modificarBodega(BodegaTB bodega);
}
