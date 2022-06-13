package com.salesianos.triana.edu.Tarea.repo;

import com.salesianos.triana.edu.Tarea.dto.comentario.GetComentarioDto;
import com.salesianos.triana.edu.Tarea.model.Comentario;
import com.salesianos.triana.edu.Tarea.model.Tarea;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    Comentario findByTarea(Tarea tarea);

    @EntityGraph("grafo-tarea-usuario-comentario")
    List<Comentario> findAllByTarea(Tarea tarea);

}
