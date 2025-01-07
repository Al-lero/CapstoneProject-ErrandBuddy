package com.errandbuddy.errandbuddy.Dto.request;

import com.errandbuddy.errandbuddy.utils.RoleType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CreateAdminRequest {
    private String name;

    @NotBlank(message = "Password is Mandatory")
    @Size(max= 6, message = "Password must be at most 6 charcters")
    @Pattern(regexp = "\\d{0,6}", message = "Password must be up to 6 digits only")
    private String password;
    private String phoneNumber;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleType roletype;
}
