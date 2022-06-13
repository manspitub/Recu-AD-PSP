package com.salesianos.triana.edu.Tarea.services;

import com.salesianos.triana.edu.Tarea.dto.tarea.GetTareaDto;
import com.salesianos.triana.edu.Tarea.dto.tarea.TareaDtoConverter;
import com.salesianos.triana.edu.Tarea.model.Tarea;
import com.salesianos.triana.edu.Tarea.user.model.User;
import com.salesianos.triana.edu.Tarea.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TareaService {

    @Autowired
    private final UserRepository repository;

    private final TareaDtoConverter dtoConverter;

    public User addUser(Long id){
        return repository.findById(id).get();
    }

    public List<GetTareaDto> listaTareaToListaGetTareaDto(List<Tarea> tareas){

        List<GetTareaDto> listaGetInmoDto = new ArrayList<>();

        tareas.stream().forEach(i -> {
            listaGetInmoDto.add(dtoConverter.tareaToGetTareaDto(i));
        });

        return listaGetInmoDto;
    }

}
