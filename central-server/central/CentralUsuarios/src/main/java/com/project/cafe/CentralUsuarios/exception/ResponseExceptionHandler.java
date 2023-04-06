package com.project.cafe.CentralUsuarios.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.cafe.CentralUsuarios.util.PropertiesUtil;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private final String ERROR_NO_DATOS_BD = "Entidad no encontrada para el QUERY";

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> manejarTodasExcepciones(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String mensaje = ex.getMessage().toUpperCase().contains(ERROR_NO_DATOS_BD.toUpperCase())
                ? PropertiesUtil.getProperty("musicroom.msg.validate.noResultBD")
                : ex.getMessage();
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), mensaje, request.getDescription(true));

        if (ex instanceof BadRequestException) {
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<Object>(exceptionResponse, status);
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public final ResponseEntity<Object> manejarModeloExcepciones(ModelNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}
