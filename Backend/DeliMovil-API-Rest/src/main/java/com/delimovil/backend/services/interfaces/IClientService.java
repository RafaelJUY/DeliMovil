package com.delimovil.backend.services.interfaces;

import com.delimovil.backend.dto.ClientDTO;
import com.delimovil.backend.dto.ClientRequestDTO;
import com.delimovil.backend.dto.UserDTO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IClientService {

    public List<ClientDTO> findAll();
    public ClientDTO findById(Integer id);

    @Transactional
    ClientDTO save(UserDTO userDTO);

    public ClientDTO update(ClientRequestDTO clientDTO, MultipartFile image, Integer id);
    public void deleteById(Integer id);

    
}
