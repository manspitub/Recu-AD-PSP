package com.salesianos.triana.edu.Tarea.services;

import com.salesianos.triana.edu.Tarea.dto.comentario.ComentarioDtoConverter;
import com.salesianos.triana.edu.Tarea.dto.comentario.GetComentarioDto;
import com.salesianos.triana.edu.Tarea.dto.tarea.GetTareaDto;
import com.salesianos.triana.edu.Tarea.model.Comentario;
import com.salesianos.triana.edu.Tarea.model.Tarea;
import com.salesianos.triana.edu.Tarea.repo.ComentarioRepository;
import com.salesianos.triana.edu.Tarea.user.model.User;
import com.salesianos.triana.edu.Tarea.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    @Autowired
    private final UserRepository repository;

    private final ComentarioDtoConverter dtoConverter;

    public User addUser(Long id){
        return repository.findById(id).get();
    }

    public List<GetComentarioDto> listaTareaToListaGetTareaDto(List<Comentario> comentarios){

        List<GetComentarioDto> listaGetComentarioDto = new ArrayList<>();

        comentarios.stream().forEach(i -> {
            listaGetComentarioDto.add(dtoConverter.comentarioToGetComentarioDto(i));
        });

        return listaGetComentarioDto;
    }


}
