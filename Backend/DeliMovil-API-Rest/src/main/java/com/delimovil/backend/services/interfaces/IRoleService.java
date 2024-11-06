package com.delimovil.backend.services.interfaces;

import com.delimovil.backend.dto.RoleDTO;

import java.util.List;

public interface IRoleService {
    RoleDTO save(RoleDTO roleDTO);
    RoleDTO update(RoleDTO roleDTO);
    List<RoleDTO> readAll();
    RoleDTO readById(Integer id);
    void delete(Integer id);
}
