package com.delimovil.backend.services.interfaces;

import com.delimovil.backend.dto.UserDTO;

import java.util.List;

public interface IUserService {

    UserDTO save(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    List<UserDTO> readAll();
    UserDTO readById(Integer id);
    void delete(Integer id);
    boolean existsByUsername(String username);
}
