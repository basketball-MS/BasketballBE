package com.revatureproject.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO
{


    @Length(min = 2)
    private @NonNull String firstName;
    private @NonNull String lastName;

    @NotBlank
    @Length(min = 2)
    @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9]*") // username must be alphanumeric
    private @NonNull String username;

    @NotBlank
    private @NonNull String password;

    @Email // checks for an @ symbol
    private @NonNull String email;

    @NotBlank
    private @NonNull String role;

    private int experience;
}