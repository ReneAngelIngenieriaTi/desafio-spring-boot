package com.previred.desafioGestionTareas.persistence.Impl;

import com.previred.desafioGestionTareas.entities.TareaEstado;
import com.previred.desafioGestionTareas.persistence.TareaEstadoDAO;
import com.previred.desafioGestionTareas.repositories.TareaEstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TareaEstadoDaoImpl implements TareaEstadoDAO
{
    @Autowired
    private TareaEstadoRepository tareaEstadoRepository;

    @Override
    public List<TareaEstado> findAll() {
        return tareaEstadoRepository.findAll();
    }
}
