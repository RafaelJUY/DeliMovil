package com.delimovil.backend.services.implement;

import com.delimovil.backend.dto.RoleDTO;
import com.delimovil.backend.models.entity.Role;
import com.delimovil.backend.repositories.IRoleRepository;
import com.delimovil.backend.services.interfaces.IRoleService;
import com.delimovil.backend.shared.exception.personalized.ModelNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IRoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional
    public RoleDTO save(RoleDTO roleDTO){
        Role role = this.roleRepository.save(mapper.map(roleDTO, Role.class));

        return mapper.map(role, RoleDTO.class);
    }

    @Override
    @Transactional
    public RoleDTO update(RoleDTO roleDTO){
        this.readById(roleDTO.getIdRole());

        Role role = this.roleRepository.save(mapper.map(roleDTO, Role.class));

        return mapper.map(role, RoleDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleDTO> readAll(){
        List<Role> roles = this.roleRepository.findAll();

        return roles.stream()
                .map(r -> mapper.map(r, RoleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public RoleDTO readById(Integer id){
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new ModelNotFoundException(id, Role.class.getSimpleName())
        );

        return mapper.map(role, RoleDTO.class);
    }

    @Override
    @Transactional
    public void delete(Integer id){
        this.readById(id);

        this.roleRepository.deleteById(id);
    }
}
