package com.EudesGomes017.payment_system.dto;

import com.EudesGomes017.payment_system.entity.Users;
import jakarta.validation.constraints.NotNull;

public record UserRequest (

       @NotNull Long id,

       @NotNull String name,

       @NotNull String email,

       @NotNull String password) {

    public Users toModel() {
        return new Users(name, email, password);
    }
}
