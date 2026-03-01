package com.previred.desafioGestionTareas.repositories;

import com.previred.desafioGestionTareas.entities.Tarea;
import com.previred.desafioGestionTareas.entities.TareaEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaEstadoRepository extends JpaRepository<TareaEstado, Long> {

        TareaEstado findByNameState(String nameState);

        List<TareaEstado> findAll();

}
