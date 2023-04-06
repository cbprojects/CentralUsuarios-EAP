package com.project.cafe.CentralUsuarios.service.impl;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;


import com.project.cafe.CentralUsuarios.enums.ECodigosBloque;
import com.project.cafe.CentralUsuarios.enums.EEstado;
import com.project.cafe.CentralUsuarios.model.BodegaTB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IBloqueDao;
import com.project.cafe.CentralUsuarios.model.BloqueTB;
import com.project.cafe.CentralUsuarios.service.IBloqueService;

@Service
public class BloqueServiceImpl implements IBloqueService {

    @Autowired
    private IBloqueDao bloqueDao;

    @Override
    public List<BloqueTB> buscarBloquesActivosPorBodega(Long idBodega) {
        return bloqueDao.buscarBloquesActivosPorBodega(idBodega);
    }

    @Override
    public List<BloqueTB> buildBloques(int cantidadBloques, String usuario, BodegaTB bodegaTB) {
        List<BloqueTB> listaBloques = new ArrayList<>(EnumSet.allOf(ECodigosBloque.class)).subList(0, cantidadBloques)
                .stream().map(eCodigo -> {
                    BloqueTB bloqueTB = buildBloque(usuario, usuario, eCodigo.getNombre(), bodegaTB);
                    return bloqueTB;
                }).collect(Collectors.toList());
        return listaBloques;
    }

    @Override
    public void bulkBloque(List<BloqueTB> listaBlioques) {
        bloqueDao.bulkBloque(listaBlioques);
    }

    private BloqueTB buildBloque(String usuarioCreacion, String usuarioAct, String codigo, BodegaTB bodega) {
        BloqueTB bloqueTB = new BloqueTB();
        bloqueTB.setEstado((short) EEstado.ACTIVO.ordinal());
        bloqueTB.setUsuarioCreacion(usuarioCreacion);
        bloqueTB.setUsuarioActualizacion(usuarioAct);
        bloqueTB.setCodigo(codigo);
        bloqueTB.setBodega(bodega);
        return bloqueTB;
    }

}
