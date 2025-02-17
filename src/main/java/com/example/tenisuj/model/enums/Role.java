package com.example.tenisuj.model.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;

@Getter
public enum Role {

    ADMIN("admin"),
    USER("user");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    //shows correct enum value in DB
    @Converter(autoApply = true)
    public static class RoleConverter implements AttributeConverter<Role, String> {

        @Override
        public String convertToDatabaseColumn(Role role) {
            return role == null ? null : role.getRole();
        }

        @Override
        public Role convertToEntityAttribute(String role) {
            return role == null ? null : Role.valueOf(role);
        }
    }
}
