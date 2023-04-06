package com.project.cafe.CentralUsuarios.service.impl;

import java.util.ArrayList;
import java.util.List;


import com.project.cafe.CentralUsuarios.enums.EEstado;
import com.project.cafe.CentralUsuarios.model.CuerpoTB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IEstanteDao;
import com.project.cafe.CentralUsuarios.model.EstanteTB;
import com.project.cafe.CentralUsuarios.service.IEstanteService;

@Service
public class EstanteServiceImpl implements IEstanteService {

    @Autowired
    private IEstanteDao estanteDao;

    @Override
    public List<EstanteTB> buscarEstantesActivosPorCuerpo(Long idCuerpo) {
        return estanteDao.buscarEstantesActivosPorCuerpo(idCuerpo);
    }

    @Override
    public void bulkEstantes(List<EstanteTB> listaEtantes) {
        estanteDao.bulkEstantes(listaEtantes);
    }

    @Override
    public List<EstanteTB> buildEstantes(int cantidadEstantesXCuerpo, String usuarioCreacion, String usuarioAct, CuerpoTB cuerpo) {
        List<EstanteTB> listaEstantes = new ArrayList<>();
        for (int i = 0; i < cantidadEstantesXCuerpo; i++) {

            listaEstantes.add(buildEstante(usuarioCreacion, usuarioAct, String.valueOf(i + 1), cuerpo));
        }
        return listaEstantes;
    }

    private EstanteTB buildEstante(String usuarioCreacion, String codigo, String usuarioAct, CuerpoTB cuerpo) {
        EstanteTB estanteTB = new EstanteTB();
        estanteTB.setEstado((short) EEstado.ACTIVO.ordinal());
        estanteTB.setUsuarioCreacion(usuarioCreacion);
        estanteTB.setUsuarioActualizacion(usuarioAct);
        estanteTB.setCodigo(codigo);
        estanteTB.setCuerpo(cuerpo);
        return estanteTB;
    }

}
