package com.errandbuddy.errandbuddy.Dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBuddyRequest {

    @NotNull(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Phone is Mandatory")
    @Size(max= 11, message = "Phone must be 11 characters")
    @Pattern(regexp = "\\d{0,11}", message = "Phone must be up to 11 digits only")
    private String phoneNumber;

    @NotNull(message = "Age is mandatory")
    @Min(value = 18, message = "Age must be at least 18 years old")
    private Integer age;

    @NotBlank(message = "Nin is Mandatory")
    @Size(max= 11, message = "Password must be 11 characters")
    @Pattern(regexp = "\\d{0,11}", message = "Nin must be up to 11 digits only")
    private String nin;

    @NotBlank(message = "Password is Mandatory")
    @Size(max= 6, message = "Password must be at most 6 characters")
    @Pattern(regexp = "\\d{0,6}", message = "Password must be up to 6 digits only")
    private String password;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Address is Mandatory")
    private String address;

}
