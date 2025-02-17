package com.example.tenisuj.model.dto;
import com.example.tenisuj.model.enums.Role;

public record SignUpDto (String username, char[] password, Role role) { }
