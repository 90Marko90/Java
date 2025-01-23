package com.example.tenisuj.model.enums;

public enum Role {
    ADMIN("ADMIN"),
    PLAYER("player"),;
    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

