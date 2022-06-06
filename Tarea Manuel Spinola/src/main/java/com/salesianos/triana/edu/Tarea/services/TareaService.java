package com.salesianos.triana.edu.Tarea.services;

import com.salesianos.triana.edu.Tarea.user.model.User;
import com.salesianos.triana.edu.Tarea.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TareaService {

    @Autowired
    private final UserRepository repository;

    public User addUser(Long id){
        return repository.findById(id).get();
    }

}
