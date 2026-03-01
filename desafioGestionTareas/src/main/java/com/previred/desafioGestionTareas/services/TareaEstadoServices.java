package com.previred.desafioGestionTareas.services;

import com.previred.desafioGestionTareas.dtos.TareaDTO;
import com.previred.desafioGestionTareas.dtos.TareaEstadoDTO;

import java.util.List;

public interface TareaEstadoServices {

    List<TareaEstadoDTO> findAll();
}
