package com.kgpalle.moneymind.dto;

import com.kgpalle.moneymind.entity.User;
import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private Long id;
}
