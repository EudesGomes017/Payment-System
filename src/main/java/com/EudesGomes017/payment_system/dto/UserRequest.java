package com.EudesGomes017.payment_system.dto;

import com.EudesGomes017.payment_system.entity.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(

        Long id,

        @NotNull(message = "O campo não pode ser null") @NotBlank(message = "O nome não pode ser vazio")  String name,

        @NotNull(message = "O email não pode ser null") @NotBlank(message = "O e-mail não pode ser vazio") @Email String email,

        @NotNull(message = "O password não pode ser null") @NotBlank(message = "O password não pode ser vazio") @Size(message = "A senha deve conter no mínimo 8 caracteres") String password) {

    public Users toModel() {
        return new Users(name, email, password);
    }
}
