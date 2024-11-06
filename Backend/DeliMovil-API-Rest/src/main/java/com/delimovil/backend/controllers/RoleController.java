package com.delimovil.backend.controllers;

import com.delimovil.backend.dto.RoleDTO;
import com.delimovil.backend.services.interfaces.IRoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> readAll(){
        return ResponseEntity.ok(this.roleService.readAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> readById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(this.roleService.readById(id));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> create(@Valid @RequestBody RoleDTO roleDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.roleService.save(roleDTO));
    }

    @PutMapping
    public ResponseEntity<RoleDTO> update(@Valid @RequestBody RoleDTO roleDTO){
        return ResponseEntity.status(HttpStatus.OK).body(this.roleService.update(roleDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        this.roleService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
