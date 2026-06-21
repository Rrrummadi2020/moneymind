package com.kgpalle.moneymind.mapper;

import com.kgpalle.moneymind.dto.UserDTO;
import com.kgpalle.moneymind.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(
            target = "password", ignore = true
    )
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
}
