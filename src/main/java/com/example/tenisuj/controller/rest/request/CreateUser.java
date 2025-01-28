package com.example.tenisuj.controller.rest.request;

import lombok.NonNull;

public record CreateUser(
        @NonNull
        String username,

        @NonNull
        String password
) {
}
