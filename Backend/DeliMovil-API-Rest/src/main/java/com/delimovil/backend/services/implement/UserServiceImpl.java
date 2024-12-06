package com.delimovil.backend.services.implement;

import com.delimovil.backend.dto.UserDTO;
import com.delimovil.backend.models.entity.Role;
import com.delimovil.backend.models.entity.User;
import com.delimovil.backend.repositories.IRoleRepository;
import com.delimovil.backend.repositories.IUserRepository;
import com.delimovil.backend.services.interfaces.IUserService;
import com.delimovil.backend.shared.exception.personalized.ModelNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional
    public UserDTO save(UserDTO userDTO) {
        String role = userDTO.getRoleName();

        Optional<Role> optionalRoleUser = roleRepository.findByName(role);

        User user = new User();
        user.setRole(optionalRoleUser.orElseThrow(() -> new ModelNotFoundException("role not found: " + role)));
        user.setUsername(userDTO.getUsername());
        user.setEnabled(userDTO.isEnabled());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return mapper.map(userRepository.save(user), UserDTO.class);
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO userDTO) {
        String role = userDTO.getRoleName();
        Optional<Role> optionalRoleUser = roleRepository.findByName(role);

        User user = new User();
        user.setIdUser(userDTO.getIdUser());
        user.setRole(optionalRoleUser.orElseThrow(() -> new ModelNotFoundException("role not found: " + role)));
        user.setUsername(userDTO.getUsername());
        user.setEnabled(userDTO.isEnabled());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return mapper.map(userRepository.save(user), UserDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO readById(Integer id) {
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new ModelNotFoundException(id, User.class.getSimpleName())
        );
        return mapper.map(user, UserDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> readAll() {
        List<User> users = this.userRepository.findAll();

        return users.stream()
                .map(u -> mapper.map(u, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        this.readById(id);
        this.userRepository.deleteById(id);
    }


    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

}
