package com.salesianos.triana.Monumentos.controller;

import com.salesianos.triana.Monumentos.model.Categoria;
import com.salesianos.triana.Monumentos.repo.CategoriaRepository;
import com.salesianos.triana.Monumentos.repo.MonumentoRepository;
import com.salesianos.triana.Monumentos.usuario.model.User;
import com.salesianos.triana.Monumentos.usuario.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;
    private final MonumentoRepository monumentoRepository;

    @GetMapping("categorias")
    public ResponseEntity<List<Categoria>> getCategorias(){
        List<Categoria> result = categoriaRepository.findAll();

        if (result.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }
    @GetMapping("categoria/{id}")
    public ResponseEntity<Categoria> getCategoria(@PathVariable Long id){

        return ResponseEntity.of(categoriaRepository.findById(id));


    }

    @PutMapping("categoria/{id}")
    public ResponseEntity<Categoria> edit(@RequestBody Categoria categoria, @AuthenticationPrincipal User currentUser, @PathVariable Long id){
        if (currentUser.getRole().equals(UserRole.ADMIN)){
            return categoriaRepository.findById(id)
                    .map(c -> {
                        c.setName(categoria.getName());
                        c.setUrl(categoria.getUrl());

                        return categoriaRepository.save(c);
                    })
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.status(FORBIDDEN).build();
        }



    }

    @PostMapping("categoria")
    public ResponseEntity<Categoria> create(@RequestBody Categoria categoria, @AuthenticationPrincipal User currentUser){
        if (currentUser.getRole().equals(UserRole.ADMIN)){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(categoriaRepository.save(categoria));
        } else {
            return ResponseEntity.status(FORBIDDEN).build();
        }

    }

    @DeleteMapping("categoria/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @AuthenticationPrincipal User currentUser){
        if (currentUser.getRole().equals(UserRole.ADMIN)){
            if (categoriaRepository.existsById(id))

                monumentoRepository.findAll().forEach(m ->{
                    if (m.getCategoria().equals(categoriaRepository.findById(id).get()))
                        m.setCategoria(null);
                });
            categoriaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.status(FORBIDDEN).build();
        }

    }

}
