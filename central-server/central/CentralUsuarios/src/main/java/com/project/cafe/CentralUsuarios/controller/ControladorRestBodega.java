package com.project.cafe.CentralUsuarios.controller;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.*;
import com.project.cafe.CentralUsuarios.exception.BadRequestException;
import com.project.cafe.CentralUsuarios.util.PropertiesUtil;
import com.project.cafe.CentralUsuarios.util.Util;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.BodegaTB;
import com.project.cafe.CentralUsuarios.service.IBodegaService;

@RestController
@RequestMapping("/central/Bodega")
public class ControladorRestBodega {

    @Autowired
    private IBodegaService bodegaService;

    // CONSULTA

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/buscarBodegasActivasPorSede")
    public List<BodegaTB> buscarBodegasActivasPorSede(@RequestBody RequestConsultarBodegasActivasDTO request) {
        try {
            return bodegaService.buscarBodegasActivasPorSede(request.getIdSede());
        } catch (JSONException e) {
            throw new ModelNotFoundException(e.getMessage());
        }
    }

    // CONSULTA

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/consultarBodegaFiltros")
    public ResponseConsultarDTO<BodegaTB> consultarBodegaFiltros(@RequestBody RequestConsultarBodegasDTO request) {
        try {
            return bodegaService.consultarBodegaFiltros(request);
        } catch (JSONException e) {
            throw new ModelNotFoundException(e.getMessage());
        }
    }

    //CREATE

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/crearBodega")
    public void crearBodega(@RequestBody CrearBodegaDTO request) {
        try {
            List<String> errores = Util.validarCrearBodega(request);
            if (errores.isEmpty()) {
                bodegaService.crearBodega(request);
            } else {
                StringBuilder mensajeErrores = new StringBuilder();
                String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");

                for (String error : errores) {
                    mensajeErrores.append(error + "|");
                }

                throw new BadRequestException(erroresTitle + mensajeErrores);
            }

        } catch (JSONException e) {
            throw new ModelNotFoundException(e.getMessage());
        }
    }

}
