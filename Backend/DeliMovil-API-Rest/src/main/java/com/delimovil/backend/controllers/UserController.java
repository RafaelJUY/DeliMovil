package com.delimovil.backend.controllers;

import com.delimovil.backend.dto.UserDTO;
import com.delimovil.backend.services.interfaces.IUserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> readAll(){
        return ResponseEntity.ok(this.userService.readAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> readById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(this.userService.readById(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO){
       return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.save(userDTO));
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO userDTO){
        return ResponseEntity.ok(this.userService.update(userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        this.userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}