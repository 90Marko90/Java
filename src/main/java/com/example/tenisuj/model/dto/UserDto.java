package com.example.tenisuj.model.dto;
import com.example.tenisuj.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//created
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto { //for reception and transmission
    private String username;
    private String password;
    private Role role;
    private String token;
}
