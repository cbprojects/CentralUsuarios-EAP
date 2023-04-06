package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;


import com.project.cafe.CentralUsuarios.dto.CrearBodegaDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarBodegasDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.enums.EEstado;
import com.project.cafe.CentralUsuarios.model.*;
import com.project.cafe.CentralUsuarios.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IBodegaDao;

@Service
public class BodegaServiceImpl implements IBodegaService {

    @Autowired
    private IBodegaDao bodegaDao;

    @Autowired
    private IBloqueService bloqueService;

    @Autowired
    private ICuerpoService cuerpoService;

    @Autowired
    private ISedeService sedeService;

    @Autowired
    private IEstanteService estanteService;

    @Autowired
    private IEntrepanoService entrepanoService;

    @Override
    public List<BodegaTB> buscarBodegasActivasPorSede(Long idSede) {
        return bodegaDao.buscarBodegasActivasPorSede(idSede);
    }

    @Override
    public ResponseConsultarDTO<BodegaTB> consultarBodegaFiltros(RequestConsultarBodegasDTO filtroBodega) {
        return bodegaDao.consultarBodegaFiltros(filtroBodega);
    }

    @Override
    public void crearBodega(CrearBodegaDTO crearBodegaDTO) {

        //CREAR BODEGA
        BodegaTB bodegaTB = buildBodega(crearBodegaDTO);
        bodegaDao.crearBodega(bodegaTB);

        //CREAR BLOQUES
        List<BloqueTB> listaBloques = bloqueService.buildBloques(crearBodegaDTO.cantidadBloques, crearBodegaDTO.usuarioCreacion,
                bodegaTB);
        bloqueService.bulkBloque(listaBloques);

        //CREAR CUERPOS
        listaBloques.forEach(bloque -> {
            List<CuerpoTB> listaCuerpos = cuerpoService.buildCuerpos(crearBodegaDTO.cantidadCuerposXBloque,
                    crearBodegaDTO.usuarioCreacion, crearBodegaDTO.usuarioCreacion, bloque);
            cuerpoService.bulkCuerpo(listaCuerpos);
            //CREAR ESTANTES
            listaCuerpos.forEach(cuerpo -> {
                List<EstanteTB> listaEstantes = estanteService.buildEstantes(crearBodegaDTO.cantidadEstantesXCuerpo, crearBodegaDTO.usuarioCreacion,
                        crearBodegaDTO.usuarioCreacion, cuerpo);
                estanteService.bulkEstantes(listaEstantes);
                //CREAR ENTREPANO
                listaEstantes.forEach(estante -> {
                    List<EntrepanoTB> listaEntrepanos = entrepanoService.buildEntrepanos(crearBodegaDTO.cantidadEstantesXCuerpo, crearBodegaDTO.usuarioCreacion,
                            crearBodegaDTO.usuarioCreacion, estante);
                    entrepanoService.bulkEntrepanos(listaEntrepanos);
                });
            });
        });

    }

    private BodegaTB buildBodega(CrearBodegaDTO crearBodegaDTO) {
        BodegaTB bodegaTB = new BodegaTB();
        bodegaTB.setEstado((short) EEstado.ACTIVO.ordinal());
        bodegaTB.setUsuarioCreacion(crearBodegaDTO.usuarioCreacion);
        bodegaTB.setUsuarioActualizacion(crearBodegaDTO.usuarioCreacion);
        bodegaTB.setCodigo(crearBodegaDTO.codigoBodega);
        bodegaTB.setNombre(crearBodegaDTO.nombreBodega);
        bodegaTB.setNombre10(crearBodegaDTO.nombre10Bodega);
        bodegaTB.setOwnerName(crearBodegaDTO.ownerNameBodega);
        bodegaTB.setSede(sedeService.buscarSedePorId(crearBodegaDTO.sedeId));
        return bodegaTB;
    }


}
