package com.previred.desafioGestionTareas.controller;

import com.previred.desafioGestionTareas.dtos.TareaDTO;
import com.previred.desafioGestionTareas.dtos.TareaEstadoDTO;
import com.previred.desafioGestionTareas.services.TareaEstadoServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/tareaEstados")
@Tag(name = "TareaEstado", description = "Controller para Tareas")
public class TareaEstadoController {

    //TODO: Implementar controlador para TareaEstado que devuela una lista de TareaEstadoDTO, utilizando el servicio TareaEstadoServices y siguiendo la misma estructura de los otros controladores.
    @Autowired
    private TareaEstadoServices tareaEstadoServices;


    @Operation(
            summary = "Buscar tareaEstados",
            description = "Retorna lista de tareaEstado o vacio sino no existiera ninguna",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Tareas eSTADO asociadas",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TareaDTO.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = " no se encontraron Tareas Estado asociadas")
            }
    )
    @GetMapping
    public ResponseEntity<List<TareaEstadoDTO>> findAll() {

        return new ResponseEntity<>(tareaEstadoServices.findAll(), HttpStatus.OK);
    }
}
