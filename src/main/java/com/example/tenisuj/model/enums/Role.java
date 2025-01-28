package com.example.tenisuj.model.enums;

import lombok.Getter;

@Getter
public enum Role {

    ADMIN("ADMIN"),
    USER("USER"),
    PLAYER("PLAYER");

    private final String role;

    Role(String role) {
        this.role = role;
    }

}

