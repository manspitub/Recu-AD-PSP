package com.salesianos.triana.edu.Tarea.repo;

import com.salesianos.triana.edu.Tarea.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
