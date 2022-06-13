package com.salesianos.triana.edu.Tarea.controller;

import com.salesianos.triana.edu.Tarea.dto.comentario.ComentarioDtoConverter;
import com.salesianos.triana.edu.Tarea.dto.comentario.CreateComentarioDto;
import com.salesianos.triana.edu.Tarea.dto.comentario.GetComentarioDto;
import com.salesianos.triana.edu.Tarea.dto.tarea.CreateTareaDto;
import com.salesianos.triana.edu.Tarea.dto.tarea.GetTareaDto;
import com.salesianos.triana.edu.Tarea.dto.tarea.TareaDtoConverter;
import com.salesianos.triana.edu.Tarea.model.Comentario;
import com.salesianos.triana.edu.Tarea.model.Tarea;
import com.salesianos.triana.edu.Tarea.repo.ComentarioRepository;
import com.salesianos.triana.edu.Tarea.repo.TareaRepository;
import com.salesianos.triana.edu.Tarea.services.TareaService;
import com.salesianos.triana.edu.Tarea.user.model.User;
import com.salesianos.triana.edu.Tarea.user.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequiredArgsConstructor
public class TareaController {

    private final TareaRepository tareaRepository;
    private final ComentarioRepository comentarioRepository;
    private final TareaService tareaService;

    private final TareaDtoConverter dtoConverter;
    private final ComentarioDtoConverter comentarioDtoConverter;

    @GetMapping("all/tareas")
    public ResponseEntity<List<GetTareaDto>> getTareas(@AuthenticationPrincipal User currentUser){

        if (currentUser.getRole().equals(UserRole.ADMIN)){
            List<GetTareaDto> listaTareas = new ArrayList<>();

            tareaRepository.findAll().stream().forEach(t->{
                listaTareas.add(dtoConverter.tareaToGetTareaDto(t));
            });

            if (listaTareas.isEmpty())
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(listaTareas);
        } else {
            return ResponseEntity.status(FORBIDDEN).build();
        }


    }

    @GetMapping("tareas/mine")
    public ResponseEntity<List<GetTareaDto>> getTareasMine(@AuthenticationPrincipal User currentUser){



        List <Tarea> tareasUser = tareaRepository.findByUser(currentUser);

            List<GetTareaDto> tareasConvert = tareaService.listaTareaToListaGetTareaDto(tareasUser);

            if (tareasUser.isEmpty())
                return ResponseEntity.notFound().build();
            else return ResponseEntity.ok(tareasConvert);


        }

    @GetMapping("tarea/{id}")
    public ResponseEntity<GetTareaDto> getTarea(@PathVariable Long id, @AuthenticationPrincipal User currentUser){



        Optional<Tarea> tar = tareaRepository.findById(id);



        if (tar.isPresent()){
            if (currentUser.getRole().equals(UserRole.ADMIN) || tar.get().getUser().equals(currentUser)){
                return ResponseEntity.ok(dtoConverter.tareaToGetTareaDto(tar.get()));
            } else {
                return ResponseEntity.status(FORBIDDEN).build();
            }
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("tarea")
    public ResponseEntity<?> crearTarea(@Valid @RequestBody CreateTareaDto tareaACrear, @AuthenticationPrincipal User currentUser){


            Tarea tar = dtoConverter.createTareaDtoToTarea(tareaACrear);

            tar.setUser(tareaService.addUser(currentUser.getId()));

            tareaRepository.save(tar);
            return ResponseEntity.ok(dtoConverter.tareaToGetTareaDto(tar));

        }

    @PutMapping("tarea/{id}")
    public ResponseEntity<?> editTarea(@RequestBody CreateTareaDto tareaEdit, @PathVariable Long id, @AuthenticationPrincipal User currentUser){

        return tareaRepository.findById(id)
                .map(t-> {
                    t.setTitulo(tareaEdit.getTitulo());
                    t.setFechaInicio(tareaEdit.getFechaInicio());
                    t.setFechaFin(tareaEdit.getFechaFin());
                    if (currentUser.getRole().equals(UserRole.ADMIN) || t.getUser().equals(currentUser)) {
                        return tareaRepository.save(t);
                    } else {
                        return ResponseEntity.status(FORBIDDEN).build();
                    }
                })
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());



    }


    @DeleteMapping("tarea/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @AuthenticationPrincipal User currentUser){



        Optional<Tarea> tareaDelete = tareaRepository.findById(id);

        if (currentUser.getRole().equals(UserRole.ADMIN) || tareaDelete.get().getUser().equals(currentUser)){
            if (tareaDelete.isPresent()){
                if (currentUser.getRole().equals(UserRole.ADMIN)){
                    tareaRepository.deleteById(id);

                }
                else{
                    return ResponseEntity.status(FORBIDDEN).build();
                }
            }
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(FORBIDDEN).build();
        }

    }

    @GetMapping("tarea/{id}/comentario")
    public ResponseEntity<List<GetComentarioDto>> getComentsTarea(@AuthenticationPrincipal User currentUser, @PathVariable Long id){

        Optional<Tarea> tareaBuscar = tareaRepository.findById(id);

        if (currentUser.getRole().equals(UserRole.ADMIN) || tareaBuscar.get().getUser().equals(currentUser)){

            List<GetComentarioDto> listaComments = new ArrayList<>();

            comentarioRepository.findAll().stream().forEach(c->{
                if (c.getTarea().equals(tareaBuscar.get())){
                    listaComments.add(comentarioDtoConverter.comentarioToGetComentarioDto(c));
                }
            });

            if (listaComments.isEmpty())
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(listaComments);

        } else{
            return ResponseEntity.status(FORBIDDEN).build();
        }


    }

    @PostMapping("tarea/{id}/comentario")
    public ResponseEntity<?> addComment(@Valid @PathVariable Long id, @RequestBody CreateComentarioDto comentCrear, @AuthenticationPrincipal User currentUser){

        Optional<Tarea> tareaBuscar = tareaRepository.findById(id);

        Comentario com = comentarioDtoConverter.createComentarioDtoToComentario(comentCrear);
        com.setUser(currentUser);
        com.addTarea(tareaBuscar.orElseGet(null));
        comentarioRepository.save(com);

        return ResponseEntity.ok(comentarioDtoConverter.comentarioToGetComentarioDto(com));


    }

    @PutMapping("tarea/{idTarea}/comentario/{idComentario}")
    public ResponseEntity<GetComentarioDto> editComent( @Valid @PathVariable Long idTarea, @PathVariable Long idComentario, @RequestBody CreateComentarioDto comentEdit, @AuthenticationPrincipal User currentUser){

        Optional<Tarea> tareaBuscar = tareaRepository.findById(idTarea);
        Optional<Comentario> commentBuscar = comentarioRepository.findById(idComentario);


        if (currentUser.getRole().equals(UserRole.ADMIN) || commentBuscar.get().getUser().equals(currentUser)){
            if (tareaBuscar.get().getComentario().contains(commentBuscar.get()))
                commentBuscar.get().setTexto(comentEdit.getTexto());
            comentarioRepository.save(commentBuscar.get());
            return ResponseEntity.ok(comentarioDtoConverter.comentarioToGetComentarioDto(commentBuscar.get()));
        } else{
            return ResponseEntity.status(FORBIDDEN).build();
        }


    }

    @DeleteMapping("tarea/{idTarea}/comentario/{idComentario}")
    public ResponseEntity<?> deleteComent(@PathVariable Long idTarea, @PathVariable Long idComentario, @AuthenticationPrincipal User currentUser){

        Optional<Tarea> tareaBuscar = tareaRepository.findById(idTarea);
        Optional<Comentario> commentBuscar = comentarioRepository.findById(idComentario);


        if (currentUser.getRole().equals(UserRole.ADMIN) || commentBuscar.get().getUser().equals(currentUser)){
            if (tareaBuscar.get().getComentario().contains(commentBuscar.get()))
                comentarioRepository.delete(commentBuscar.get());
        } else{
            return ResponseEntity.status(FORBIDDEN).build();
        }

        return ResponseEntity.noContent().build();


    }



    }







