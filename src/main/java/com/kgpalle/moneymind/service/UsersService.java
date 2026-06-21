package com.kgpalle.moneymind.service;

import com.kgpalle.moneymind.dto.UserDTO;
import com.kgpalle.moneymind.entity.User;
import com.kgpalle.moneymind.mapper.UserMapper;
import com.kgpalle.moneymind.repository.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UsersService(UsersRepository usersRepository,
                        PasswordEncoder passwordEncoder,
                        UserMapper userMapper
    ) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public UserDTO create(User user) {
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            throw new RuntimeException("User details are invalid");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createdUser = usersRepository.save(user);
        return userMapper.toDTO(createdUser);
    }
}
