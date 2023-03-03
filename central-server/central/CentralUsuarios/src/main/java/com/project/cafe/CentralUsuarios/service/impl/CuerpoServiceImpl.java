package com.project.cafe.CentralUsuarios.service.impl;

import java.util.ArrayList;
import java.util.List;


import com.project.cafe.CentralUsuarios.enums.EEstado;
import com.project.cafe.CentralUsuarios.model.BloqueTB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.ICuerpoDao;
import com.project.cafe.CentralUsuarios.model.CuerpoTB;
import com.project.cafe.CentralUsuarios.service.ICuerpoService;

@Service
public class CuerpoServiceImpl implements ICuerpoService {

    @Autowired
    private ICuerpoDao cuerpoDao;

    @Override
    public List<CuerpoTB> buscarCuerposActivosPorBloque(Long idBloque) {
        return cuerpoDao.buscarCuerposActivosPorBloque(idBloque);
    }

    @Override
    public List<CuerpoTB> buildCuerpos(int cantidadCuerposXBloque, String usuarioCreacion, String usuarioAct, BloqueTB bloque) {
        List<CuerpoTB> listaCuerpos = new ArrayList<>();
        // maximo de cantidadCuerposXBloque son 2
        for (int i = 0; i < cantidadCuerposXBloque; i++) {

            listaCuerpos.add(buildCuerpo(usuarioCreacion, usuarioAct, String.valueOf(i + 1), bloque));
        }
        return listaCuerpos;
    }

    @Override
    public void bulkCuerpo(List<CuerpoTB> listaCuerpo) {
        cuerpoDao.bulkCuerpo(listaCuerpo);
    }

    private CuerpoTB buildCuerpo(String usuarioCreacion, String usuarioAct, String codigo, BloqueTB bloque) {
        CuerpoTB cuerpoTB = new CuerpoTB();
        cuerpoTB.setEstado((short) EEstado.ACTIVO.ordinal());
        cuerpoTB.setUsuarioCreacion(usuarioCreacion);
        cuerpoTB.setUsuarioActualizacion(usuarioAct);
        cuerpoTB.setCodigo(codigo);
        cuerpoTB.setBloque(bloque);
        return cuerpoTB;
    }

}
