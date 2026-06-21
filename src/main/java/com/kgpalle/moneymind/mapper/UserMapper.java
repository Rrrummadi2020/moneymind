package com.kgpalle.moneymind.mapper;

import com.kgpalle.moneymind.dto.UserDTO;
import com.kgpalle.moneymind.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
}
