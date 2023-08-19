package com.project.cafe.CentralUsuarios.service.impl;

import java.util.ArrayList;
import java.util.List;


import com.project.cafe.CentralUsuarios.enums.EEstado;
import com.project.cafe.CentralUsuarios.model.EstanteTB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IEntrepanoDao;
import com.project.cafe.CentralUsuarios.model.EntrepanoTB;
import com.project.cafe.CentralUsuarios.service.IEntrepanoService;

@Service
public class EntrepanoServiceImpl implements IEntrepanoService {

    @Autowired
    private IEntrepanoDao entrepanoDao;

    @Override
    public List<EntrepanoTB> buscarEntrepanosActivosPorEstante(Long idEstante) {
        return entrepanoDao.buscarEntrepanosActivosPorEstante(idEstante);
    }

    @Override
    public void bulkEntrepanos(List<EntrepanoTB> listaEntrepanos) {
        entrepanoDao.bulkEntrepanos(listaEntrepanos);
    }

    @Override
    public List<EntrepanoTB> buildEntrepanos(int cantidadEntrepanoXEstante, String usuarioCreacion, String usuarioAct, EstanteTB estante) {
        List<EntrepanoTB> listaEntrepanos = new ArrayList<>();
        for (int i = 0; i < cantidadEntrepanoXEstante; i++) {
            listaEntrepanos.add(buildEntrepano(usuarioCreacion, String.valueOf(i + 1),usuarioAct,  estante));
        }
        return listaEntrepanos;
    }

    private EntrepanoTB buildEntrepano(String usuarioCreacion, String codigo, String usuarioAct, EstanteTB estante) {
        EntrepanoTB entrepanoTB = new EntrepanoTB();
        entrepanoTB.setEstado((short) EEstado.ACTIVO.ordinal());
        entrepanoTB.setUsuarioCreacion(usuarioCreacion);
        entrepanoTB.setUsuarioActualizacion(usuarioAct);
        entrepanoTB.setCodigo(codigo);
        entrepanoTB.setEstante(estante);
        return entrepanoTB;
    }

}
