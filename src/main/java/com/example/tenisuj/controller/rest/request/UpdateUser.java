package com.example.tenisuj.controller.rest.request;

public record UpdateUser(
        String username,
        String password,
        String playerId) {
}
