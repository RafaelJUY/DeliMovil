package com.delimovil.backend.services.implement;

import com.delimovil.backend.dto.ClientDTO;
import com.delimovil.backend.dto.ClientRequestDTO;
import com.delimovil.backend.dto.RoleDTO;
import com.delimovil.backend.dto.UserDTO;
import com.delimovil.backend.models.entity.Client;
import com.delimovil.backend.models.entity.Role;
import com.delimovil.backend.models.entity.User;
import com.delimovil.backend.repositories.IClientRepository;
import com.delimovil.backend.services.interfaces.IClientService;
import com.delimovil.backend.shared.exception.personalized.ModelNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements IClientService {
    @Autowired
    private IClientRepository clientRepo;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;
    @Override
    @Transactional(readOnly = true)
    public List<ClientDTO> findAll() {
        return this.clientRepo.findAll()
                .stream()
                .map(res -> mapper.map(res, ClientDTO.class))
                .collect(Collectors.toList());
    }



    @Override
    @Transactional(readOnly = true)
    public ClientDTO findById(Integer id) {
        Client client = clientRepo.findById(id).orElseThrow(
                () -> new ModelNotFoundException(id, Client.class.getSimpleName())
        );
        return mapper.map(client, ClientDTO.class);
    }

    @Override
    @Transactional
    public ClientDTO save(UserDTO userDTO) {

        RoleDTO role = roleService.readById(1);     //en la base de datos ya esta creado el rol con id 1 que tiene el nombre de ROLE_CLIENT.
        String roleName = role.getName();
        Client client = new Client();
        userDTO.setIdUser(client.getId());
        userDTO.setRoleName(roleName);

        UserDTO newUserDTOSaved = userService.save(userDTO);

        User user = mapper.map(newUserDTOSaved, User.class);

        client.setUser_id(user);


        Client saveClient = this.clientRepo.save(client);
        return mapper.map(saveClient, ClientDTO.class);
    }

    @Override
    @Transactional
    public ClientDTO update(ClientRequestDTO clientDTO, MultipartFile image, Integer id) {
        Client clientBD = this.clientRepo.findById(id).orElseThrow(
                () -> new ModelNotFoundException(id, Client.class.getSimpleName())
        );
        clientBD.setFirst_name(clientDTO.getFirst_name());
        clientBD.setLast_name(clientDTO.getLast_name());
        clientBD.setPhone(clientDTO.getPhone());
        clientBD.setLatitude(clientDTO.getLatitude());
        clientBD.setLongitude(clientDTO.getLongitude());
        clientBD.setName_street(clientDTO.getName_street());
        clientBD.setNumber_street(clientDTO.getNumber_street());
        clientBD.setFloor_department(clientDTO.getFloor_department());
        if (image != null && !image.isEmpty()) {
            String imageUrl = cloudinaryService.uploadFile(image);
            clientBD.setImageUrl(imageUrl);
        }
        Client updatedClient = this.clientRepo.save(clientBD);
        return mapper.map(updatedClient, ClientDTO.class);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Optional<Client> client = this.clientRepo.findById(id);
        if(client.isEmpty()){
            throw new ModelNotFoundException(id, Client.class.getSimpleName());
        }
        this.clientRepo.deleteById(id);
    }




}
