package com.previred.desafioGestionTareas.persistence;

import com.previred.desafioGestionTareas.entities.Tarea;
import com.previred.desafioGestionTareas.entities.TareaEstado;

import java.util.List;

public interface TareaEstadoDAO {

    List<TareaEstado> findAll();
}
