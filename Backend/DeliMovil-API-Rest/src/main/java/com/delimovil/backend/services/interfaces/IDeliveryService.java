package com.delimovil.backend.services.interfaces;

import com.delimovil.backend.dto.DeliveryDTO;
import com.delimovil.backend.dto.DeliveryRequestDTO;
import com.delimovil.backend.dto.UserDTO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IDeliveryService {
    public List<DeliveryDTO> findAll();
    public DeliveryDTO findById(Integer id);
    public DeliveryDTO save(UserDTO userDto);


    public DeliveryDTO update(DeliveryRequestDTO deliveryDTO, MultipartFile image, Integer id);
    public void deleteById(Integer id);
}
