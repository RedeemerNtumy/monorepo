package com.dennis.user_service.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String email;
    private String contact;

}
