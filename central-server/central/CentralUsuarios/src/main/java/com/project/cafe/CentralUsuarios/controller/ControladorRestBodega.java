package com.project.cafe.CentralUsuarios.controller;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.*;
import com.project.cafe.CentralUsuarios.exception.BadRequestException;
import com.project.cafe.CentralUsuarios.model.PerfilTB;
import com.project.cafe.CentralUsuarios.model.RolTB;
import com.project.cafe.CentralUsuarios.util.ConstantesTablasNombre;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;
import com.project.cafe.CentralUsuarios.util.PropertiesUtil;
import com.project.cafe.CentralUsuarios.util.Util;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BodegaTB> crearBodega(@RequestBody CrearBodegaDTO request) {
        try {
        	BodegaTB newBodega = new BodegaTB();
            List<String> errores = Util.validarCrearBodega(request);
            if (errores.isEmpty()) {
                // validar bodega unica
                if (validarBodegaUnicaCrear(request.codigoBodega)) {
                	newBodega=bodegaService.crearBodega(request);
                } else {
                    String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
                    String mensajeErrores = ConstantesValidaciones.MSG_BODEGA_REPETIDA;

                    throw new ModelNotFoundException(erroresTitle + mensajeErrores);
                }
            } else {
                StringBuilder mensajeErrores = new StringBuilder();
                String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");

                for (String error : errores) {
                    mensajeErrores.append(error + "|");
                }

                throw new ModelNotFoundException(erroresTitle + mensajeErrores);
            }
            return new ResponseEntity<BodegaTB>(newBodega, HttpStatus.OK);

        } catch (JSONException e) {
            throw new ModelNotFoundException(e.getMessage());
        }
    }

    // UPDATE

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/modificarBodega")
    public ResponseEntity<BodegaTB> modificarBodega(@RequestBody BodegaTB bodega) {
        try {
            BodegaTB newBodega = new BodegaTB();
            // validaciones de campos vacios o valores incorrectos
            List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_BODEGA_TB, bodega);
            if (errores.isEmpty()) {
                // validar bodega unica
                if (validarBodegaUnicaEditar(bodega.getCodigo(), bodega.getId())) {
                    newBodega = bodegaService.modificarBodega(bodega);
                } else {
                    String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
                    String mensajeErrores = ConstantesValidaciones.MSG_BODEGA_REPETIDA;

                    throw new ModelNotFoundException(erroresTitle + mensajeErrores);
                }
            } else {
                String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
                StringBuilder mensajeErrores = new StringBuilder();
                for (String error : errores) {
                    mensajeErrores.append(error + "|");
                }

                throw new ModelNotFoundException(erroresTitle + mensajeErrores);
            }
            return new ResponseEntity<BodegaTB>(newBodega, HttpStatus.OK);
        } catch (Exception e) {
            throw new ModelNotFoundException(e.getMessage());
        }

    }

    private boolean validarBodegaUnicaCrear(String codigo) {
        List<BodegaTB> listaBodegas = bodegaService.buscarBodegaPorCodigo(codigo);
        return listaBodegas == null || listaBodegas.isEmpty();
    }

    private boolean validarBodegaUnicaEditar(String codigo, long id) {
        List<BodegaTB> listaBodegas = bodegaService.buscarBodegaPorCodigo(codigo);
        if (listaBodegas == null || listaBodegas.isEmpty()) {
            return true;
        } else {
            if (listaBodegas.get(0).getId() == (id)) {
                return true;
            }
        }
        return false;
    }

}
