package com.kgpalle.moneymind.service;

import com.kgpalle.moneymind.dto.UserDTO;
import com.kgpalle.moneymind.entity.Role;
import com.kgpalle.moneymind.entity.User;
import com.kgpalle.moneymind.mapper.UserMapper;
import com.kgpalle.moneymind.repository.RolesRepository;
import com.kgpalle.moneymind.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RolesRepository rolesRepository;

    public UsersService(UsersRepository usersRepository,
                        PasswordEncoder passwordEncoder,
                        RolesRepository rolesRepository,
                        UserMapper userMapper
    ) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserDTO create(UserDTO userDTO) {
        if (userDTO == null || userDTO.getUsername() == null || userDTO.getPassword() == null) {
            throw new RuntimeException("User details are invalid");
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userMapper.toEntity(userDTO);
        User createdUser = usersRepository.save(user);
        Role role = new Role();
        role.setName("ADMIN");
        role.setUser(createdUser);
        this.rolesRepository.save(role);
        userDTO = userMapper.toDTO(createdUser);
        return userDTO;
    }
}
