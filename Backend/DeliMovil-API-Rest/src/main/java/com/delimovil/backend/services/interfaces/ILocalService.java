package com.delimovil.backend.services.interfaces;

import com.delimovil.backend.dto.LocalCreateDTO;
import com.delimovil.backend.dto.LocalDTO;
import com.delimovil.backend.dto.LocalRequestDTO;
import com.delimovil.backend.dto.UserDTO;

import java.util.List;

public interface ILocalService {
    List<LocalDTO> findAll();
    LocalDTO findById(Integer id);
    LocalDTO save(LocalCreateDTO localDTO, UserDTO userDto);
    LocalDTO update(LocalRequestDTO localDTO, Integer id);
    void deleteById(Integer id);
}
