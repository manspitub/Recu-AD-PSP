package com.salesianos.triana.edu.Tarea.repo;

import com.salesianos.triana.edu.Tarea.model.Tarea;
import com.salesianos.triana.edu.Tarea.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {

    List<Tarea> findByUser(User user);

}
