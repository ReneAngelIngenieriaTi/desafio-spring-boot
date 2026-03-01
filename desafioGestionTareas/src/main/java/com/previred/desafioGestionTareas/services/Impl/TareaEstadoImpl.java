package com.previred.desafioGestionTareas.services.Impl;

import com.previred.desafioGestionTareas.configs.ModelMapperConfig;
import com.previred.desafioGestionTareas.dtos.TareaEstadoDTO;
import com.previred.desafioGestionTareas.entities.TareaEstado;
import com.previred.desafioGestionTareas.persistence.TareaEstadoDAO;
import com.previred.desafioGestionTareas.services.TareaEstadoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TareaEstadoImpl implements TareaEstadoServices {

    @Autowired
    private TareaEstadoDAO tareaEstadoDAO;

    @Autowired
    private ModelMapperConfig modelMapper;

    @Override
    public List<TareaEstadoDTO> findAll() {
        List<TareaEstado> tareaEstados = tareaEstadoDAO.findAll();

        return tareaEstados.stream()
                .map(tareaEstado -> modelMapper.modelMapper().map(tareaEstado, TareaEstadoDTO.class))
                .collect(Collectors.toList());
    }
}
